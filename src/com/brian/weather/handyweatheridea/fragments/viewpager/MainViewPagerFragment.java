package com.brian.weather.handyweatheridea.fragments.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brian.weather.handyweatheridea.adapters.ViewPagerAdapter;
import com.brian.weather.handyweatheridea.fragments.MainFragment;
import com.brian.weather.todaysweatheridea.R;
import com.viewpagerindicator.TabPageIndicator;

public class MainViewPagerFragment extends MainFragment {
	
	//private List<Fragment> mFragmentTabs = new ArrayList<Fragment>();
	protected List<Fragment> mFragmentTabs = new ArrayList<Fragment>();
	protected List<String> mFragmentString = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mFragmentString.add(getString(R.string.graph));
		mFragmentString.add(getString(R.string.week_list));
		mFragmentString.add(getString(R.string.map));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//mFragmentTabs.add(new LocationTodayWeatherFragment());
		//mFragmentTabs.add(new CityTodayWeatherFragment());
		return inflater.inflate(R.layout.view_pager_tabs, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onViewCreated(view, savedInstanceState);
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		
		pager.setOffscreenPageLimit(2);
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), mFragmentTabs, mFragmentString);
		
		pager.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
		
		TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		indicator.notifyDataSetChanged();
		
	}

	@Override
	public void changeCity(String city) {
		// TODO Auto-generated method stub
		// nothing to do here
	}

	@Override
	public void updateWeatherData(String location) {
		// TODO Auto-generated method stub
		// nothing to do here
	}

}
