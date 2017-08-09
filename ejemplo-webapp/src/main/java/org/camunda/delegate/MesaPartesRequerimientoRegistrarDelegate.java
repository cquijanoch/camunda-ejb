package org.camunda.delegate;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.unsa.business.MesaPartesBusiness;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.util.Constantes;


@Named("mesaPartesRequerimientoRegistrarDelegate")
@Stateless
public class MesaPartesRequerimientoRegistrarDelegate implements JavaDelegate {
	
	@EJB
	private MesaPartesBusiness requerimientos;

	private final static Logger LOGGER = Logger.getLogger(MesaPartesRequerimientoRegistrarDelegate.class.getName());

	public void execute(DelegateExecution execution) throws Exception {
		
		Map<String, Object> variables = execution.getVariables();

		if (!variables.containsKey("mutex")) {
			ReqMesaPartesDto registro = (ReqMesaPartesDto) variables.get("requerimiento");

			requerimientos.registrarReqMesaPartes(variables);
			requerimientos.derivarReqMesaPartes(variables);
			execution.setVariable("mutex", false);
		}
  }

}

