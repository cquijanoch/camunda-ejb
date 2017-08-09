package org.camunda.listener;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.unsa.business.MesaPartesBusiness;
import org.unsa.dto.ReqMesaPartesDto;
import org.unsa.util.Constantes;

@Named("mesaPartesRequerimientoRevisarListener")
@Stateless
public class MesaPartesRequerimientoRevisarListener implements TaskListener  {
	
	@EJB
	private MesaPartesBusiness requerimientos;

	private final static Logger LOGGER = Logger.getLogger(MesaPartesRequerimientoRevisarListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		
		Map<String,Object> variables =delegateTask.getVariables();
		ReqMesaPartesDto registro=(ReqMesaPartesDto) variables.get("requerimiento");
		
		if(registro.getEstado().equals(Constantes.ESTADO_REQ_APROBADO)){
			requerimientos.aprobarReqMesaPartes(variables);
			variables.put("completed", true);
		}
		
		else{
			requerimientos.desaprobarReqMesaPartes(variables);
		};
		
	}
}

