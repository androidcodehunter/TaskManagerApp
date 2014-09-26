package com.manager.task;

import java.util.Calendar;
import com.manager.task.receiver.TaskAlarmService;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AddDateTime {
	private Context context;
	private Dialog dialog;
	private DatePicker datep;
	private TimePicker timep;
	private Button set;
	private int month, day, year;
	private int hour, minute;
	private int AM_PM;

	public AddDateTime(Context context) {
		this.context = context;
	}

	public void setAlarm() {
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.data_time);

		dialog.setTitle("Select Date and Time");

		datep = (DatePicker) dialog.findViewById(R.id.datePicker);
		timep = (TimePicker) dialog.findViewById(R.id.timePicker1);
		set = (Button) dialog.findViewById(R.id.btnSet);

		set.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				month = datep.getMonth();
				day = datep.getDayOfMonth();
				year = datep.getYear();
				hour = timep.getCurrentHour();
				minute = timep.getCurrentMinute();

				Log.e("date result", "moth" + month + "day" + day + "year"
						+ year);
				
				if (hour < 12) {
					AM_PM = Calendar.AM;
				} else {
					AM_PM = Calendar.PM;
				}

				dialog.dismiss();

				Calendar calendar = Calendar.getInstance();

				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DAY_OF_MONTH, day);

				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.AM_PM, AM_PM);

				AlarmManager alarmMgr = (AlarmManager) context
						.getSystemService(Context.ALARM_SERVICE);
				Intent intent = new Intent(context, TaskAlarmService.class);

				int uniqueValue = (int) System.currentTimeMillis();
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						context, uniqueValue, intent, 0);

				alarmMgr.set(AlarmManager.RTC_WAKEUP,
						calendar.getTimeInMillis(), pendingIntent);

			}
		});

		dialog.show();

	}

}
