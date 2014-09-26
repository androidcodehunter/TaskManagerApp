package com.manager.task;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.manager.task.tasks.Task;

public class RouteActivity extends Activity {
	private double initLatitude = 23.728978;
	private double initLongitude = 90.398199;
	private GoogleMap mMap;
	private LatLng fromPosition;
	private LatLng toPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route);

		Intent intent = getIntent();

		Task task = (Task) intent.getSerializableExtra("TASK");

		fromPosition = new LatLng(initLatitude, initLongitude);
		toPosition = new LatLng(task.getLatitude(), task.getLongitude());

		new GetDirectionAsyncTask().execute();
	}

	private class GetDirectionAsyncTask extends
			AsyncTask<String, Integer, ArrayList<LatLng>> {
		@Override
		protected ArrayList<LatLng> doInBackground(String... params) {

			ArrayList<LatLng> directionPoint = null;

			try {
				mMap = ((MapFragment) getFragmentManager().findFragmentById(
						R.id.rout_map)).getMap();
				GMapV2Direction md = new GMapV2Direction();
				Document doc = md.getDocument(fromPosition, toPosition,
						GMapV2Direction.MODE_DRIVING);

				directionPoint = md.getDirection(doc);
			} catch (Exception e) {
				Log.e("text", e.toString());
			}
			return directionPoint;
		}

		@Override
		protected void onPostExecute(ArrayList<LatLng> directionPoint) {
			PolylineOptions rectLine = new PolylineOptions().width(3).color(
					Color.RED);
			for (int i = 0; i < directionPoint.size(); i++) {
				rectLine.add(directionPoint.get(i));
			}
			if (mMap != null) {
				Marker hamburg = mMap
						.addMarker(new MarkerOptions()
								.position(fromPosition)
								.icon(BitmapDescriptorFactory
										.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
				Marker kiel = mMap
						.addMarker(new MarkerOptions()
								.position(toPosition)
								.icon(BitmapDescriptorFactory
										.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
				// Move the camera instantly to hamburg with a zoom of 15.
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromPosition,
						15));
				// Zoom in, animating the camera.
				mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
				mMap.addPolyline(rectLine);
			}
		}
	}
}
