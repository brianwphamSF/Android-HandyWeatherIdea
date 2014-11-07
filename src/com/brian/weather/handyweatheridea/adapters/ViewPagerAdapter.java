package com.brian.weather.handyweatheridea.adapters;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> mList;
	private List<String> mTab;

	public ViewPagerAdapter(FragmentManager fragmentManager,
			List<Fragment> mList, List<String> mTab) {
		super(fragmentManager);
		this.mList = mList;
		this.mTab = mTab;
	}

	@Override
	public Fragment getItem(int i) {
		return mList.get(i);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTab.get(position);
	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

}