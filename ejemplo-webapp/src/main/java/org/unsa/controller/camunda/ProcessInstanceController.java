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
import org.unsa.dto.camunda.ActivityInstanceDto;
import org.unsa.dto.camunda.ProcessDefinitionDiagramDto;

@Stateless
@Path("/process-instance")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ProcessInstanceController {
	
	@EJB
	private CamundaApi camundaApi;
	
	@GET
	@Path("/activity-instances/{processInstanceId}")
	public  ActivityInstanceDto getActivityInstanceTree(@PathParam("processInstanceId") String paramString){
		
		 ActivityInstanceDto activityInstance = camundaApi.getActivityInstanceTree(paramString) ;
		return activityInstance;
	}
}
