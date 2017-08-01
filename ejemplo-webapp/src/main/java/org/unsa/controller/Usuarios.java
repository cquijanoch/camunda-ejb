package org.unsa.controller;

import javax.ejb.Stateless;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

import org.unsa.dto.UsuarioDto;;

@Stateless
@Path("registrarUsuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Usuarios {

	@POST
	public UsuarioDto guardarUsuario(UsuarioDto usua) {
		UsuarioDto Usuario = usua;
		System.out.println("llego a guardar usuario: " + Usuario.getNombre());
		return Usuario;

	}

}