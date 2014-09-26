package com.manager.task.tasks;

import java.io.Serializable;

public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private boolean complete;

	private long id;

	public Task(String taskName) {
		name = taskName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return name;
	}

	public void toggleComplete() {
		complete = !complete;
	}

	public void setId(long taskId) {
		this.id = taskId;
	}

	public long getId() {
		return id;
	}
}
