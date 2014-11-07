package com.brian.weather.handyweatheridea.fragments.map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brian.weather.handyweatheridea.fragments.MainFragment;
import com.brian.weather.todaysweatheridea.R;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.Overlay.OverlayTapListener;
import com.mapquest.android.maps.OverlayItem;

public abstract class MainMapFragment extends MainFragment {
	
	MapView mapView;
	AnnotationView annotation;
	double lat, lng;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_mapview, container, false);
		
		mapView = (MapView) v.findViewById(R.id.map);
		mapView.getController().setZoom(13);
		
		setupMap();
		
		return v;
	}
	
	protected abstract void setupMap();

	@Override
	public void changeCity(String city) {
		// TODO Auto-generated method stub
		updateWeatherData(city);
	}

	@Override
	public void updateWeatherData(String location) {
		// TODO Auto-generated method stub
		new GetMapTask().execute(location);
	}

	protected class GetMapTask extends JSONWeatherTask {
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (json != null) {
				renderOnMap(json);
			}
		}
		
	}
	
	protected void renderOnMap(JSONObject json) {
		// TODO Auto-generated method stub
		
		annotation = new AnnotationView(mapView);

		Drawable icon = getResources().getDrawable(R.drawable.ic_marker);

		final DefaultItemizedOverlay poiOverlay = new DefaultItemizedOverlay(
				icon);
		
		try {
			JSONArray list = json.getJSONArray("list");
			
			for (int i = 0; i < list.length(); i++) {
				JSONObject obj = list.getJSONObject(i);
				JSONObject coords = obj.getJSONObject("coord");
				
				lat = coords.getDouble("lat");
				lng = coords.getDouble("lon");
				
				String name = obj.optString("name");
				
				JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
				
				String weatherInfo = weather.optString("main") + ": " + weather.optString("description");
				
				OverlayItem point = new OverlayItem(new GeoPoint(lat, lng), name, weatherInfo);
				poiOverlay.addItem(point);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		poiOverlay.setTapListener(new OverlayTapListener() {

			@Override
			public void onTap(GeoPoint arg0, MapView arg1) {
				// TODO Auto-generated method stub
				int lastTouchedIndex = poiOverlay.getLastFocusedIndex();
				if (lastTouchedIndex > -1) {
					OverlayItem tapped = poiOverlay.getItem(lastTouchedIndex);
					annotation.showAnnotationView(tapped);
				}
			}
		});

		mapView.getOverlays().add(poiOverlay);
	}
}
