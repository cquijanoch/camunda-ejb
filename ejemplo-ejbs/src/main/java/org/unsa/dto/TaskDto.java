package org.unsa.dto;

import java.util.HashMap;
import java.util.Map;

public class TaskDto {
	
	private String assignee;
	private String processInstanceId;
	private String processDefinitionKey;
	private String name;
	private String group;
	private String taskId;
	private String key;
	private String executionId;
	private Map<String,Object> variables;
	
	
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	
	public  Object getVariable(String key) {
		if(variables==null)
			return null;
		else
			return variables.get(key);
	}

	public void setVariable(String key,Object value) {
		if(variables==null)
			variables = new HashMap<String,Object>();
		this.variables.put(key, value);
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	
}
