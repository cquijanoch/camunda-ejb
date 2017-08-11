package org.unsa.dto.camunda;

import java.util.Date;

import org.camunda.bpm.engine.history.HistoricActivityInstance;

public class HistoricActivityInstanceDto {
	private String id;
	private String parentActivityInstanceId;
	private String activityId;
	private String activityName;
	private String activityType;
	private String processDefinitionKey;
	private String processDefinitionId;
	private String processInstanceId;
	private String executionId;
	private String taskId;
	private String calledProcessInstanceId;
	private String calledCaseInstanceId;
	private String assignee;
	private Date startTime;
	private Date endTime;
	private Long durationInMillis;
	private Boolean canceled;
	private Boolean completeScope;

	public String getId() {
		return this.id;
	}

	public String getParentActivityInstanceId() {
		return this.parentActivityInstanceId;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public String getActivityType() {
		return this.activityType;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public String getExecutionId() {
		return this.executionId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public String getCalledProcessInstanceId() {
		return this.calledProcessInstanceId;
	}

	public String getCalledCaseInstanceId() {
		return this.calledCaseInstanceId;
	}

	public String getAssignee() {
		return this.assignee;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public Long getDurationInMillis() {
		return this.durationInMillis;
	}

	public Boolean getCanceled() {
		return this.canceled;
	}

	public Boolean getCompleteScope() {
		return this.completeScope;
	}

	public static HistoricActivityInstanceDto fromHistoricActivityInstance(
			HistoricActivityInstance historicActivityInstance) {
		HistoricActivityInstanceDto dto = new HistoricActivityInstanceDto();

		dto.id = historicActivityInstance.getId();
		dto.parentActivityInstanceId = historicActivityInstance.getParentActivityInstanceId();
		dto.activityId = historicActivityInstance.getActivityId();
		dto.activityName = historicActivityInstance.getActivityName();
		dto.activityType = historicActivityInstance.getActivityType();
		dto.processDefinitionKey = historicActivityInstance.getProcessDefinitionKey();
		dto.processDefinitionId = historicActivityInstance.getProcessDefinitionId();
		dto.processInstanceId = historicActivityInstance.getProcessInstanceId();
		dto.executionId = historicActivityInstance.getExecutionId();
		dto.taskId = historicActivityInstance.getTaskId();
		dto.calledProcessInstanceId = historicActivityInstance.getCalledProcessInstanceId();
		dto.calledCaseInstanceId = historicActivityInstance.getCalledCaseInstanceId();
		dto.assignee = historicActivityInstance.getAssignee();
		dto.startTime = historicActivityInstance.getStartTime();
		dto.endTime = historicActivityInstance.getEndTime();
		dto.durationInMillis = historicActivityInstance.getDurationInMillis();
		dto.canceled = Boolean.valueOf(historicActivityInstance.isCanceled());
		dto.completeScope = Boolean.valueOf(historicActivityInstance.isCompleteScope());

		return dto;
	}
}
