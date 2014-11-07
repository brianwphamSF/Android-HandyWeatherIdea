package com.brian.weather.handyweatheridea.activities;

import java.util.Calendar;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.brian.weather.handyweatheridea.fragments.DrawerSelectFragment;
import com.brian.weather.handyweatheridea.fragments.navdrawer.NavigationDrawerFragment;
import com.brian.weather.handyweatheridea.fragments.navdrawer.NavigationDrawerFragment.NavigationDrawerCallbacks;
import com.brian.weather.handyweatheridea.fragments.today.CityTodayWeatherFragment;
import com.brian.weather.handyweatheridea.fragments.today.LocationTodayWeatherFragment;
import com.brian.weather.handyweatheridea.fragments.viewpager.CityViewPagerFragment;
import com.brian.weather.handyweatheridea.fragments.viewpager.LocationViewPagerFragment;
import com.brian.weather.handyweatheridea.interfaces.ActivityCommunicator;
import com.brian.weather.handyweatheridea.interfaces.FragmentCommunicator;
import com.brian.weather.handyweatheridea.utils.ExtraUtils;
import com.brian.weather.todaysweatheridea.R;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerCallbacks, ActivityCommunicator {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	public FragmentCommunicator fragmentCommunicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupNavigationDrawer();

		switchUIDayNight();
	}

	private void setupNavigationDrawer() {
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		mTitle = getTitle();

		// Set up the drawer
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// TODO Auto-generated method stub
		selectFragment(DrawerSelectFragment.newInstance(position + 1));
	}

	public void selectFragment(Fragment fragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();

		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.replace(R.id.container, fragment).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.t_location);
		case 2:
			selectFragment(LocationTodayWeatherFragment.newInstance());
			mTitle = getString(R.string.t_current_weather);
			break;
		case 3:
			selectFragment(LocationViewPagerFragment.newInstance());
			mTitle = getString(R.string.t_more_information);
			break;
		case 4:
			mTitle = getString(R.string.t_chosen);
		case 5:
			selectFragment(CityTodayWeatherFragment.newInstance());
			mTitle = getString(R.string.t_current_weather);
			break;
		case 6:
			selectFragment(CityViewPagerFragment.newInstance());
			mTitle = getString(R.string.t_more_information);
			break;
		case 7:
			mTitle = getString(R.string.t_share);
		case 8:
			selectFragment(LocationTodayWeatherFragment.newInstance());
			mTitle = getString(R.string.t_share_weather);
			break;
		}
	}

	public void restoreActionBar(CharSequence cs) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(cs);
	}

	protected void switchUIDayNight() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);

		UiModeManager manager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);

		if ((hour >= 18 && hour <= 23) || (hour >= 0 && hour <= 6)) {
			manager.enableCarMode(0);
		} else {
			manager.disableCarMode(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar(mTitle);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id == R.id.action_search) {
			onNavigationDrawerItemSelected(4);
			restoreActionBar(getString(R.string.t_current_weather));
			ExtraUtils.showInputDialog(this);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void passDataToActivity(String data) {
		// TODO Auto-generated method stub
		if (mTitle == getString(R.string.t_share_weather)) {
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, data);
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
		}
	}
}
