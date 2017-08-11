package org.unsa.dto.camunda;

import org.camunda.bpm.engine.runtime.TransitionInstance;

public class TransitionInstanceDto {
	protected String id;
	protected String parentActivityInstanceId;
	protected String processInstanceId;
	protected String processDefinitionId;
	protected String activityId;
	protected String activityName;
	protected String activityType;
	protected String executionId;

	public String getId() {
		return this.id;
	}

	public String getParentActivityInstanceId() {
		return this.parentActivityInstanceId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	@Deprecated
	public String getTargetActivityId() {
		return this.activityId;
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

	public String getExecutionId() {
		return this.executionId;
	}

	public static TransitionInstanceDto fromTransitionInstance(TransitionInstance instance) {
		TransitionInstanceDto result = new TransitionInstanceDto();
		result.id = instance.getId();
		result.parentActivityInstanceId = instance.getParentActivityInstanceId();
		result.activityId = instance.getActivityId();
		result.activityName = instance.getActivityName();
		result.activityType = instance.getActivityType();
		result.processInstanceId = instance.getProcessInstanceId();
		result.processDefinitionId = instance.getProcessDefinitionId();
		result.executionId = instance.getExecutionId();
		return result;
	}

	public static TransitionInstanceDto[] fromListOfTransitionInstance(TransitionInstance[] instances) {
		TransitionInstanceDto[] result = new TransitionInstanceDto[instances.length];
		for (int i = 0; i < result.length; ++i) {
			result[i] = fromTransitionInstance(instances[i]);
		}
		return result;
	}
}
