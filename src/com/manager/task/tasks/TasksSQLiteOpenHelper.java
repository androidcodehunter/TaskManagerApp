package com.manager.task.tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "tasks_db.sqlite";
	public static final int VERSION = 1;
	public static final String TABLE_NAME = "tasks";
	public static final String TASK_ID = "id";
	public static final String TASK_NAME = "name";
	public static final String TASK_COMPLETE = "complete";

	public TasksSQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// currently unused
	}

	private void createTable(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + TASK_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + TASK_NAME
				+ " TEXT, " + TASK_COMPLETE + " TEXT " + ");");
	}

}
