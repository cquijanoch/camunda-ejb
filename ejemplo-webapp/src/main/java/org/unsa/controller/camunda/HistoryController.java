package org.unsa.controller.camunda;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.unsa.camunda.CamundaApi;
import org.unsa.dto.camunda.HistoricActivityInstanceDto;
import org.unsa.dto.camunda.HistoricProcessInstanceDto;
import org.unsa.dto.camunda.ProcessDefinitionDiagramDto;

@Stateless
@Path("/history")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class HistoryController {
	
	@EJB
	private CamundaApi camundaApi;
	@GET
	@Path("/process-instance/{processInstanceId}")
	public  HistoricProcessInstanceDto getHistoricProcessInstance(@PathParam("processInstanceId") String paramString){
		
		HistoricProcessInstanceDto historicProcessInstance =camundaApi.getHistoricProcessInstance(paramString);

		return historicProcessInstance;
	}
	
	@GET
	@Path("/activity-instance/{processInstanceId}")
	public  List<HistoricActivityInstanceDto> getPrsocessDefinitionBpmn20Xml(@PathParam("processInstanceId") String paramString){
		
		List<HistoricActivityInstanceDto> response = camundaApi.getHistoricActivityInstance(paramString);
		return response;
	}
}
