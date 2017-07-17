package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.business.Bar;
import org.unsa.dto.PersonaDto;
import org.unsa.mybatis.bean.Contoh;

import java.util.ArrayList;
import java.util.List;

@Stateless

@Path("contoh")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Contohs {
  
	@EJB
	Bar bar;
    @GET
    public List<Contoh> obtenerPersona(){
    	
    	return bar.listarCon();
    }

   
}