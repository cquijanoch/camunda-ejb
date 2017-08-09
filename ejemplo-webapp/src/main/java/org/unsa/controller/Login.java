package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unsa.business.MesaPartesBusiness;
import org.unsa.config.TokenAuthentication;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.UsuarioDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Login {
	@POST
	public String SignIn(String content) {

//		JSONObject jsonObject = new JSONObject(content);
//		WebRequest wRequest = WebRequest.createFromJSON(jsonObject);
//		WebResponse wResponse = wRequest.invoke(Sigesmed.MODULO_CONFIGURACION);
		return "";
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