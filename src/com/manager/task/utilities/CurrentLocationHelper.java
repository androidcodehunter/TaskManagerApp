package com.manager.task.utilities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class CurrentLocationHelper implements LocationListener {
	private static final long MIN_TIME_BW_UPDATES = 35;
	private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
	private LocationManager locationManager;
	private Context context;
	private boolean isGPSEnabled;
	private boolean isNetworkEnabled;
	private Location location;

	public CurrentLocationHelper(Context context) {
		this.context = context;
	}

	public Location getGpsLocation() {
		try {
			locationManager = (LocationManager) context
					.getSystemService(context.LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
				gotoGpsSettingForTurnOn();
			} else {
				this.isGPSEnabled = true;
				if (isNetworkEnabled) {
					Log.e("network available", "available");
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

					if (locationManager != null) {
						Log.e("network available", "available manager");							
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						
						if (location != null) {
							Log.e("network available", "available location");	
							return location;
						}
					}
				}

				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								return location;
							}
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void gotoGpsSettingForTurnOn() {
		Intent gpsOptionsIntent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		context.startActivity(gpsOptionsIntent);
	}

	@Override
	public void onLocationChanged(Location location) {

	}

	@Override
	public void onProviderDisabled(String arg0) {
	}

	@Override
	public void onProviderEnabled(String arg0) {
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	}

}