package org.camunda.listener;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.unsa.business.UsuarioBusiness;
import org.unsa.dto.ReqMesaPartesDto;


@Named("usuarioRequerimientoListener")
@Stateless
public class UsuarioRequerimientoListener implements TaskListener {
	
	@EJB
	private UsuarioBusiness usuarioBusiness;

	private final static Logger LOGGER = Logger.getLogger(UsuarioRequerimientoListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		
		Map<String,Object> variables =delegateTask.getVariables();
		ReqMesaPartesDto requerimiento =(ReqMesaPartesDto)usuarioBusiness.saveRequerimiento(variables).get("ReqMesaPartesDto") ;
		delegateTask.setVariable("ReqMesaPartesDto", requerimiento);
		
	}

}
