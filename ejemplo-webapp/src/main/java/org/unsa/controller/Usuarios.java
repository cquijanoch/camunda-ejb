package org.unsa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.unsa.business.UsuarioBusiness;
import org.unsa.dto.RequerimientoDto;
import org.unsa.dto.UsuarioDto;
import org.unsa.dto.camunda.ProcessDto;

@Stateless
@Path("registrarUsuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Usuarios {
	
	@EJB
	private UsuarioBusiness usuarioBusiness;

	@POST
	public RequerimientoDto saveRequerimiento(RequerimientoDto processDto) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("usuarioRequerimiento", processDto);
		return (RequerimientoDto) usuarioBusiness.saveRequerimiento(request).get("usuarioRequerimiento") ;
	}

}