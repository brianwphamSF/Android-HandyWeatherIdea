package com.brian.weather.handyweatheridea.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.widget.EditText;

import com.brian.weather.handyweatheridea.fragments.MainFragment;
import com.brian.weather.todaysweatheridea.R;

public class ExtraUtils {
	
	protected static String data;
	
	public static String showInputDialog(final Context context) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(context);
	    builder.setTitle("Please enter city name\n(City,Country) for most accurate results");
	    final EditText input = new EditText(context);
	    input.setTextColor(Color.BLACK);
	    input.setInputType(InputType.TYPE_CLASS_TEXT);
	    builder.setView(input);
	    builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            changeCity(input.getText().toString(), context);
	            
	        }
	    });
	    builder.show();
	    
	    data = input.getText().toString();
	    
	    return data;
	}
	
	public static String savedFromInput() {
		if (data == null) {
			data = "New York, NY";
		}
		return data;
	}
	
	public static void changeCity(String city, Context context) {
	    MainFragment wf = (MainFragment) ((FragmentActivity) context).getSupportFragmentManager()
	                            .findFragmentById(R.id.container);
	    wf.changeCity(city);
	    new LocationPreference(context).setCity(city);
	}

}
