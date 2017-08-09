package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.unsa.business.MesaPartesBusiness;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.UsuarioDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("/mesaPartes2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MesaPartes {
	@EJB
	private MesaPartesBusiness requerimientos;

	@GET
	@Path("bandejaSinRevision")
	public List<ReqMesaPartesDto> obtenerRequerimientos() {
		List<ReqMesaPartesDto> listaRequerimientos = new ArrayList<ReqMesaPartesDto>();
		listaRequerimientos = (List<ReqMesaPartesDto>) requerimientos.listaReqMesaPartesSinRevisar().get("requerimientos");

		return listaRequerimientos;
	}

	@GET
	@Path("bandejaAprobados")
	public List<ReqMesaPartesDto> obtenerRequerimientosAprobados() {
		List<ReqMesaPartesDto> listaRequerimientos = new ArrayList<ReqMesaPartesDto>();
		listaRequerimientos = (List<ReqMesaPartesDto>) requerimientos.listaReqMesaPartesAprobados().get("requerimientos");

		return listaRequerimientos;
	}
	
	@PUT
	@Path("aprobarRequisito")
	public void aprobarRequisito(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.aprobarReqMesaPartes(request);
	}
	
	@PUT
	@Path("desaprobarRequisito")
	public void desaprobarRequisito(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.desaprobarReqMesaPartes(request);
	}
	
	@PUT
	@Path("registrarRequerimiento")
	public void registrarRequerimiento(ReqMesaPartesDto requerimiento) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		requerimientos.registrarReqMesaPartes(request);
		requerimientos.derivarReqMesaPartes(request);
	}

}