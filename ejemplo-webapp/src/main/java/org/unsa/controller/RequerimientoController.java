package org.unsa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.HeaderDto;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;

@Stateless
@Path("registrarRequerimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequerimientoController {
	
	
	@EJB
	private CamundaApi camundaApi;

	@POST
	public ResponseMessage<GetTaskDto<RequerimientoDto>> saveRequeriment(RequestMessage<RequerimientoDto> request) {
		
		RequerimientoDto requerimiento = request.getBody();
		
		ProcessDto processDto = new ProcessDto();
		processDto.setProcessDefinitionKey("Proceso2dfase");
		camundaApi.createProcess(processDto);

		List<TaskDto> activeTasks = camundaApi.getTaskByProcessInstance(processDto);
		TaskDto userTask= null;

		if (activeTasks.size() == 1) {
			 userTask = activeTasks.get(0);
			 Map<String,Object>  variables= new HashMap<String,Object>();
			 variables.put("usuarioRequerimiento", requerimiento);
			 userTask.setVariables(variables);
			camundaApi.completeTask(userTask);
		}
		
		ResponseMessage<GetTaskDto<RequerimientoDto>> response = new ResponseMessage<GetTaskDto<RequerimientoDto>>();
		
		HeaderDto header = new HeaderDto();
		header.setBusinessKey(processDto.getBusinessKey());
		header.setProcessDefinitionKey(processDto.getProcessDefinitionKey());
		header.setProcessInstanceId(processDto.getProcessInstanceId());
		header.setProcessName(processDto.getProcessName());
		header.setExecutionId(userTask.getExecutionId());
//		
		GetTaskDto<RequerimientoDto> body = new GetTaskDto<RequerimientoDto>();
		UserTaskDto<RequerimientoDto> task = new UserTaskDto<RequerimientoDto>();
		
		task.setHeaderDto(header);
		task.setBody(requerimiento);
		
		body.setTask(task);
				
		response.setBody(body);
		return response;

	}
	
	@GET
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> getAllRequeriment(){
		
		List<TaskDto> activeTasks = camundaApi.getTaskByTaskId("task_review_req", "Proceso2dfase");
		
		ResponseMessage<GetTaskDto<ReqMesaPartesDto>> response = new ResponseMessage<GetTaskDto<ReqMesaPartesDto>>();
		GetTaskDto<ReqMesaPartesDto> body = new GetTaskDto<ReqMesaPartesDto>();
		
		
		for(TaskDto taskIndex : activeTasks){
			ReqMesaPartesDto requerimiento = (ReqMesaPartesDto)taskIndex.getVariable("ReqMesaPartesDto");
			
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskName(taskIndex.getName());
			header.setTaskId(taskIndex.getTaskId());
			header.setProcessDefinitionKey(taskIndex.getProcessDefinitionKey());
			header.setTaskKey(taskIndex.getKey());
			
			UserTaskDto<ReqMesaPartesDto> task = new UserTaskDto<ReqMesaPartesDto>();
			task.setBody(requerimiento);
			task.setHeaderDto(header);
			body.setTask(task);
		}
		
		response.setBody(body);
		return response;
	}
	

}