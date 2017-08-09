package org.unsa.message;

import java.util.ArrayList;
import java.util.List;

import org.unsa.dto.UserTaskDto;

public class GetTaskDto <T>{
	private List<UserTaskDto<T>> tasks;

	public List<UserTaskDto<T>> getTask() {
		return tasks;
	}

	public void setTask(List<UserTaskDto<T>> tasks) {
		this.tasks = tasks;
	}
	
	public void setTask(UserTaskDto<T> task){
		if(tasks ==null){
			tasks = new ArrayList<UserTaskDto<T>>();
		}
		
		tasks.add(task);
	}
	
}