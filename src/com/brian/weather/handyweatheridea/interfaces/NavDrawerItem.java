package com.brian.weather.handyweatheridea.interfaces;

public interface NavDrawerItem {
	
	public int getId();
	public String getTitle();
	public int getType();
	public boolean isEnabled();
	public boolean updateActionBarTitle();

}
