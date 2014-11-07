package com.brian.weather.handyweatheridea.fragments.today;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brian.weather.handyweatheridea.fragments.MainFragment;
import com.brian.weather.handyweatheridea.interfaces.ActivityCommunicator;
import com.brian.weather.handyweatheridea.interfaces.FragmentCommunicator;
import com.brian.weather.todaysweatheridea.R;

public class MainTodayWeatherFragment extends MainFragment implements FragmentCommunicator {
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	public Context context;

	Typeface weatherFont;

	TextView cityField;
	TextView updatedField;
	TextView detailsField;
	TextView currentTemperatureField;
	TextView weatherIcon;
	TextView advisableComment;
	TextView findAccessory;
	
	private ActivityCommunicator activityCommunicator;
	private String activityAssignedValue = "";
	private static final String STRING_VALUE = "stringValue";
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = getActivity();
		activityCommunicator = (ActivityCommunicator) context;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			activityAssignedValue = savedInstanceState.getString(STRING_VALUE);
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		outState.putString(STRING_VALUE, activityAssignedValue);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_today, container,
				false);

		cityField = (TextView) rootView.findViewById(R.id.city_field);
		updatedField = (TextView) rootView.findViewById(R.id.updated_field);
		detailsField = (TextView) rootView.findViewById(R.id.details_field);
		currentTemperatureField = (TextView) rootView
				.findViewById(R.id.current_temperature_field);
		weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);
		advisableComment = (TextView) rootView.findViewById(R.id.advisable_comment);
		findAccessory = (TextView) rootView.findViewById(R.id.find_accessory);

		weatherIcon.setTypeface(weatherFont);
		
		setRetainInstance(true);

		return rootView;
	}
	
	@Override
	public void changeCity(String city) {
		updateWeatherData(city);
	}
	
	@Override
	public void updateWeatherData(final String location) {
		// TODO Auto-generated method stub
		
		new GetWeatherTask().execute(location);
		
	}

	public class GetWeatherTask extends JSONWeatherTask {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (json != null) {
				renderWeather(json);
			}
		}

	}

	protected void renderWeather(JSONObject json) {
		// TODO Auto-generated method stub
		try {
			cityField.setText(json.optString("name").toUpperCase(Locale.US)
					+ ", " + json.getJSONObject("sys").optString("country"));

			JSONObject details = json.getJSONArray("weather").getJSONObject(0);
			JSONObject main = json.getJSONObject("main");
			detailsField
					.setText(details.optString("description").toUpperCase(
							Locale.US)
							+ "\n"
							+ "Humidity: "
							+ main.optString("humidity")
							+ "%"
							+ "\n"
							+ "Pressure: "
							+ main.optString("pressure") + " hPa");

			currentTemperatureField.setText(String.format("%.2f",
					main.getDouble("temp"))
					+ " ℃");

			DateFormat df = DateFormat.getDateTimeInstance();
			String updatedOn = df.format(new Date(json.getLong("dt") * 1000));
			updatedField.setText("Last update: " + updatedOn);

			setWeatherIconAndAdvisableText(details.getInt("id"), json.getJSONObject("sys")
					.getLong("sunrise") * 1000, json.getJSONObject("sys")
					.getLong("sunset") * 1000);
			
			activityCommunicator.passDataToActivity("In " + cityField.getText().toString()
					+ ", there is " + details.optString("description")
					+ "\nat " + currentTemperatureField.getText().toString()
					+ "\nas of " + updatedOn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setWeatherIconAndAdvisableText(int locationId, long sunrise, long sunset) {
		// TODO Auto-generated method stub
		int id = locationId / 100;
		String icon = "";
		if (locationId == 800) {
			long currentTime = new Date().getTime();
			if (currentTime >= sunrise && currentTime < sunset) {
				icon = context.getString(R.string.weather_sunny);
				advisableComment.setText(context.getString(R.string.advice_sunny));
				findAccessory.setText(context.getString(R.string.accessory_sunny));
			} else {
				icon = context.getString(R.string.weather_clear_night);
				advisableComment.setText(context.getString(R.string.advice_clear_night));
				findAccessory.setText(context.getString(R.string.accessory_clear_night));
			}
		} else {
			switch (id) {
			case 2:
				icon = context.getString(R.string.weather_thunder);
				advisableComment.setText(context.getString(R.string.advice_thunder));
				break;
			case 3:
				icon = context.getString(R.string.weather_drizzle);
				advisableComment.setText(context.getString(R.string.advice_drizzle));
				findAccessory.setText(context.getString(R.string.accessory_rainy_drizzly));
				break;
			case 7:
				icon = context.getString(R.string.weather_foggy);
				advisableComment.setText(context.getString(R.string.advice_foggy));
				findAccessory.setText(context.getString(R.string.accessory_foggy));
				break;
			case 8:
				icon = context.getString(R.string.weather_cloudy);
				advisableComment.setText(context.getString(R.string.advice_cloudy));
				findAccessory.setText(context.getString(R.string.accessory_cloudy));
				break;
			case 6:
				icon = context.getString(R.string.weather_snowy);
				advisableComment.setText(context.getString(R.string.advice_snowy));
				findAccessory.setText(context.getString(R.string.accessory_snowy));
				break;
			case 5:
				icon = context.getString(R.string.weather_rainy);
				advisableComment.setText(context.getString(R.string.advice_rainy));
				findAccessory.setText(context.getString(R.string.accessory_rainy_drizzly));
				break;
			}
		}
		weatherIcon.setText(icon);
	}

	@Override
	public void passDataToFragment(String data) {
		// TODO Auto-generated method stub
		activityAssignedValue = data;
	}

}