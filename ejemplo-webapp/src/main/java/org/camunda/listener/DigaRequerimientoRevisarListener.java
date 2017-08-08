package org.camunda.listener;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;


@Named("digaRequerimientoRevisarListener")
@Stateless
public class DigaRequerimientoRevisarListener implements TaskListener {

	private final static Logger LOGGER = Logger.getLogger(UsuarioRequerimientoListener.class.getName());

	@Override
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub

	}

}