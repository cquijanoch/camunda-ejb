package org.unsa.controller;

import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.dto.PersonaDto;
import org.unsa.dto.UsuarioDto;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("requisitos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Requisitos {
  

//    @GET
//    public List<UsuarioDto> obtenerRequerimientos(){
//    	List<PersonaDto> listaPersona=new ArrayList<PersonaDto>();
//    	PersonaDto persona=new PersonaDto("Asunto", "34454545", "A", "Jose Tito", 34);
//    	listaPersona.add(persona);
//    	listaPersona.add(persona);
//    	listaPersona.add(persona);
//    	
//    	return listaPersona;
//    }


}