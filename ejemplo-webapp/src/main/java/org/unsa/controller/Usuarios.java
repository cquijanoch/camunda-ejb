package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.unsa.business.UsuarioBusiness;
import org.unsa.dto.ProcessDto;

@Stateless
@Path("registrarUsuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Usuarios {
	
	@EJB
	private UsuarioBusiness usuarioBusiness;

	@POST
	public ProcessDto saveUser(ProcessDto processDto) {
	
		return this.usuarioBusiness.saveUser(processDto) ;

	}

}