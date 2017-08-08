package org.unsa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.unsa.business.RequerimientoBusiness;
import org.unsa.camunda.CamundaApi;
import org.unsa.dto.HeaderDto;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.RequerimientoDigaDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;

@Stateless
@Path("RequemientoDiga")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class requerimientoDigaController {

	@EJB
	private RequerimientoBusiness requerimientoBusiness;

	@EJB
	private CamundaApi camundaApi;

	@POST
	public ResponseMessage<GetTaskDto<RequerimientoDigaDto>> saveRequeriment(
			RequestMessage<RequerimientoDigaDto> request) {

		HeaderDto header = request.getHeader();
		
		TaskDto userTask = new TaskDto();
		
		RequerimientoDigaDto body = request.getBody();
		
		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("validado", body.getAprobado());
		userTask.setVariables(variables);
		camundaApi.completeTask(userTask);

		return null;
	}

	@GET
	public ResponseMessage<GetTaskDto<RequerimientoDigaDto>> getAllRequerimientosDIGA() {
		List<TaskDto> activeTasksRR = camundaApi.getTaskByTaskId("task_review_req_diga", "Proceso2dfase");

		ResponseMessage<GetTaskDto<RequerimientoDigaDto>> response = new ResponseMessage<GetTaskDto<RequerimientoDigaDto>>();
		GetTaskDto<RequerimientoDigaDto> body = new GetTaskDto<RequerimientoDigaDto>();

		for (TaskDto taskIndex : activeTasksRR) {
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			UserTaskDto<RequerimientoDigaDto> task = new UserTaskDto<RequerimientoDigaDto>();
			task.setHeaderDto(header);
			body.setTask(task);
		}
		response.setBody(body);
		return response;
	}
}
