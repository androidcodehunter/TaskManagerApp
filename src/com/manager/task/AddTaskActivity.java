package com.manager.task;

import com.manager.task.tasks.Task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends Activity {
	private EditText taskNameEditText;
	private Button addButton;
	private Button cancelButton;
	private Button addLocationButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tasks);

		setUpViews();
	}

	private void addTask() {

		String taskName = taskNameEditText.getText().toString();
		Task task = new Task(taskName);

		TaskManagerApplication tma = (TaskManagerApplication) getApplication();
		tma.addTask(task);

		finish();

	}

	private void cancel() {
		finish();
	}

	private void setUpViews() {
		taskNameEditText = (EditText) findViewById(R.id.task_name);
		addButton = (Button) findViewById(R.id.add_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);
		addLocationButton = (Button) findViewById(R.id.add_location_button);

		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addTask();
			}

		});

		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cancel();
			}

		});

		addLocationButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(AddTaskActivity.this,
						AddLocationMapActivity.class));
			}
		});
	}

}
