package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unsa.business.LoginBusiness;
import org.unsa.config.TokenAuthentication;
import org.unsa.dto.RolDto;
import org.unsa.dto.UserDto;

import java.util.List;

@Stateless
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Login {
	
	@EJB
	LoginBusiness login;
	
	@POST
	@Path("search")
	public List<RolDto> search(UserDto user) {

		List<RolDto> roles=(List<RolDto>) login.listarRolesByNickname(user.getNombre()).get("roles");
		
		return roles;
	}

	@HEAD
	@Produces(MediaType.APPLICATION_JSON)
	public Response verificarToken(@HeaderParam("autorizacion") String autorizacion) {

		// si no hay token
		if (autorizacion == null || autorizacion.contentEquals("")) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		// token no autorizado
		if (!(new TokenAuthentication()).isValidAuthentication(autorizacion)) {
			System.out.println("TOKEN INVALIDO");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		// el token a sido validado
		 return Response.ok().build();
	}
}