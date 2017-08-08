package org.unsa.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.unsa.business.Bar;
import org.unsa.dto.ExpedienteDigaDto;
import org.unsa.dto.PersonaDto;
import org.unsa.mybatis.bean.Contoh;

import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("expedientesDiga")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpedientesDiga {
  
	@EJB
	Bar bar;
	
    @GET
    public List<ExpedienteDigaDto> listarExpedientes(){
    	List<ExpedienteDigaDto> lista=new ArrayList<ExpedienteDigaDto>();
    	ExpedienteDigaDto exp1=new ExpedienteDigaDto();
    	ExpedienteDigaDto exp2=new ExpedienteDigaDto();
    	
    	exp1.setDni("77777777");
    	exp1.setNombre("alonso silva");
    	exp1.setDetalle("monto de 1000");
    	
    	exp2.setDni("77766666");
    	exp2.setNombre("abel silva");
    	exp2.setDetalle("monto de 5000");
    	
    	lista.add(exp1);
    	lista.add(exp2);
    	return lista;
    }

   
}