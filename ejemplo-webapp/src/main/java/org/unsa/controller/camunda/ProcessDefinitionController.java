package org.unsa.controller.camunda;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.camunda.ProcessDefinitionDiagramDto;

@Stateless
@Path("/process-definition")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ProcessDefinitionController {
	
	@EJB
	private CamundaApi camundaApi;
	
	@GET
	@Path("/xml/{id}")
	public  ProcessDefinitionDiagramDto getProcessDefinitionBpmn20Xml(@PathParam("id") String paramString){
		
		ProcessDefinitionDiagramDto diagrama = camundaApi.getProcessDefinitionBpmn20Xml(paramString);
		
		return diagrama;
	}
}