package com.manager.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.manager.task.tasks.User;

public class SignUpActivity extends Activity implements
		android.widget.CompoundButton.OnCheckedChangeListener {
	private Button signUpButton;
	private EditText etMobile;
	private EditText etEmail;
	private Switch alertService, alarmService, smsService, mailService;
	private int isSmsService = 0, isAlertService = 1, isMailService = 0,
			isAlarmService = 1;
	protected String phoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		setUpViews();

		signUpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				phoneNumber = "88" + etMobile.getText().toString();

				if (!isEmailValid(etEmail.getText().toString())) {
					Toast.makeText(SignUpActivity.this,
							"Your email is not valid", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (!phoneNumber.matches("8801[0-9]{9}")) {
					Toast.makeText(SignUpActivity.this,
							"Your Phone is not valid, Please starts with 88",
							Toast.LENGTH_SHORT).show();
					return;
				}

				User user = new User();
				setUserData(user);

				TaskManagerApplication tma = (TaskManagerApplication) getApplication();
				tma.addUser(user);
				Toast.makeText(SignUpActivity.this,
						"SignUp Completed Successfully", Toast.LENGTH_SHORT)
						.show();

				SharedPreferences preferences = PreferenceManager
						.getDefaultSharedPreferences(SignUpActivity.this);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putBoolean("IS_SIGNIN", true);
				editor.commit();

				finish();

				startActivity(new Intent(SignUpActivity.this,
						MainActivity.class));
			}

		});
	}

	public static boolean isEmailValid(String email) {
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	private void setUserData(User user) {
		user.setMobileNumber(phoneNumber);
		user.setEmail(etEmail.getText().toString());
		user.setAlarmService(isAlarmService);
		user.setAlertService(isAlertService);
		user.setMailService(isMailService);
		user.setSmsService(isSmsService);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.alarm_service:
			isAlarmService = 0;
			break;
		case R.id.alert_service:
			isAlertService = 0;
			break;
		case R.id.sms_service:
			isSmsService = 1;
			break;
		case R.id.mail_service:
			isMailService = 1;
			break;
		}
	}

	private void setUpViews() {
		signUpButton = (Button) findViewById(R.id.bt_sign_up);
		etMobile = (EditText) findViewById(R.id.et_mobile);
		etEmail = (EditText) findViewById(R.id.et_email);
		alarmService = (Switch) findViewById(R.id.alarm_service);
		smsService = (Switch) findViewById(R.id.sms_service);
		mailService = (Switch) findViewById(R.id.mail_service);
		alertService = (Switch) findViewById(R.id.alert_service);

		alarmService.setOnCheckedChangeListener(this);
		smsService.setOnCheckedChangeListener(this);
		mailService.setOnCheckedChangeListener(this);
		alertService.setOnCheckedChangeListener(this);

	}

}
