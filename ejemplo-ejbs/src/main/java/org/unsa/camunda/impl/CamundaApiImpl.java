package org.unsa.camunda.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.unsa.camunda.CamundaApi;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.TaskDto;

@Stateless
public class CamundaApiImpl  implements CamundaApi {
	
	
	private RuntimeService runtimeService;
	private TaskService taskService;
	private RepositoryService repositoryService;
	
	@PostConstruct
	public void initialize(){
		ProcessEngine processEngine=BpmPlatform.getProcessEngineService().getProcessEngine("default");
		runtimeService= processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		repositoryService=processEngine.getRepositoryService();
	}

	@Override
	public ProcessDto createProcess(ProcessDto processDto) {
		
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(processDto.getProcessDefinitionKey());
		processDto.setProcessInstanceId(processInstance.getProcessInstanceId());
		processDto.setBusinessKey(processInstance.getBusinessKey());
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processInstance.getProcessDefinitionId())
				.singleResult();
		
		processDto.setProcessName(processDefinition.getName());
		processDto.setProcessDefinitionKey(processDefinition.getId());
		processDto.setProcessKey(processDefinition.getKey());
		
		return processDto;
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
			taskDto.setProcessInstanceId(task.getProcessInstanceId());
			taskDto.setExecutionId(task.getExecutionId());
			taskDtoList.add(taskDto);
		}
		
		return taskDtoList;
	}

	@Override
	public TaskDto completeTask(TaskDto task) {
		runtimeService.setVariables(task.getExecutionId(), task.getVariables());
		taskService.complete(task.getTaskId());
		return task;
	}

	@Override
	public void finishProcess(ProcessDto process) {
		this.runtimeService.deleteProcessInstance(process.getProcessInstanceId(),null);
	}

	@Override
	public List<ProcessDto> getAllInstanceProcess(String processDefinitionKey) {
		List<ProcessInstance> processInstance =this.runtimeService.createProcessInstanceQuery().
				processDefinitionKey(processDefinitionKey)
				.orderByProcessInstanceId()
				.desc()
				.list();
		
		List<ProcessDto> processes = new ArrayList<ProcessDto>();
		for(ProcessInstance index : processInstance){
			
			ProcessDto processDto = new ProcessDto();
			processDto.setBusinessKey(index.getBusinessKey());
			processDto.setProcessInstanceId(index.getProcessInstanceId());
		}
		return processes;
	}

	@Override
	public ProcessDto getProcess(String processInstanceId) {
		ProcessDto processDto = new ProcessDto();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();
		
		processDto.setBusinessKey(processInstance.getBusinessKey());
		processDto.setProcessInstanceId(processInstance.getProcessInstanceId());
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processInstance.getProcessDefinitionId())
				.singleResult();
		
		processDto.setProcessName(processDefinition.getName());
		processDto.setProcessDefinitionKey(processDefinition.getKey());
		
		processDto.setVariables(runtimeService.getVariables(processInstance.getProcessInstanceId()));
		
		return processDto;
	}

	@Override
	public List<TaskDto> getTaskByTaskId(String taskId,String procesdefinitionKey) {
		
		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
		
		TaskQuery query = taskService.createTaskQuery()
				.processDefinitionKey(procesdefinitionKey)
				.taskDefinitionKey(taskId);

		List<Task> tasks = query.list();
		
		for(Task task : tasks){
			
			
			TaskDto taskDto = new TaskDto();
			taskDto.setName(task.getName());
			taskDto.setTaskId(task.getId());
			taskDto.setAssignee(task.getAssignee());
			taskDto.setProcessInstanceId(task.getProcessInstanceId());
			taskDto.setExecutionId(task.getExecutionId());
			taskDto.setVariables(taskService.getVariables(task.getId()));
			taskDto.setProcessDefinitionKey(task.getProcessDefinitionId());
			taskDto.setKey(task.getTaskDefinitionKey());
			taskDtoList.add(taskDto);
			
			
		}
		return taskDtoList;
	}

	@Override
	public ProcessDto getSubprocess(String parentProcessInstanceId, String childProcessKey) {
		ProcessDto processDto = new ProcessDto();
		
		ProcessInstance subProcessInstance =runtimeService.createProcessInstanceQuery()
				.superProcessInstanceId(parentProcessInstanceId)
				.processDefinitionKey(childProcessKey)
				.singleResult();
		
		
		processDto.setBusinessKey(subProcessInstance.getBusinessKey());
		processDto.setProcessInstanceId(subProcessInstance.getProcessInstanceId());
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(subProcessInstance.getProcessDefinitionId())
				.singleResult();
		
		processDto.setProcessDefinitionKey(processDefinition.getName());
		processDto.setProcessDefinitionKey(processDefinition.getKey());
		
		processDto.setVariables(runtimeService.getVariables(subProcessInstance.getProcessInstanceId()));
		
		return processDto;
	}

	@Override
	public List<TaskDto> getActiveTaskByProcessInstance(String processInstanceId) {
		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();

		TaskQuery query = taskService.createTaskQuery().processInstanceId(processInstanceId);

		List<Task> tasks = query.list();

		for (Task task : tasks) {

			TaskDto taskDto = new TaskDto();
			taskDto.setName(task.getName());
			taskDto.setTaskId(task.getId());
			taskDto.setAssignee(task.getAssignee());
			taskDto.setProcessInstanceId(task.getProcessInstanceId());
			taskDto.setExecutionId(task.getExecutionId());
			taskDto.setVariables(taskService.getVariables(task.getId()));
			taskDto.setProcessDefinitionKey(task.getProcessDefinitionId());
			taskDto.setKey(task.getTaskDefinitionKey());
			taskDtoList.add(taskDto);
		}
		return taskDtoList;
	}

	@Override
	public List<TaskDto> getActiveTaskBySubProcess(String parentProcessdefinitionKey,
			String childparentProcessdefinitionKey, String taskId) {

		List<TaskDto> taskDtoList = new ArrayList<TaskDto>();

		TaskQuery query = taskService.createTaskQuery().processDefinitionKey(childparentProcessdefinitionKey)
				.taskDefinitionKey(taskId);

		List<Task> tasks = query.list();

		for (Task task : tasks) {

			TaskDto taskDto = new TaskDto();
			taskDto.setName(task.getName());
			taskDto.setTaskId(task.getId());
			taskDto.setAssignee(task.getAssignee());
			taskDto.setProcessInstanceId(task.getProcessInstanceId());
			taskDto.setExecutionId(task.getExecutionId());
			taskDto.setVariables(taskService.getVariables(task.getId()));
			taskDto.setProcessDefinitionKey(task.getProcessDefinitionId());
			taskDto.setKey(task.getTaskDefinitionKey());
			taskDtoList.add(taskDto);
		}
		return taskDtoList;

	}

}
