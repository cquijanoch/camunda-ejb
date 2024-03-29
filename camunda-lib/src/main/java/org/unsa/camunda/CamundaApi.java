package org.unsa.camunda;

import java.util.List;

import javax.ejb.Local;


import org.unsa.dto.camunda.ActivityInstanceDto;
import org.unsa.dto.camunda.GroupCamundaDto;
import org.unsa.dto.camunda.HistoricActivityInstanceDto;
import org.unsa.dto.camunda.HistoricProcessInstanceDto;
import org.unsa.dto.camunda.ProcessDefinitionDiagramDto;
import org.unsa.dto.camunda.ProcessDto;
import org.unsa.dto.camunda.TaskDto;
import org.unsa.dto.camunda.UserCamundaDto;

@Local
public interface CamundaApi {
	
	ProcessDto createProcess(ProcessDto process);

	void finishProcess(ProcessDto process);

	List<TaskDto> getTaskByProcessInstance(ProcessDto process);

	List<TaskDto> getTaskByTaskId(String taskId, String procesdefinitionKey);

	List<TaskDto> getActiveTaskByProcessInstance(String processInstanceId);

	TaskDto completeTask(TaskDto task);

	List<ProcessDto> getAllInstanceProcess(String processDefinitionKey);

	ProcessDto getProcess(String processInstanceId);

	ProcessDto getSubprocess(String parentProcess, String childProcess);

	List<TaskDto> getActiveTaskBySubProcess(String parentProcessdefinitionKey, String childparentProcessdefinitionKey,String taskId);

	boolean verificarPassword(UserCamundaDto user);

	UserCamundaDto createUser(UserCamundaDto user);

	void deleteUser(String idUser);

	GroupCamundaDto createGroup(GroupCamundaDto group);

	void deleteGroup(String idGroup);
	
	ProcessDefinitionDiagramDto getProcessDefinitionBpmn20Xml(String processDefinitionId);
	
	HistoricProcessInstanceDto getHistoricProcessInstance(String processInstanceId);
	
	List<HistoricActivityInstanceDto> getHistoricActivityInstance(String processInstanceId);
	
	 ActivityInstanceDto getActivityInstanceTree(String processInstanceId) ;
}
