package org.unsa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.unsa.business.UsuarioBusiness;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.UsuarioDto;

@Stateless
@Path("registrarUsuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Usuarios {
	
	@EJB
	private UsuarioBusiness usuarioBusiness;

	@POST
	public UsuarioDto saveUser(UsuarioDto processDto) {
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("usuarioRequerimiento", processDto);
		return (UsuarioDto) usuarioBusiness.saveRequerimiento(request).get("usuarioRequerimiento") ;
	}

}