package org.unsa.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
		String a = "Task_169u5wo";
		return null;
	}
}
