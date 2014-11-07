package com.brian.weather.handyweatheridea.fragments.weeklist;

import com.brian.weather.handyweatheridea.utils.LocationPreference;

public class CityWeekListFragment extends MainWeekListFragment {

	public static CityWeekListFragment newInstance() {
		return new CityWeekListFragment();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&cnt=7&units=metric&mode=json";

		updateWeatherData(new LocationPreference(getActivity())
				.getSearchedCity());

		super.onResume();
	}

}
