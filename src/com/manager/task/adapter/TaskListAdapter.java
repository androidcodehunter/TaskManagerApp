package com.manager.task.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.manager.task.R;
import com.manager.task.tasks.Task;
import com.manager.task.views.TaskListItem;

public class TaskListAdapter extends BaseAdapter {

	private ArrayList<Task> tasks;
	private Context context;

	public TaskListAdapter(ArrayList<Task> tasks, Context context) {
		super();
		this.tasks = tasks;
		this.context = context;
	}

	@Override
	public int getCount() {
		return tasks.size();
	}

	@Override
	public Task getItem(int position) {
		return tasks == null ? null : tasks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TaskListItem tli;
		if (convertView == null) {
			tli = (TaskListItem) View.inflate(context, R.layout.task_list_item,
					null);
		} else {
			tli = (TaskListItem) convertView;
		}

		tli.setTask(tasks.get(position));
		return tli;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public Long[] removeCompletedTask() {
		ArrayList<Long> completedTasksIds = new ArrayList<Long>();
		ArrayList<Task> completedTasks = new ArrayList<Task>();
		for (Task task : tasks) {
			if (task.isComplete()) {
				completedTasks.add(task);
				completedTasksIds.add(task.getId());
			}
		}

		tasks.removeAll(completedTasks);
		notifyDataSetChanged();
		return completedTasksIds.toArray(new Long[] {});
	}

	public void toggleTaskCompletedAtPositin(int position) {
		Task t = tasks.get(position);
		t.toggleComplete();
		notifyDataSetChanged();
	}

}
