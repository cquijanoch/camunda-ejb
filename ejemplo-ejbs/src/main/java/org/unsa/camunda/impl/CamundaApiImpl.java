package org.unsa.camunda.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.unsa.camunda.CamundaApi;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.TaskDto;

@Stateless
public class CamundaApiImpl implements CamundaApi {
	
	
	private RuntimeService runtimeService;
	
	
	private TaskService taskService;
	
	@PostConstruct
	public void initialize(){
		ProcessEngine processEngine=BpmPlatform.getProcessEngineService().getProcessEngine("default");
		runtimeService= processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
	}

	@Override
	public ProcessDto createProcess(ProcessDto process) {
		
		
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(process.getProcessDefinitionKey());
		process.setProcessInstanceId(processInstance.getProcessInstanceId());
		return process;
	}

	@Override
	public List<TaskDto> getTaskByProcessInstance(ProcessDto process) {
		
		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
		
		TaskQuery query = taskService.createTaskQuery()
				.processInstanceId(process.getProcessInstanceId())
				.orderByTaskName()
				.asc();

		List<Task> tasks = query.list();
		
		for(Task task : tasks){
			
			TaskDto taskDto = new TaskDto();
			taskDto.setName(task.getName());
			taskDto.setTaskId(task.getId());
			taskDto.setAssignee(task.getAssignee());
		}
		return null;
	}

	@Override
	public List<TaskDto> completeTask(TaskDto task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessDto finishProcess(ProcessDto process) {
		
		return null;
	}
	
	

}
