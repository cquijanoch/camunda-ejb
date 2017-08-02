package org.unsa.controller;

import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.dto.PersonaDto;

import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("personas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Personal {
  

    @GET
    public List<PersonaDto> obtenerPersona(){
    	List<PersonaDto> listaPersona=new ArrayList<PersonaDto>();
    	PersonaDto persona=new PersonaDto();
//    	persona.setNombre("a");
//    	persona.setSegundoNombre("b");
//    	persona.setSegundoNombre("c");
//    	persona.setApellidos("a");
//    	listaPersona.add(persona);
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
    	return persona;

    }

    @DELETE
    @Path("{id}")
    public void deletePerson(@PathParam("id") Long id) {
        
    }
}