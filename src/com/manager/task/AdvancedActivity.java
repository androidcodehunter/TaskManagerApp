package com.manager.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdvancedActivity extends Activity {
	private Button btnSendSms;
	private Button btnSendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advanced_screen);

		btnSendSms = (Button) findViewById(R.id.btn_send_sms);
		btnSendEmail = (Button) findViewById(R.id.btn_send_email);

		btnSendSms.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(AdvancedActivity.this,
						SMSActivity.class));
			}
		});

		btnSendEmail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("text/plain");
				startActivity(emailIntent);
			}
		});
	}
}
