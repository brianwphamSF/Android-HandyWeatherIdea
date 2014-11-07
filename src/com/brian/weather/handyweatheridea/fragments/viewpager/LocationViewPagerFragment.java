package com.brian.weather.handyweatheridea.fragments.viewpager;

import android.os.Bundle;

import com.brian.weather.handyweatheridea.fragments.graphs.LocationGraphFragment;
import com.brian.weather.handyweatheridea.fragments.map.LocationMapFragment;
import com.brian.weather.handyweatheridea.fragments.weeklist.LocationWeekListFragment;

public class LocationViewPagerFragment extends MainViewPagerFragment {
	
	public static LocationViewPagerFragment newInstance() {
		return new LocationViewPagerFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mFragmentTabs.add(LocationGraphFragment.newInstance());
		mFragmentTabs.add(LocationWeekListFragment.newInstance());
		mFragmentTabs.add(LocationMapFragment.newInstance());
	}

}
