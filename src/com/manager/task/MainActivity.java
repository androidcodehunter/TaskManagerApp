package com.manager.task;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.manager.task.adapter.TaskListAdapter;
import com.manager.task.tasks.Task;

public class MainActivity extends ListActivity {

	private Button addButton;
	private TaskManagerApplication app;
	private TaskListAdapter adapter;
	private Button removeButton;

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		adapter.toggleTaskCompletedAtPositin(position);

		Task t = adapter.getItem(position);
		app.saveTask(t);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setUpViews();

		app = (TaskManagerApplication) getApplication();

		adapter = new TaskListAdapter(app.getCurrentTasks(), this);
		setListAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

		adapter.forceReload();
	}

	private void setUpViews() {
		addButton = (Button) findViewById(R.id.add_button);

		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this,
						AddTaskActivity.class));
			}
		});

		removeButton = (Button) findViewById(R.id.remove_button);
		removeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Long[] ids = adapter.removeCompletedTask();

				app.removeCompletedTasks(ids);

			}
		});
	}
}
