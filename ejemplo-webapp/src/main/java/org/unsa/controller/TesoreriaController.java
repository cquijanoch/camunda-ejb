package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.ExpedienteDigaDto;
import org.unsa.dto.HeaderDto;
import org.unsa.dto.PersonaDto;
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;
import org.unsa.mybatis.bean.Contoh;
import org.unsa.util.BPM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("tesoreria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TesoreriaController {
  
//	@EJB
//	private RequerimientoBusiness requerimientoBusiness;
	
	@EJB
	private CamundaApi camundaApi;
	
    
    @GET
	public ResponseMessage<GetTaskDto<RequerimientoDto>> getAllRequeriment(){
		
		List<TaskDto> activeTasks = camundaApi.getTaskByTaskId(BPM.TESORERIA_PAG_SELECCIONAR, BPM.PROCESO_PAGO);
		
		ResponseMessage<GetTaskDto<RequerimientoDto>> response = new ResponseMessage<GetTaskDto<RequerimientoDto>>();
		GetTaskDto<RequerimientoDto> body = new GetTaskDto<RequerimientoDto>();
		
		
		for(TaskDto taskIndex : activeTasks){
			RequerimientoDto requerimiento = (RequerimientoDto)taskIndex.getVariable("RequerimientoDto");
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			
			UserTaskDto<RequerimientoDto> task = new UserTaskDto<RequerimientoDto>();
			task.setHeaderDto(header);
			task.setBody(requerimiento);
			body.setTask(task);
		}
		
		response.setBody(body);
		return response;
		
	}
    
    @GET
    @Path("/pagoscheques")
	public ResponseMessage<GetTaskDto<RequerimientoDto>> getAllRequeriment2(){
		
		List<TaskDto> activeTasks = camundaApi.getTaskByTaskId(BPM.TESORERIA_PAG_CHEQUE, BPM.PROCESO_PAGO);
		
		ResponseMessage<GetTaskDto<RequerimientoDto>> response = new ResponseMessage<GetTaskDto<RequerimientoDto>>();
		GetTaskDto<RequerimientoDto> body = new GetTaskDto<RequerimientoDto>();	
		
		for(TaskDto taskIndex : activeTasks){
			RequerimientoDto requerimiento = (RequerimientoDto)taskIndex.getVariable("RequerimientoDto");
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			
			UserTaskDto<RequerimientoDto> task = new UserTaskDto<RequerimientoDto>();
			task.setHeaderDto(header);
			task.setBody(requerimiento);
			body.setTask(task);
		}
		
		response.setBody(body);
		return response;
		
	}
    
    @GET
    @Path("/depositoCuenta")
	public ResponseMessage<GetTaskDto<RequerimientoDto>> getAllRequeriment3(){
		
		List<TaskDto> activeTasks = camundaApi.getTaskByTaskId(BPM.TESORERIA_PAG_CUENTA, BPM.PROCESO_PAGO);
		
		ResponseMessage<GetTaskDto<RequerimientoDto>> response = new ResponseMessage<GetTaskDto<RequerimientoDto>>();
		GetTaskDto<RequerimientoDto> body = new GetTaskDto<RequerimientoDto>();
		
		
		for(TaskDto taskIndex : activeTasks){
			RequerimientoDto requerimiento = (RequerimientoDto)taskIndex.getVariable("RequerimientoDto");
			HeaderDto header = new HeaderDto();
			header.setProcessInstanceId(taskIndex.getProcessInstanceId());
			header.setExecutionId(taskIndex.getExecutionId());
			header.setTaskId(taskIndex.getTaskId());
			
			UserTaskDto<RequerimientoDto> task = new UserTaskDto<RequerimientoDto>();
			task.setHeaderDto(header);
			task.setBody(requerimiento);
			body.setTask(task);
		}
		
		response.setBody(body);
		return response;
		
	}
    
	@POST
	public ResponseMessage<String> saveRequeriment(RequestMessage<ExpedienteDigaDto> request) {
		
		ExpedienteDigaDto requerimiento = request.getBody();
		//this.requerimientoBusiness.save(requerimiento);
		
		HeaderDto header = request.getHeader();
		TaskDto userTask= new TaskDto();

		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());
		
		 Map<String,Object>  variables= new HashMap<String,Object>();
		 variables.put("efectivo", requerimiento.getEfectivo());
		 variables.put("cheque", requerimiento.getCheque());
		 variables.put("deposito", requerimiento.getDeposito());
		 userTask.setVariables(variables);
		 camundaApi.completeTask(userTask);
		
		 
		 ResponseMessage<String> response = new ResponseMessage<String>(); 
		 response.setBody("completado");
		return response;

	}

}

