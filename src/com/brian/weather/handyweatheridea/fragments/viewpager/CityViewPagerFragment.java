package com.brian.weather.handyweatheridea.fragments.viewpager;

import com.brian.weather.handyweatheridea.fragments.graphs.CityGraphFragment;
import com.brian.weather.handyweatheridea.fragments.map.CityMapFragment;
import com.brian.weather.handyweatheridea.fragments.weeklist.CityWeekListFragment;

import android.os.Bundle;

public class CityViewPagerFragment extends MainViewPagerFragment {
	
	public static CityViewPagerFragment newInstance() {
		return new CityViewPagerFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mFragmentTabs.add(CityGraphFragment.newInstance());
		mFragmentTabs.add(CityWeekListFragment.newInstance());
		mFragmentTabs.add(CityMapFragment.newInstance());
	}

}
