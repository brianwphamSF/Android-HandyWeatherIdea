package com.brian.weather.handyweatheridea.models;

import com.brian.weather.handyweatheridea.interfaces.NavDrawerItem;

public class CustomDrawer implements NavDrawerItem {
	
	public static final int ITEM_TYPE = 1;
	
	private int id;
	private int icon;
	private String title;
	private boolean updateActionBarTitle;
	
	public CustomDrawer() {
		super();
	}
	
	public static CustomDrawer create(int id, int icon, String title, boolean updateActionBarTitle) {
		CustomDrawer drawer = new CustomDrawer();
		drawer.setId(id);
		drawer.setIcon(icon);
		drawer.setTitle(title);
		drawer.setUpdateActionBarTitle(updateActionBarTitle);
		return drawer;
	}

	private void setUpdateActionBarTitle(boolean updateActionBarTitle) {
		// TODO Auto-generated method stub
		this.updateActionBarTitle = updateActionBarTitle;
	}

	private void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}

	private void setIcon(int icon) {
		// TODO Auto-generated method stub
		this.icon = icon;
	}

	private void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	public int getIcon() {
		return icon;
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
		return ITEM_TYPE;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateActionBarTitle() {
		// TODO Auto-generated method stub
		return this.updateActionBarTitle;
	}

}
