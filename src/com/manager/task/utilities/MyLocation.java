package com.manager.task.utilities;


public class MyLocation {
	private double latitude;
	private double longitude;

	public MyLocation() {
		setLatitude(23.728978);
		setLongitude(90.398199);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
