package com.manager.task;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends Activity {
	private Button sendSMS;
	private String message;
	private String phoneNo;
	private EditText phoneNumber;
	private EditText etMessage;
	private Button cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.send_sms);

		setUpViews();

	}

	private void setUpViews() {

		phoneNumber = (EditText) findViewById(R.id.txtPhoneNo);
		etMessage = (EditText) findViewById(R.id.txtMessage);

		sendSMS = (Button) findViewById(R.id.btnSendSMS);
		sendSMS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				phoneNo = "88" + phoneNumber.getText().toString();
				message = etMessage.getText().toString();
				if (phoneNo.matches("8801[0-9]{9}") && message.length() > 0)
					sendSMS(phoneNo, message);

				else
					Toast.makeText(getBaseContext(),
							"Please enter both phone number and message.",
							Toast.LENGTH_SHORT).show();
			}

		});
		
		

		cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void sendSMS(String phoneNo, String message) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNo, null, message, null, null);
	}
}
