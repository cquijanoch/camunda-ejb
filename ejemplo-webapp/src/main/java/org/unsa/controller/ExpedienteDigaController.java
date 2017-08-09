package org.unsa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.HeaderDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;



@Stateless
@Path("/expedientesDiga")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ExpedienteDigaController {
	
	@EJB
	private CamundaApi camundaApi;
  
	@POST
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> saveRequeriment(
			RequestMessage<ReqMesaPartesDto> request) {

		HeaderDto header = request.getHeader();
		
		TaskDto userTask = new TaskDto();
		
		ReqMesaPartesDto requerimientoDiga = request.getBody();
		
		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("ReqMesaPartesDto", requerimientoDiga);
		userTask.setVariables(variables);
		camundaApi.completeTask(userTask);

		return null;
	}

	@GET
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> getAllRequerimientosDIGA() {
		List<TaskDto> activeTasksRR = camundaApi.getTaskByTaskId("task_review_req_diga", "Proceso2dfase");

		ResponseMessage<GetTaskDto<ReqMesaPartesDto>> response = new ResponseMessage<GetTaskDto<ReqMesaPartesDto>>();
		GetTaskDto<ReqMesaPartesDto> body = new GetTaskDto<ReqMesaPartesDto>();

		for (TaskDto taskIndex : activeTasksRR) {
			ReqMesaPartesDto requerimiento = (ReqMesaPartesDto)taskIndex.getVariable("requerimiento");
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			
			UserTaskDto<ReqMesaPartesDto> task = new UserTaskDto<ReqMesaPartesDto>();
			task.setBody(requerimiento);
			task.setHeaderDto(header);
			body.setTask(task);
		}
		response.setBody(body);
		return response;
	}
	
	@POST
	@Path("/validarUsuario")
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> saveUsuario(
			RequestMessage<ReqMesaPartesDto> request) {

		HeaderDto header = request.getHeader();

		TaskDto userTask = new TaskDto();

		ReqMesaPartesDto requerimiento = request.getBody();

		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("ReqMesaPartesDto", requerimiento);
		userTask.setVariables(variables);
		camundaApi.completeTask(userTask);

		return null;
	}

	@GET
	@Path("/validarUsuario")
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> getAllUsuariosDIGA() {
		List<TaskDto> activeTasksVU = camundaApi.getTaskByTaskId("Task_1411tui", "Proceso2dfase");

		ResponseMessage<GetTaskDto<ReqMesaPartesDto>> response = new ResponseMessage<GetTaskDto<ReqMesaPartesDto>>();
		GetTaskDto<ReqMesaPartesDto> body = new GetTaskDto<ReqMesaPartesDto>();

		for (TaskDto taskIndex : activeTasksVU) {
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			UserTaskDto<ReqMesaPartesDto> task = new UserTaskDto<ReqMesaPartesDto>();
			task.setHeaderDto(header);
			body.setTask(task);
		}
		response.setBody(body);
		return response;
	}


   
}