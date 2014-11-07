package com.brian.weather.handyweatheridea.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brian.weather.handyweatheridea.models.WeekList;
import com.brian.weather.todaysweatheridea.R;

public class WeekListAdapter extends ArrayAdapter<WeekList> {
	private List<WeekList> weekList = new ArrayList<WeekList>();
	
	static class WeekListHolder {
		ImageView ivIcon;
		TextView tvDate;
		TextView tvTempMin;
		TextView tvTempMax;
		TextView tvDesc;
		TextView tvCond;
	}

	public WeekListAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		
		WeekListHolder wlHolder = null;
		
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.weeklist_item, parent, false);
			
			wlHolder = new WeekListHolder();
			wlHolder.ivIcon = (ImageView) row.findViewById(R.id.ivIcon);
			wlHolder.tvDate = (TextView) row.findViewById(R.id.tvDate);
			wlHolder.tvTempMin = (TextView) row.findViewById(R.id.tvTempMin);
			wlHolder.tvTempMax = (TextView) row.findViewById(R.id.tvTempMax);
			wlHolder.tvDesc = (TextView) row.findViewById(R.id.tvDesc);
			wlHolder.tvCond = (TextView) row.findViewById(R.id.tvCond);
			
			row.setTag(wlHolder);
			
		} else {
			wlHolder = (WeekListHolder) row.getTag();
		}
		
		WeekList weekList = getItem(position);
		
		wlHolder.ivIcon.setImageDrawable(weekList.getIcon());
		wlHolder.tvDate.setText(weekList.getDate());
		wlHolder.tvTempMin.setText(weekList.getTempMin());
		wlHolder.tvTempMax.setText(weekList.getTempMax());
		wlHolder.tvDesc.setText(weekList.getDesc());
		wlHolder.tvCond.setText(weekList.getCond());
		
		return row;
	}
	
	@Override
	public void add(WeekList object) {
		// TODO Auto-generated method stub
		weekList.add(object);
		super.add(object);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.weekList.size();
	}
	
	@Override
	public WeekList getItem(int position) {
		// TODO Auto-generated method stub
		return this.weekList.get(position);
	}

}
