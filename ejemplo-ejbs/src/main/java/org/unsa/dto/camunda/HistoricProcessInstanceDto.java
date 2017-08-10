package org.unsa.dto.camunda;

import java.util.Date;

import org.camunda.bpm.engine.history.HistoricProcessInstance;

public class HistoricProcessInstanceDto {
	private String id;
	private String businessKey;
	private String processDefinitionId;
	private String processDefinitionKey;
	private Date startTime;
	private Date endTime;
	private Long durationInMillis;
	private String startUserId;
	private String startActivityId;
	private String deleteReason;
	private String superProcessInstanceId;
	private String superCaseInstanceId;
	private String caseInstanceId;

	public String getId() {
		return this.id;
	}

	public String getBusinessKey() {
		return this.businessKey;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public String getProcessDefinitionKey() {
		return this.processDefinitionKey;
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

	public String getStartUserId() {
		return this.startUserId;
	}

	public String getStartActivityId() {
		return this.startActivityId;
	}

	public String getDeleteReason() {
		return this.deleteReason;
	}

	public String getSuperProcessInstanceId() {
		return this.superProcessInstanceId;
	}

	public String getSuperCaseInstanceId() {
		return this.superCaseInstanceId;
	}

	public String getCaseInstanceId() {
		return this.caseInstanceId;
	}

	public static HistoricProcessInstanceDto fromHistoricProcessInstance(
			HistoricProcessInstance historicProcessInstance) {
		HistoricProcessInstanceDto dto = new HistoricProcessInstanceDto();

		dto.id = historicProcessInstance.getId();
		dto.businessKey = historicProcessInstance.getBusinessKey();
		dto.processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		dto.processDefinitionKey = historicProcessInstance.getProcessDefinitionKey();
		dto.startTime = historicProcessInstance.getStartTime();
		dto.endTime = historicProcessInstance.getEndTime();
		dto.durationInMillis = historicProcessInstance.getDurationInMillis();
		dto.startUserId = historicProcessInstance.getStartUserId();
		dto.startActivityId = historicProcessInstance.getStartActivityId();
		dto.deleteReason = historicProcessInstance.getDeleteReason();
		dto.superProcessInstanceId = historicProcessInstance.getSuperProcessInstanceId();
		dto.superCaseInstanceId = historicProcessInstance.getSuperCaseInstanceId();
		dto.caseInstanceId = historicProcessInstance.getCaseInstanceId();

		return dto;
	}
}