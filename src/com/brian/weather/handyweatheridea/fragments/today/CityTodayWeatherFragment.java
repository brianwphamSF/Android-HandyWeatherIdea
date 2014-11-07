package com.brian.weather.handyweatheridea.fragments.today;

import android.graphics.Typeface;
import android.os.Bundle;

import com.brian.weather.handyweatheridea.utils.LocationPreference;

public class CityTodayWeatherFragment extends MainTodayWeatherFragment {
	
	public static CityTodayWeatherFragment newInstance() {
		return new CityTodayWeatherFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
		weatherFont = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/weather.ttf");
		updateWeatherData(new LocationPreference(getActivity()).getSearchedCity());
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
