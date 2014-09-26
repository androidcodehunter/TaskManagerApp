package com.manager.task.views;

import com.manager.task.tasks.Task;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

public class TaskListItem extends LinearLayout {

	private Task task;
	private CheckedTextView checkBox;

	public TaskListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		checkBox = (CheckedTextView) findViewById(android.R.id.text1);
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
		checkBox.setText(task.getName());
		checkBox.setChecked(task.isComplete());
	}

}
