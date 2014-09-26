package com.manager.task.receiver;

import static com.manager.task.tasks.TasksSQLiteOpenHelper.ALARM_SERVICE;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.ALERT_SERVICE;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.EMAIL;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.MAIL_SERVICE;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.MOBILE_NUMBER;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.SMS_SERVICE;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.USER_ID;
import static com.manager.task.tasks.TasksSQLiteOpenHelper.USER_TABLE;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.telephony.gsm.SmsManager;
import android.util.Log;

import com.manager.task.R;
import com.manager.task.tasks.TasksSQLiteOpenHelper;

public class TaskAlarmService extends BroadcastReceiver {

	private NotificationManager nm;
	private SQLiteDatabase database;
	private String mobileNumber;
	private String userEmail;
	private int alertService;
	private int smsService;
	private int alarmService;

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {

		TasksSQLiteOpenHelper helper = new TasksSQLiteOpenHelper(context);
		database = helper.getWritableDatabase();

		Cursor cursor = databaseSetUpAndGetValue();

		cursor.close();
		database.close();

		if (alertService == 1) {
			alertService(context);
		}
		if (alarmService == 1) {
			alarmService(context);
		}
		if (smsService == 1) {
			smsService();
		}

	}

	private Cursor databaseSetUpAndGetValue() {
		Cursor cursor = database.query(USER_TABLE, new String[] { USER_ID,
				MOBILE_NUMBER, EMAIL, ALERT_SERVICE, SMS_SERVICE, MAIL_SERVICE,
				ALARM_SERVICE }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				mobileNumber = cursor.getString(1);
				userEmail = cursor.getString(2);
				alertService = cursor.getInt(3);
				smsService = cursor.getInt(4);
				alarmService = cursor.getInt(6);

				Log.e("please print the number ", "mobile " + mobileNumber
						+ "userEmail " + userEmail + "alertservice "
						+ alertService + "smsService " + smsService
						+ "alarm serviec " + alarmService);
			} while (cursor.moveToNext());
		}
		return cursor;
	}

	private void smsService() {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(
				mobileNumber,
				null,
				"Hello Sir, You have an important task. Please check your task from task manager app",
				null, null);
	}

	private void alertService(Context context) {
		nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		CharSequence from = "Your";
		CharSequence message = "Available Tasks Here...";
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(), 0);

		Notification notif = new Notification(R.drawable.ic_launcher,
				"Hey Wake Up Your Task is Available",
				System.currentTimeMillis());
		notif.setLatestEventInfo(context, from, message, contentIntent);
		nm.notify(1, notif);
	}

	private void alarmService(Context context) {
		Vibrator v = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		// for 3 seconds
		long milliseconds = 3000;
		long pattern[] = { 0, milliseconds, 200, 300, 500 };
		v.vibrate(pattern, -1);
	}
}
