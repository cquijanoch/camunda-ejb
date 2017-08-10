package org.camunda.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.unsa.business.DigaBusiness;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.util.Constantes;

@Named("digaUsuarioValidarListener")
@Stateless
public class DigaUsuarioValidarListener implements TaskListener {

	@EJB
	private DigaBusiness requerimientos;
	private final static Logger LOGGER = Logger.getLogger(UsuarioRequerimientoListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		
		ReqMesaPartesDto requerimiento = (ReqMesaPartesDto)delegateTask.getVariable("ReqMesaPartesDto");
		
		Map<String,Object> request=new HashMap<String, Object>();
		request.put("requerimiento", requerimiento);
		
		boolean estado = false;
		if(requerimiento.getEstado().equals("true")){
			requerimientos.revisarAprobadoUsuarioDiga(request);
			estado = true;
		}
		
		else{
			requerimientos.revisarDesaprobadoUsuarioDiga(request);
		}
		delegateTask.setVariable("validado",estado);

	}

}

