package com.brian.weather.handyweatheridea.fragments.graphs;

import com.brian.weather.handyweatheridea.utils.LocationPreference;

public class CityGraphFragment extends MainGraphFragment {
	
	public static CityGraphFragment newInstance() {
		return new CityGraphFragment();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric";
		updateWeatherData(new LocationPreference(getActivity()).getSearchedCity());
		
		super.onResume();
	}

}
