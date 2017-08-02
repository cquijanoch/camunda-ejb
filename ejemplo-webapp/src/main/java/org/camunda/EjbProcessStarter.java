
package org.camunda;

import javax.ejb.DependsOn;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;

/**
 * Ejb which is automatically started when the application is deployed and creates a new process instance
 *
 */
@Startup
@Singleton
@DependsOn("DefaultEjbProcessApplication")
public class EjbProcessStarter {

//  @Inject
//  private RuntimeService runtimeService;

//  @Schedule(hour="*", minute="*")
//  public void startProcessInstance() {
//
//    runtimeService.startProcessInstanceByKey("testResolveBean");
//
//  }

}
