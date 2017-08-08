package org.camunda.delegate;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


@Named("mesaPartesRequerimientoRegistrarDelegate")
@Stateless
public class MesaPartesRequerimientoRegistrarDelegate implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger(MesaPartesRequerimientoRegistrarDelegate.class.getName());
  
  public void execute(DelegateExecution execution) throws Exception {

    LOGGER.info("\n\n\n This is a @Stateless Ejb component invoked from a BPMN 2.0 process \n\n\n");
    LOGGER.info("\n\n\n"+" \n\n\n");

  }

}

