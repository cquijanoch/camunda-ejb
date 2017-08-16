package org.unsa.dto.camunda;

import java.util.List;
import java.util.Map;

public class ProcessDto {
	
	private String businessKey;
	private String processInstanceId;
	private String processKey;
	private String processDefinitionKey;
	private String processName;
	
	private List<TaskDto> taskList;
	private Map<String,Object> variables;

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public List<TaskDto> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskDto> taskList) {
		this.taskList = taskList;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}



	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	
	public void setVariable(String key,Object value){
		this.variables.put(key, value);
	}
	
	
}