package com.manager.task;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends Activity {
	private Button signUpButton;
	private boolean isSignIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		isSignIn = preferences.getBoolean("IS_SIGNIN", false);

		if (isSignIn) {
			startActivity(new Intent(this, MainActivity.class));
			finish();
		} else {
			setUpViews();
		}

	}

	private void setUpViews() {
		signUpButton = (Button) findViewById(R.id.bt_sign_up);

		signUpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomeScreen.this, SignUpActivity.class));
			}
		});
	}
}
