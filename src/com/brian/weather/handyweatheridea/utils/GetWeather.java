package com.brian.weather.handyweatheridea.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;

import com.brian.weather.todaysweatheridea.R;

public class GetWeather {

	public static JSONObject getJSON(Context context, String city, String api_url) {
		try {
			URL url = new URL(String.format(api_url, city));
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.addRequestProperty("x-api-key",
					context.getString(R.string.open_weather_maps_app_id));

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			StringBuffer json = new StringBuffer(1024);
			String tmp = "";
			while ((tmp = reader.readLine()) != null)
				json.append(tmp).append("\n");
			reader.close();

			JSONObject data = new JSONObject(json.toString());

			// This value will be 404 if the request was not
			// successful
			if (data.getInt("cod") != 200) {
				return null;
			}

			return data;
		} catch (Exception e) {
			return null;
		}
	}
}
