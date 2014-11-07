package com.brian.weather.handyweatheridea.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class LocationPreference {
	
	SharedPreferences prefs;
    
    public LocationPreference(Context context){
        prefs = context.getSharedPreferences(context.getApplicationContext().toString(), Context.MODE_PRIVATE);
    }
     
    // If the user has not chosen a city yet, return
    // Sydney as the default city
    public String getCity(Context context) {
        return prefs.getString("location", ExtraUtils.showInputDialog(context));
    }
    
    public String getSearchedCity() {
        return prefs.getString("location", ExtraUtils.savedFromInput());
    }
    
    public String getCityByLocation(String latlng) {
        return prefs.getString("location", latlng);
    }
     
    public void setCity(String city){
        prefs.edit().putString("location", city).commit();
    }

}
