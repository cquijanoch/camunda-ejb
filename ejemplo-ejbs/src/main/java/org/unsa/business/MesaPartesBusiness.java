package org.unsa.business;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.unsa.dto.UsuarioDto;

@Local
public interface MesaPartesBusiness {
	public Map<String,Object> listaRequerimientos();
	public Map<String,Object> listaReqMesaPartes();
	public Map<String,Object> listaReqMesaPartesAprobados();
	public Map<String,Object> listaReqMesaPartesDesaprobados();
	public Map<String,Object> listaReqMesaPartesSinRevisar();
	public void aprobarReqMesaPartes(Map<String,Object> request);
	public void desaprobarReqMesaPartes(Map<String,Object> request);
	public void registrarReqMesaPartes(Map<String,Object> request);
	public void derivarReqMesaPartes(Map<String,Object> request);
	
}
