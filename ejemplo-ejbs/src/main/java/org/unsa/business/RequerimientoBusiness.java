package org.unsa.business;

import javax.ejb.Stateless;

import org.unsa.dto.RequerimientoDto;

@Stateless
public interface RequerimientoBusiness {
	
	RequerimientoDto save(RequerimientoDto requerimiento);

}
