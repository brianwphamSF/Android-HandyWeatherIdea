package com.brian.weather.handyweatheridea.models;

import com.brian.weather.handyweatheridea.interfaces.NavDrawerItem;

public class CustomDrawerSection implements NavDrawerItem {
	
	public static final int SECTION_TYPE = 0;
	private int id;
	private String title;
	
	private CustomDrawerSection() {
		
	}
	
	public static CustomDrawerSection create(int id, String title) {
		CustomDrawerSection drawerSection = new CustomDrawerSection();
		drawerSection.setTitle(title);
		return drawerSection;
	}

	private void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return SECTION_TYPE;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateActionBarTitle() {
		// TODO Auto-generated method stub
		return false;
	}

}
