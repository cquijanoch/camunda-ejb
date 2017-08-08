package org.camunda.listener;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.unsa.dto.RequerimientoDto;


@Named("usuarioRequerimientoListener")
@Stateless
public class UsuarioRequerimientoListener implements TaskListener {

	private final static Logger LOGGER = Logger.getLogger(UsuarioRequerimientoListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		Map<String,Object> variables =delegateTask.getVariables();
		RequerimientoDto requerimiento = (RequerimientoDto)variables.get("RequerimientoDto");
		variables.put("RequerimientoDto", requerimiento);
		delegateTask.setVariables(variables);
	}

}
