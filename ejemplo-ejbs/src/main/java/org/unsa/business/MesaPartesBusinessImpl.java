package org.unsa.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.unsa.camunda.CamundaApi;
import org.unsa.common.dao.MesaPartesDao;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.dto.UsuarioDto;
import org.unsa.util.Constantes;

@Stateless
public class MesaPartesBusinessImpl implements MesaPartesBusiness {
	
	private MesaPartesDao mesaPartesDao;
	
	public MesaPartesDao getService() {
		return this.mesaPartesDao;
	}
	@PostConstruct
	public void initialize() {
		this.mesaPartesDao = new MesaPartesDao();
	}
	

	@Override
	public Map<String,Object> listaRequerimientos() {
		List<UsuarioDto> requerimientos=mesaPartesDao.getRequerimientos();
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqMesaPartes() {
		List<ReqMesaPartesDto> requerimientos=mesaPartesDao.getReqMesaPartes();
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqMesaPartesAprobados() {
		List<ReqMesaPartesDto> requerimientos=mesaPartesDao.getReqMesaPartesByEstado(Constantes.ESTADO_REQ_APROBADO);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqMesaPartesDesaprobados() {
		List<ReqMesaPartesDto> requerimientos=mesaPartesDao.getReqMesaPartesByEstado(Constantes.ESTADO_REQ_DESAPROBADO);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqMesaPartesSinRevisar() {
		List<ReqMesaPartesDto> requerimientos=mesaPartesDao.getReqMesaPartesByEstado(Constantes.ESTADO_REQ_SIN_CALIFICACION);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public void aprobarReqMesaPartes(Map<String,Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		mesaPartesDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_APROBADO);
		
	}
	@Override
	public void desaprobarReqMesaPartes(Map<String, Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		mesaPartesDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_DESAPROBADO);
		
	}
	@Override
	public void registrarReqMesaPartes(Map<String, Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		mesaPartesDao.updateDetalleRequerimiento(registro);
	}
	
	@Override
	public void derivarReqMesaPartes(Map<String, Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		mesaPartesDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_DERIVADO);
		registro.setEstadoDiga(Constantes.ESTADO_REQ_SIN_CALIFICACION);
		mesaPartesDao.guardarBandejaDiga(registro);
	}
	
}
