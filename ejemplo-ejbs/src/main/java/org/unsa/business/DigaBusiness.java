package org.unsa.business;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.unsa.dto.UsuarioDto;

@Local
public interface DigaBusiness {
	
	public Map<String,Object> listaReqDiga();
	public Map<String,Object> listaReqDigaAprobados();
	public Map<String,Object> listaReqDigaDesaprobados();
	public Map<String,Object> listaReqDigaSinRevisar();
	public void aprobarReqDiga(Map<String,Object> request);
	public void desaprobarReqDiga(Map<String,Object> request);
	public void revisarAprobadoReqDiga(Map<String,Object> request);
	public void revisarDesaprobadoReqDiga(Map<String,Object> request);
	public void revisarAprobadoUsuarioDiga(Map<String,Object> request);
	public void revisarDesaprobadoUsuarioDiga(Map<String,Object> request);

	
}
