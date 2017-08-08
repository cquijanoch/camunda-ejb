package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.business.RequerimientoBusiness;
import org.unsa.camunda.CamundaApi;
import org.unsa.dto.HeaderDto;
import org.unsa.dto.PersonaDto;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserTaskDto;
import org.unsa.message.GetTaskDto;
import org.unsa.message.RequestMessage;
import org.unsa.message.ResponseMessage;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("mesapartes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class mesaPartes {
	
	@EJB
	private RequerimientoBusiness requerimientoBusiness;
	
	@EJB
	private CamundaApi camundaApi;

	@POST
	public ResponseMessage<String> saveRequeriment(RequestMessage<RequerimientoDto> request) {
		
		RequerimientoDto requerimiento = request.getBody();
		this.requerimientoBusiness.save(requerimiento);
		
		HeaderDto header = request.getHeader();
		TaskDto userTask= new TaskDto();

		userTask.setExecutionId(header.getExecutionId());
		userTask.setTaskId(header.getTaskId());
		
		 Map<String,Object>  variables= new HashMap<String,Object>();
		 variables.put("completed", requerimiento.getCompleted());
		 userTask.setVariables(variables);
		 camundaApi.completeTask(userTask);
		
		 
		 ResponseMessage<String> response = new ResponseMessage<String>(); 
		 response.setBody("completado");
		return response;

	}
	
	@GET
	public ResponseMessage<GetTaskDto<RequerimientoDto>> getAllRequeriment(){
		
		List<TaskDto> activeTasks = camundaApi.getTaskByTaskId("task_review_req", "Proceso2dfase");
		
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
	

  
/*
    @GET
    public List<PersonaDto> obtenerPersona(){
    	List<PersonaDto> listaPersona=new ArrayList<PersonaDto>();
    	PersonaDto persona=new PersonaDto("Asunto", "34454545", "A", "Jose Tito", 34);
    	listaPersona.add(persona);
    	listaPersona.add(persona);
    	listaPersona.add(persona);
    	
    	return listaPersona;
    }

    @GET
    @Path("{id}")
    public PersonaDto getPersona( @PathParam("id") Long id) {
    	PersonaDto persona=new PersonaDto();
    	return persona;
         
    }

    @POST
    public PersonaDto savePerson(PersonaDto person) {
    	PersonaDto persona=person;    	
    	System.out.println(persona);
    	System.out.println("hola mundo");
    	return persona;

    }

    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Long id) {
        
    }
    
    */
}