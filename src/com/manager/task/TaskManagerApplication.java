package com.manager.task;

import java.util.ArrayList;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.manager.task.tasks.Task;
import com.manager.task.tasks.TasksSQLiteOpenHelper;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.*;

public class TaskManagerApplication extends Application {
	private ArrayList<Task> currentTasks;
	private SQLiteDatabase database;

	@Override
	public void onCreate() {
		super.onCreate();

		if (currentTasks == null) {
			TasksSQLiteOpenHelper helper = new TasksSQLiteOpenHelper(this);
			database = helper.getWritableDatabase();
			loadTasks();
		}
	}

	private void loadTasks() {
		currentTasks = new ArrayList<Task>();

		Cursor tasksCursor = database.query(TABLE_NAME, new String[] { TASK_ID,
				TASK_NAME, TASK_COMPLETE }, null, null, null, null,
				String.format("%s,%s", TASK_COMPLETE, TASK_NAME));

		tasksCursor.moveToFirst();
		Task t;

		if (!tasksCursor.isAfterLast()) {
			do {
				long taskId = tasksCursor.getLong(0);
				String taskName = tasksCursor.getString(1);
				String boolValue = tasksCursor.getString(2);
				boolean complete = Boolean.parseBoolean(boolValue);

				t = new Task(taskName);
				t.setId(taskId);
				t.setComplete(complete);

				currentTasks.add(t);

			} while (tasksCursor.moveToNext());
		}
		tasksCursor.close();
	}

	public ArrayList<Task> getCurrentTasks() {
		return currentTasks;
	}

	public void setCurrentTasks(ArrayList<Task> currentTasks) {
		this.currentTasks = currentTasks;
	}

	public void addTask(Task t) {

		ContentValues values = new ContentValues();
		values.put(TASK_NAME, t.getName());
		values.put(TASK_COMPLETE, Boolean.toString(t.isComplete()));
		long id = database.insert(TABLE_NAME, null, values);
		t.setId(id);

		currentTasks.add(t);
	}

	public void saveTask(Task t) {
		ContentValues values = new ContentValues();
		values.put(TASK_NAME, t.getName());
		values.put(TASK_COMPLETE, t.isComplete());
		long id = t.getId();
		String where = String.format("%s=%d", TASK_ID, id);
		database.update(TABLE_NAME, values, where, null);
	}

	public void removeCompletedTasks(Long[] ids) {

		StringBuffer bufferIds = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			bufferIds.append(ids[i]);
			if (i < ids.length - 1) {
				bufferIds.append(",");
			}
		}

		String where = String.format("%s in (%s)", TASK_ID, bufferIds);
		database.delete(TABLE_NAME, where, null);

	}

}
