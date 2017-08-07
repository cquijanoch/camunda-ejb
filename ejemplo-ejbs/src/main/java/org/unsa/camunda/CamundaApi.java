package org.unsa.camunda;

import java.util.List;

import javax.ejb.Local;

import org.unsa.dto.ProcessDto;
import org.unsa.dto.TaskDto;

@Local
public interface CamundaApi {
	
	ProcessDto createProcess(ProcessDto process);
	void finishProcess(ProcessDto process);
	List<TaskDto> getTaskByProcessInstance(ProcessDto process);
	List<TaskDto> getTaskByTaskId(String taskId,String procesdefinitionKey);
	TaskDto completeTask(TaskDto task);
	List<ProcessDto> getAllInstanceProcess(String processDefinitionKey);
	ProcessDto getProcess(String processInstanceId);
	ProcessDto getSubprocess(String parentProcess,String childProcess);
	

}
