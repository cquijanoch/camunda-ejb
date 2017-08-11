package org.unsa.camunda.impl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.AuthorizationException;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.util.IoUtil;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.unsa.camunda.CamundaApi;
import org.unsa.dto.GroupCamundaDto;
import org.unsa.dto.ProcessDto;
import org.unsa.dto.TaskDto;
import org.unsa.dto.UserCamundaDto;
import org.unsa.dto.camunda.ActivityInstanceDto;
import org.unsa.dto.camunda.HistoricActivityInstanceDto;
import org.unsa.dto.camunda.HistoricProcessInstanceDto;
import org.unsa.dto.camunda.ProcessDefinitionDiagramDto;

@Stateless
public class CamundaApiImpl  implements CamundaApi {
	
	
	private RuntimeService runtimeService;
	private TaskService taskService;
	private RepositoryService repositoryService;
	private IdentityService identityService;
	private HistoryService historyService;
	
	@PostConstruct
	public void initialize(){
		ProcessEngine processEngine=BpmPlatform.getProcessEngineService().getProcessEngine("default");
		runtimeService= processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		repositoryService=processEngine.getRepositoryService();
		identityService = processEngine.getIdentityService();
		historyService = processEngine.getHistoryService();
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
	
	@Override
	public boolean verificarPassword(UserCamundaDto user) {
		boolean verificado;
		List<GroupCamundaDto> ListaGrupos = null;

		verificado = identityService.checkPassword(user.getIdUsuario(), user.getPassword());
		if (verificado == true) {
			List<Group> roles = identityService.createGroupQuery().groupMember(user.getIdUsuario()).list();
			ListaGrupos = new ArrayList<GroupCamundaDto>();
			
			for (Group groupIndex : roles) {
				GroupCamundaDto grupo = new GroupCamundaDto();
				grupo.setIdGroup(groupIndex.getId());
				grupo.setNameGroup(groupIndex.getName());
				ListaGrupos.add(grupo);
			}
			user.setGrupos(ListaGrupos);
		}
		return verificado;
	}

	@Override
	public UserCamundaDto createUser(UserCamundaDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String idUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public GroupCamundaDto createGroup(GroupCamundaDto group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGroup(String idGroup) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public ProcessDefinitionDiagramDto getProcessDefinitionBpmn20Xml(String processDefinitionId) {
		InputStream processModelIn = null;
		try {
			processModelIn = this.repositoryService.getProcessModel(processDefinitionId);
			byte[] processModel = IoUtil.readInputStream(processModelIn, "processModelBpmn20Xml");
			ProcessDefinitionDiagramDto localProcessDefinitionDiagramDto = ProcessDefinitionDiagramDto
					.create(processDefinitionId, new String(processModel, "UTF-8"));

			return localProcessDefinitionDiagramDto;
		} catch (AuthorizationException e) {
		} catch (ProcessEngineException e) {
		} catch (UnsupportedEncodingException e) {
		} finally {
			IoUtil.closeSilently(processModelIn);
		}
		return null;
	}

	@Override
	public HistoricProcessInstanceDto getHistoricProcessInstance(String  processInstanceId) {
		HistoricProcessInstance instance = (HistoricProcessInstance) historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		if (instance == null) {
			
		}

		return HistoricProcessInstanceDto.fromHistoricProcessInstance(instance);

	}
	
	@Override
	public List<HistoricActivityInstanceDto> getHistoricActivityInstance(String processInstanceId){
		List<HistoricActivityInstance> instances =  historyService
				.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId)
				.list();
		
		List<HistoricActivityInstanceDto> response= new ArrayList<HistoricActivityInstanceDto>();

		if (instances == null) {
//			throw new InvalidRequestException(Response.Status.NOT_FOUND,
//					"Historic activity instance with id '" + this.activityInstanceId + "' does not exist");
		}
		
		for (HistoricActivityInstance instance : instances){
			response.add(HistoricActivityInstanceDto.fromHistoricActivityInstance(instance));
		}
		

		return  response;
	}

	

	@Override
	public ActivityInstanceDto getActivityInstanceTree(String processInstanceId) {

		ActivityInstance activityInstance = null;
		try {
			activityInstance = runtimeService.getActivityInstance(processInstanceId);
		} catch (AuthorizationException e) {
			throw e;
		} catch (ProcessEngineException e) {
//			throw new InvalidRequestException(Response.Status.INTERNAL_SERVER_ERROR, e, e.getMessage());
		}

		if (activityInstance == null) {
//			throw new InvalidRequestException(Response.Status.NOT_FOUND,
//					"Process instance with id " + processInstanceId + " does not exist");
		}

		ActivityInstanceDto result = ActivityInstanceDto.fromActivityInstance(activityInstance);
		return result;

	}

}
