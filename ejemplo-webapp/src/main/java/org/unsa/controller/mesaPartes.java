package org.unsa.controller;

import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.dto.PersonaDto;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("aprobarRequisito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class mesaPartes {
  

    @GET
    public List<PersonaDto> obtenerPersona(){
    	List<PersonaDto> listaPersona=new ArrayList<PersonaDto>();
    	PersonaDto persona=new PersonaDto();
    	
    	return listaPersona;
    }

    @GET
    @Path("{id}")
    public PersonaDto getPersona( @PathParam("id") Long id) {
    	PersonaDto persona=new PersonaDto();
    	return persona;
         
    }

    @POST
    public PersonaDto savePerson(PersonaDto person) {
    	PersonaDto persona=person;    	
    	System.out.println(persona);
    	System.out.println("hola mundo");
    	return persona;

    }

    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Long id) {
        
    }
}