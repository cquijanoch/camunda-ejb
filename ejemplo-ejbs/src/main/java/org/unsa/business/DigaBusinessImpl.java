package org.unsa.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.unsa.common.dao.DigaDao;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.util.Constantes;

@Stateless
public class DigaBusinessImpl implements DigaBusiness {
	
	private DigaDao digaDao;
	
	public DigaDao getService() {
		return this.digaDao;
	}
	@PostConstruct
	public void initialize() {
		this.digaDao = new DigaDao();
	}
	
	@Override
	public Map<String, Object> listaReqDiga() {
		List<ReqMesaPartesDto> requerimientos=digaDao.getReqDiga();
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqDigaAprobados() {
		List<ReqMesaPartesDto> requerimientos=digaDao.getReqDigaByEstado(Constantes.ESTADO_REQ_APROBADO);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqDigaDesaprobados() {
		List<ReqMesaPartesDto> requerimientos=digaDao.getReqDigaByEstado(Constantes.ESTADO_REQ_DESAPROBADO);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public Map<String, Object> listaReqDigaSinRevisar() {
		List<ReqMesaPartesDto> requerimientos=digaDao.getReqDigaByEstado(Constantes.ESTADO_REQ_SIN_CALIFICACION);
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimientos", requerimientos);
		return request;
	}
	@Override
	public void aprobarReqDiga(Map<String,Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_APROBADO);
		
	}
	@Override
	public void revisarAprobadoReqDiga(Map<String,Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		ReqMesaPartesDto actual=digaDao.getReqDigaById(registro.getId());
		if(actual.getEstadoDiga().equals(Constantes.ESTADO_REQ_SIN_CALIFICACION))
		{
			digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REV_REQ_APROBADO);
		} else if(actual.getEstadoDiga().equals(Constantes.ESTADO_REV_REQ_APROBADO))
		{
			digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_DERIVADO);
		}
		
	}
	@Override
	public void revisarAprobadoUsuarioDiga(Map<String,Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		ReqMesaPartesDto actual=digaDao.getReqDigaById(registro.getId());
		if(actual.getEstadoDiga().equals(Constantes.ESTADO_REQ_SIN_CALIFICACION))
		{
			digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REV_USER_APROBADO);
		} else if(actual.getEstadoDiga().equals(Constantes.ESTADO_REV_USER_APROBADO))
		{
			digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_DERIVADO);
		}
		
	}
	@Override
	public void revisarDesaprobadoReqDiga(Map<String,Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REV_REQ_DESAPROBADO);
		
	}
	@Override
	public void revisarDesaprobadoUsuarioDiga(Map<String,Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REV_USER_DESAPROBADO);
		
	}
	@Override
	public void desaprobarReqDiga(Map<String, Object> request) {
		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
		digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_DESAPROBADO);
		
	}
//	@Override
//	public void registrarReqDiga(Map<String, Object> request) {
//		ReqMesaPartesDto registro=(ReqMesaPartesDto) request.get("requerimiento");
//		digaDao.updateDetalleRequerimiento(registro);
//		digaDao.updateEstadoRequerimiento(registro,Constantes.ESTADO_REQ_DERIVADO);
//	}
	
}
