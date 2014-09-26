package com.manager.task;

import static com.manager.task.tasks.TasksSQLiteOpenHelper.*;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.manager.task.tasks.TasksSQLiteOpenHelper;

public class SettingActivity extends Activity implements
		OnCheckedChangeListener {
	private SQLiteDatabase database;
	private EditText etMobile;
	private EditText etMail;
	private Switch alertSerice, alarmService, smsService, mailService;
	private boolean isAlertService, isAlarmService, isSmsService,
			isMailService;
	private Button updateButton;
	private TasksSQLiteOpenHelper helper;
	private String mobileNumber;
	private String userEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);

		setUpViews();

		helper = new TasksSQLiteOpenHelper(SettingActivity.this);
		database = helper.getWritableDatabase();

		loadUserInfo();

		alertSerice.setChecked(isAlertService);
		alarmService.setChecked(isAlarmService);
		smsService.setChecked(isSmsService);
		mailService.setChecked(isMailService);
		etMobile.setText(mobileNumber);
		etMail.setText(userEmail);
	}

	private void loadUserInfo() {
		Cursor cursor = database.query(USER_TABLE, new String[] { USER_ID,
				MOBILE_NUMBER, EMAIL, ALERT_SERVICE, ALARM_SERVICE,
				SMS_SERVICE, MAIL_SERVICE }, null, null, null, null, null);

		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			do {
				mobileNumber = cursor.getString(1);
				userEmail = cursor.getString(2);
				isAlertService = cursor.getInt(3) != 0;
				isAlarmService = cursor.getInt(4) != 0;
				isSmsService = cursor.getInt(5) != 0;
				isMailService = cursor.getInt(6) != 0;
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void setUpViews() {
		etMobile = (EditText) findViewById(R.id.et_mobile);
		etMail = (EditText) findViewById(R.id.et_mail);
		alertSerice = (Switch) findViewById(R.id.alert_service);
		alarmService = (Switch) findViewById(R.id.alarm_service);
		smsService = (Switch) findViewById(R.id.sms_service);
		mailService = (Switch) findViewById(R.id.mail_service);
		alertSerice.setOnCheckedChangeListener(this);
		alarmService.setOnCheckedChangeListener(this);
		smsService.setOnCheckedChangeListener(this);
		mailService.setOnCheckedChangeListener(this);

		updateButton = (Button) findViewById(R.id.bt_update);
		updateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put(MOBILE_NUMBER, etMobile.getText().toString());
				values.put(EMAIL, etMail.getText().toString());
				values.put(ALERT_SERVICE, isAlertService);
				values.put(ALARM_SERVICE, isAlarmService);
				values.put(SMS_SERVICE, isSmsService);
				values.put(MAIL_SERVICE, isMailService);
				database.update(USER_TABLE, values, null, null);

				Toast.makeText(SettingActivity.this,
						"Your Info is Updated Successfully", Toast.LENGTH_SHORT)
						.show();
				finish();
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.alert_service:
			isAlertService = isChecked;
			break;
		case R.id.alarm_service:
			isAlarmService = isChecked;
			break;
		case R.id.sms_service:
			isSmsService = isChecked;
			break;
		case R.id.mail_service:
			isMailService = isChecked;
			break;
		}

	}
}
