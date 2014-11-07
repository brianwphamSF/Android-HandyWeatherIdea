package com.brian.weather.handyweatheridea.fragments.graphs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.brian.weather.handyweatheridea.fragments.MainFragment;
import com.brian.weather.todaysweatheridea.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class MainGraphFragment extends MainFragment {
	
	protected GraphViewSeries gvSeries;
	protected GraphView graphView;
	protected LinearLayout llGraph;
	protected GraphViewDataInterface [] graphViewDataInterface;
	
	protected int [] hours;
	protected double [] temps;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = inflater
				.inflate(R.layout.fragment_graphview, container, false);

		llGraph = (LinearLayout) v.findViewById(R.id.llGraph);
		
		return v;
	}

	@Override
	public void changeCity(String city) {
		// TODO Auto-generated method stub
		updateWeatherData(city);
	}

	@Override
	public void updateWeatherData(String location) {
		// TODO Auto-generated method stub

		new GetGraphTask().execute(location);
		
	}
	
	public class GetGraphTask extends JSONWeatherTask {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (json != null) {
				renderGraph(json);
				
			}
		}

	}

	protected void renderGraph(JSONObject json) {
		// TODO Auto-generated method stub
		try {
			JSONArray list = json.getJSONArray("list");
			
			hours = new int[list.length()];
			temps = new double[list.length()];
			graphViewDataInterface = new GraphViewDataInterface[list.length()];
			
			for (int i = 0; i < list.length(); i++) {
				final int position = i;
				
				JSONObject obj = list.getJSONObject(i);
				JSONObject main = obj.getJSONObject("main");
				final String hour = obj.optString("dt_txt").replaceAll(".*\\ |\\:.*", "");
				final String temp = main.optString("temp");
				
				hours[i] = Integer.parseInt(hour);
				temps[i] = Double.parseDouble(temp);
				
				graphViewDataInterface[i] = new GraphViewDataInterface() {
					
					@Override
					public double getY() {
						// TODO Auto-generated method stub
						return temps[position];
					}
					
					@Override
					public double getX() {
						// TODO Auto-generated method stub
						return position * 3;
					}
				};
			}
			
			gvSeries = new GraphViewSeries("3 Day Chart", new GraphViewSeriesStyle(Color.rgb(255, 204, 153), 3), graphViewDataInterface);
			
			graphView = new LineGraphView(getActivity(), "Hourly from today by temperature (3 days calculation)");
			graphView.addSeries(gvSeries);
			graphView.getGraphViewStyle().setTextSize(16);
			
			llGraph.addView(graphView);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
