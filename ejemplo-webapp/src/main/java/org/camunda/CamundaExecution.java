package org.camunda;

import javax.ejb.EJB;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.unsa.camunda.CamundaApi;
import org.unsa.dto.camunda.ProcessDto;

public abstract class CamundaExecution implements JavaDelegate {
	
	@EJB
	CamundaApi camundaApi;
	
	 public abstract Object execute(ProcessDto processDto);
	
	 public void execute(DelegateExecution execution) throws Exception {
		 String executionId= execution.getProcessInstanceId();
		 String activityName=execution.getCurrentActivityName();
		 ProcessDto processDto = camundaApi.getProcess(executionId);
		 processDto.setVariables(execution.getVariables());
		 
		 Object result= execute(processDto);
		 
		 if(result!=null)
			 execution.setVariable(activityName,result );
		 
	 }
	 
	

}
