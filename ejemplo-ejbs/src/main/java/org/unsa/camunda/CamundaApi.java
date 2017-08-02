package org.unsa.camunda;

import java.util.List;

import javax.ejb.Local;

import org.unsa.dto.ProcessDto;
import org.unsa.dto.TaskDto;

@Local
public interface CamundaApi {
	
	ProcessDto createProcess(ProcessDto process);
	ProcessDto finishProcess(ProcessDto process);
	List<TaskDto> getTaskByProcessInstance(ProcessDto process);
	List<TaskDto> completeTask(TaskDto task);
	

}
