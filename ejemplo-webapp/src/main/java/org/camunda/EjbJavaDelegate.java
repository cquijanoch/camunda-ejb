
package org.camunda;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.unsa.business.Foo;

/**
 * This is an Ejb which is invoked form a Service task
 *
 */
@Named("exampleBean")
@Stateless
public class EjbJavaDelegate implements JavaDelegate {

  private final static Logger LOGGER = Logger.getLogger(EjbJavaDelegate.class.getName());
  
  @EJB
  private Foo foo;
  public void execute(DelegateExecution execution) throws Exception {

    LOGGER.info("\n\n\n This is a @Stateless Ejb component invoked from a BPMN 2.0 process \n\n\n");
    LOGGER.info("\n\n\n"+foo.decirOtraCosa()+" \n\n\n");

  }

}
