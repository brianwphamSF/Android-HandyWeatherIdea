package com.brian.weather.handyweatheridea.fragments.map;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Process;

import com.brian.weather.handyweatheridea.utils.LocationPreference;
import com.mapquest.android.maps.GeoPoint;

public class CityMapFragment extends MainMapFragment {
	
	public static CityMapFragment newInstance() {
		return new CityMapFragment();
	}
	
	@Override
	protected void setupMap() {
		// TODO Auto-generated method stub
		
		// Thread that will geocode the location
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				String locationStr = new LocationPreference(getActivity())
					.getSearchedCity();

				double lat, lng;
				JSONObject jObj = null, coord = null;
				JSONArray list = null;
				String str = "";
				HttpResponse response;
				HttpClient client = new DefaultHttpClient();
				try {
					HttpPost connection = new HttpPost(
							"http://api.openweathermap.org/data/2.5/find?q="
									+ URLEncoder.encode(locationStr, "UTF-8") + "&type=accurate");
					response = client.execute(connection);
					str = EntityUtils.toString(response.getEntity(), "UTF-8");
					
					jObj = new JSONObject(str);
					list = jObj.getJSONArray("list");
					coord = list.getJSONObject(0).getJSONObject("coord");
					lat = coord.getDouble("lat");
					lng = coord.getDouble("lon");
					mapView.getController().setCenter(new GeoPoint(lat, lng));
					
					String latlng = "lat=" + lat + "&lon=" + lng;
					
					OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/find?" + latlng + "&cnt=30";
					
					updateWeatherData(latlng);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
		thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);

		mapView.setBuiltInZoomControls(true);
	}

}
