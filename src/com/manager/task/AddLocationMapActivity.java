package com.manager.task;

import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class AddLocationMapActivity extends Activity {
	private GoogleMap googleMap;
	private Button mapLocationButton;
	private Button useLocationButton;
	private EditText addressText;
	private Address address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_location);
		setUpViews();
	}

	private void setUpViews() {

		googleMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		addressText = (EditText) findViewById(R.id.task_address);
		mapLocationButton = (Button) findViewById(R.id.map_location_button);
		useLocationButton = (Button) findViewById(R.id.use_this_location_button);
		useLocationButton.setEnabled(false);

		mapLocationButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mapCurrentAddress();
			}
		});
	}

	protected void mapCurrentAddress() {

		String addressString = addressText.getText().toString();

		Geocoder g = new Geocoder(this);

		List<Address> addresses = null;

		try {
			addresses = g.getFromLocationName(addressString, 1);
			if (addresses.size() > 0) {
				address = addresses.get(0);
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
