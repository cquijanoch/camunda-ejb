package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.unsa.business.DigaBusiness;
import org.unsa.dto.ReqMesaPartesDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("/expedientesDiga2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpedientesDiga {
  
	@EJB
	private DigaBusiness requerimientos;
	
	@GET
	@Path("bandejaSinRevision")
	public List<ReqMesaPartesDto> listarRequerimientosSinRevision() {
		List<ReqMesaPartesDto> listaRequerimientos = new ArrayList<ReqMesaPartesDto>();
		listaRequerimientos = (List<ReqMesaPartesDto>) requerimientos.listaReqDiga().get("requerimientos");

		return listaRequerimientos;
	}
	
	@PUT
	@Path("aprobarUsuario")
	public void aprobarUsuario(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.revisarAprobadoUsuarioDiga(request);
		
	}
	
	@PUT
	@Path("aprobarRequisito")
	public void aprobarRequisito(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.revisarAprobadoReqDiga(request);
		
	}
	
	@PUT
	@Path("desaprobarUsuario")
	public void desaprobarUsuario(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.revisarDesaprobadoUsuarioDiga(request);
		
	}
	
	@PUT
	@Path("desaprobarRequisito")
	public void desaprobarRequisito(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.revisarDesaprobadoReqDiga(request);
		
	}

   
}