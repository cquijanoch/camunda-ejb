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

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.HeaderDto;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;
import org.unsa.util.BPM;

@Stateless
@Path("/mesapartes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequerimientoMesaPartesController {
	

	@EJB
	private CamundaApi camundaApi;
	
	@POST
	@Path("/save")
	public ResponseMessage<ReqMesaPartesDto> saveAnexo(RequestMessage<ReqMesaPartesDto> request) {
		
		ReqMesaPartesDto requerimiento = request.getBody();
		HeaderDto header = request.getHeader();
		
		TaskDto userTask = new TaskDto();
		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());
		
		Map<String,Object>  variables= new HashMap<String,Object>();
		variables.put("requerimiento", requerimiento);
		userTask.setVariables(variables);
		camundaApi.completeTask(userTask);
		
		return null;
	}
	
	@GET
	@Path("/bandejaAprobados")
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> getAllRequeriment() {
		
		
		List<TaskDto> activeTasks = camundaApi.getActiveTaskBySubProcess(BPM.PROCESO_PAGO,BPM.PROCESO_REQ_REG,BPM.MP_REQ_INGRESAR);
		
		ResponseMessage<GetTaskDto<ReqMesaPartesDto>> response = new ResponseMessage<GetTaskDto<ReqMesaPartesDto>>();
		GetTaskDto<ReqMesaPartesDto> body = new GetTaskDto<ReqMesaPartesDto>();
		
		
		for(TaskDto taskIndex : activeTasks){
			ReqMesaPartesDto requerimiento = (ReqMesaPartesDto)taskIndex.getVariable("requerimiento");
			
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
	
	@POST
	public ResponseMessage<String> saveRequeriment(RequestMessage<ReqMesaPartesDto> request) {
		
		ReqMesaPartesDto requerimiento = request.getBody();
		
		HeaderDto header = request.getHeader();
		TaskDto userTask= new TaskDto();

		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());
		
		 Map<String,Object>  variables= new HashMap<String,Object>();
		 variables.put("requerimiento", requerimiento);
		 userTask.setVariables(variables);
		 camundaApi.completeTask(userTask);
		
		 
		 ResponseMessage<String> response = new ResponseMessage<String>(); 
		 response.setBody("completado");
		return response;

	}
	
	@GET
	public ResponseMessage<GetTaskDto<ReqMesaPartesDto>> getAll(){
		
		List<TaskDto> activeTasks = camundaApi.getTaskByTaskId(BPM.MP_REQ_REVISAR, BPM.PROCESO_PAGO);
		
		ResponseMessage<GetTaskDto<ReqMesaPartesDto>> response = new ResponseMessage<GetTaskDto<ReqMesaPartesDto>>();
		GetTaskDto<ReqMesaPartesDto> body = new GetTaskDto<ReqMesaPartesDto>();
		
		
		for(TaskDto taskIndex : activeTasks){
			ReqMesaPartesDto requerimiento = (ReqMesaPartesDto)taskIndex.getVariable("ReqMesaPartesDto");
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			
			UserTaskDto<ReqMesaPartesDto> task = new UserTaskDto<ReqMesaPartesDto>();
			task.setHeaderDto(header);
			task.setBody(requerimiento);
			body.setTask(task);
		}
		
		response.setBody(body);
		return response;
	}
}
