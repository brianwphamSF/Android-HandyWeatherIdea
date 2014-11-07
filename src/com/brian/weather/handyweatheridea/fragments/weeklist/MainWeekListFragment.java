package com.brian.weather.handyweatheridea.fragments.weeklist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.brian.weather.handyweatheridea.adapters.WeekListAdapter;
import com.brian.weather.handyweatheridea.fragments.MainFragment;
import com.brian.weather.handyweatheridea.models.WeekList;
import com.brian.weather.todaysweatheridea.R;

public class MainWeekListFragment extends MainFragment {

	protected ListView weekListView;
	protected WeekListAdapter wlAdapter;
	
	Typeface weatherFont;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_weeklist, container, false);

		weatherFont = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/weather.ttf");

		weekListView = (ListView) v.findViewById(R.id.week_listview);

		weekListView.addHeaderView(new View(getActivity()));
		weekListView.addFooterView(new View(getActivity()));

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
		new GetListTask().execute(location);
	}

	protected class GetListTask extends JSONWeatherTask {
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (json != null) {
				renderList(json);
			}
		}
	}

	private void renderList(final JSONObject json) {

		wlAdapter = new WeekListAdapter(getActivity(), R.layout.weeklist_item);

		try {
			JSONArray list = json.getJSONArray("list");
			for (int i = 0; i < list.length(); i++) {
				JSONObject obj = list.getJSONObject(i);

				JSONObject weather = obj.getJSONArray("weather")
						.getJSONObject(0);

				JSONObject temp = obj.getJSONObject("temp");

				String dStr = "i" + weather.optString("icon");
				int id = getResources().getIdentifier(dStr, "drawable",
						getActivity().getPackageName());
				Drawable drawable = getResources().getDrawable(id);
				
				Calendar c = Calendar.getInstance();
				
				c.add(Calendar.DATE, i);
				
				SimpleDateFormat month = new SimpleDateFormat("EEEE: MMMM dd, yyyy", Locale.US);
				
				WeekList wList = new WeekList(drawable,
						month.format(c.getTime()),
						"Min: " + temp.optString("min") + "℃",
						"Max: " + temp.optString("max") + "℃",
						weather.optString("description"),
						"How's The Weather: " + weather.optString("main"));
				wlAdapter.add(wList);
				weekListView.setAdapter(wlAdapter);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
