package com.brian.weather.handyweatheridea.fragments;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.brian.weather.handyweatheridea.utils.GetWeather;
import com.brian.weather.todaysweatheridea.R;

public abstract class MainFragment extends Fragment {
	
	// API endpoint determined by CityWeatherFragment
	// and LocationWeatherFragment
	protected String OPEN_WEATHER_MAP_API;

	protected String c, ll, data;
	
	public abstract void changeCity(String city);
	
	public abstract void updateWeatherData(final String location);
	
	protected class JSONWeatherTask extends AsyncTask<String, Void, String> {

		protected JSONObject json;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			// Default is city. Save the value
			// to a temporary variable so that
			// we can retrieve it later on.
			if (params[0] != null) {
				c = params[0];
			} else {
				params[0] = c;
			}
			
			// First try to retrieve by city
			json = GetWeather.getJSON(getActivity(), params[0],
					OPEN_WEATHER_MAP_API);
			
			// Then try by location
			if (json == null) {
				json = GetWeather.getJSON(getActivity(), ll,
						OPEN_WEATHER_MAP_API);
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (json == null) {
				Toast.makeText(getActivity(),
						//"Place not found",
						getActivity().getString(R.string.place_not_found),
						Toast.LENGTH_LONG).show();

			}
		}

	}

}
