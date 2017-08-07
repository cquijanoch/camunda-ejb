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
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;

@Stateless
@Path("/requerimientoMesaPartes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequerimientoMesaPartesController {
	
	@EJB
	private RequerimientoBusiness requerimientoBusiness;

	@EJB
	private CamundaApi camundaApi;
	
	@POST
	@Path("/save")
	public ResponseMessage<GetTaskDto<RequerimientoDto>> save(RequestMessage<RequerimientoDto> request) {
		
		String keySubprocess = "RegistrarReq";
		TaskDto userTask = new TaskDto();
		 Map<String,Object>  variables= new HashMap<String,Object>();
		 userTask.setVariables(variables);
		camundaApi.completeTask(userTask);
		return null;
	}
	
	@GET
	@Path("/getAll")
	public ResponseMessage<GetTaskDto<RequerimientoDto>> getAllRequeriment() {
		
		String keyProcess = "Proceso2dfase";
		String keySubprocess = "RegistrarReq";
		String keyTask = "Task_169u5wo";
		
		List<TaskDto> activeTasks = camundaApi.getActiveTaskBySubProcess(keyProcess,keySubprocess,keyTask);
		
		ResponseMessage<GetTaskDto<RequerimientoDto>> response = new ResponseMessage<GetTaskDto<RequerimientoDto>>();
		GetTaskDto<RequerimientoDto> body = new GetTaskDto<RequerimientoDto>();
		
		for(TaskDto taskIndex : activeTasks){
			RequerimientoDto requerimiento = (RequerimientoDto)taskIndex.getVariable("requerimiento");
			
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskName(taskIndex.getName());
			header.setTaskId(taskIndex.getTaskId());
			header.setProcessDefinitionKey(taskIndex.getProcessDefinitionKey());
			header.setTaskKey(taskIndex.getKey());
			
			UserTaskDto<RequerimientoDto> task = new UserTaskDto<RequerimientoDto>();
			task.setBody(requerimiento);
			task.setHeaderDto(header);
			body.setTask(task);
		}
		
		response.setBody(body);
		return response;
	}
}
