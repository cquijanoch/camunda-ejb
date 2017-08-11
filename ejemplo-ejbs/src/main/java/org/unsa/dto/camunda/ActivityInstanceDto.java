package org.unsa.dto.camunda;

import org.camunda.bpm.engine.runtime.ActivityInstance;

public class ActivityInstanceDto {
	protected String id;
	protected String parentActivityInstanceId;
	protected String activityId;
	protected String activityType;
	protected String processInstanceId;
	protected String processDefinitionId;
	protected ActivityInstanceDto[] childActivityInstances;
	protected TransitionInstanceDto[] childTransitionInstances;
	protected String[] executionIds;
	protected String activityName;

	public String getId() {
		return this.id;
	}

	public String getParentActivityInstanceId() {
		return this.parentActivityInstanceId;
	}

	public String getActivityId() {
		return this.activityId;
	}

	public String getActivityType() {
		return this.activityType;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public ActivityInstanceDto[] getChildActivityInstances() {
		return this.childActivityInstances;
	}

	public TransitionInstanceDto[] getChildTransitionInstances() {
		return this.childTransitionInstances;
	}

	public String[] getExecutionIds() {
		return this.executionIds;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public String getName() {
		return this.activityName;
	}

	public static ActivityInstanceDto fromActivityInstance(ActivityInstance instance) {
		ActivityInstanceDto result = new ActivityInstanceDto();
		result.id = instance.getId();
		result.parentActivityInstanceId = instance.getParentActivityInstanceId();
		result.activityId = instance.getActivityId();
		result.activityType = instance.getActivityType();
		result.processInstanceId = instance.getProcessInstanceId();
		result.processDefinitionId = instance.getProcessDefinitionId();
		result.childActivityInstances = fromListOfActivityInstance(instance.getChildActivityInstances());
		result.childTransitionInstances = TransitionInstanceDto
				.fromListOfTransitionInstance(instance.getChildTransitionInstances());
		result.executionIds = instance.getExecutionIds();
		result.activityName = instance.getActivityName();
		return result;
	}

	public static ActivityInstanceDto[] fromListOfActivityInstance(ActivityInstance[] instances) {
		ActivityInstanceDto[] result = new ActivityInstanceDto[instances.length];
		for (int i = 0; i < result.length; ++i) {
			result[i] = fromActivityInstance(instances[i]);
		}
		return result;
	}
}