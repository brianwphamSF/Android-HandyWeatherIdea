package com.brian.weather.handyweatheridea.fragments.map;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.brian.weather.handyweatheridea.utils.LocationPreference;
import com.mapquest.android.maps.GeoPoint;

public class LocationMapFragment extends CityMapFragment implements
		LocationListener {

	private LocationManager locationManager;

	private String provider;
	
	public static LocationMapFragment newInstance() {
		return new LocationMapFragment();
	}

	@Override
	protected void setupMap() {
		// TODO Auto-generated method stub
		// Get location manager
		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		boolean enabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if (!enabled) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}

		// Define the criteria how to select the location provider
		// -> use default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		android.location.Location location = locationManager
				.getLastKnownLocation(provider);

		// DebugLog.d(location.getLatitude() + " " + location.getLongitude());

		// Initialize the location fields
		if (location != null) {
			mapView.getController().setCenter(new GeoPoint(location.getLatitude(), location.getLongitude()));
			
			ll = "lat=" + location.getLatitude() + "&lon="
					+ location.getLongitude();
			OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/find?%s&cnt=30";
			updateWeatherData(new LocationPreference(getActivity())
					.getCityByLocation(ll));
			onLocationChanged(location);

		} else {
			super.setupMap();
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		// location.getLatitude and location.getLongitude
		// already called in currentLocationSet and changes
		// without using this method
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

}
