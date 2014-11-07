package com.brian.weather.handyweatheridea.models;

import android.graphics.drawable.Drawable;

public class WeekList {
	
	private Drawable dIcon;
	private String tvDate;
	private String tvTempMin;
	private String tvTempMax;
	private String tvDesc;
	private String tvCond;
	
	public WeekList(Drawable dIcon, String tvDate, String tvTempMin, String tvTempMax, String tvDesc, String tvCond) {
		this.dIcon = dIcon;
		this.tvDate = tvDate;
		this.tvTempMin = tvTempMin;
		this.tvTempMax = tvTempMax;
		this.tvDesc = tvDesc;
		this.tvCond = tvCond;
	}
	
	public Drawable getIcon() {
		return dIcon;
	}
	
	public String getTempMin() {
		return tvTempMin;
	}
	
	public String getTempMax() {
		return tvTempMax;
	}
	
	public String getDesc() {
		return tvDesc;
	}
	
	public String getCond() {
		return tvCond;
	}
	
	public String getDate() {
		return tvDate;
	}

}
