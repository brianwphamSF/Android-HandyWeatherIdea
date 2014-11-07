package com.brian.weather.handyweatheridea.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brian.weather.handyweatheridea.interfaces.NavDrawerItem;
import com.brian.weather.handyweatheridea.models.CustomDrawer;
import com.brian.weather.handyweatheridea.models.CustomDrawerSection;
import com.brian.weather.todaysweatheridea.R;

public class DrawerAdapter extends ArrayAdapter<NavDrawerItem> {

	Context context;
	int layoutResourceId;
	NavDrawerItem data[] = null;
	
	private LayoutInflater inflater;

	public DrawerAdapter(Context context, int resource, NavDrawerItem[] objects) {
		super(context, resource, objects);
		this.layoutResourceId = resource;
		this.context = context;
		this.data = objects;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = null;
		
		NavDrawerItem item = this.getItem(position);
		if (item.getType() == CustomDrawer.ITEM_TYPE) {
			row = getItemView(convertView, parent, item);
		} else {
			row = getSectionView(convertView, parent, item);
		}
		
		return row;
	}

	public View getItemView(View convertView, ViewGroup parentView,
			NavDrawerItem navDrawerItem) {

		CustomDrawer cDrawer = (CustomDrawer) navDrawerItem;
		DrawerHolder dHolder = null;

		if (convertView == null) {
			//LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.drawer_item, parentView, false);

			dHolder = new DrawerHolder();
			dHolder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
			dHolder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

			convertView.setTag(dHolder);
		} else {
			dHolder = (DrawerHolder) convertView.getTag();
		}

		//CustomDrawer cDrawer = data[position];
		dHolder.txtTitle.setText(cDrawer.getTitle());
		dHolder.imgIcon.setImageResource(cDrawer.getIcon());
		
		return convertView;
	}
	
	public View getSectionView(View convertView, ViewGroup parentView, NavDrawerItem navDrawerItem) {
		
		CustomDrawerSection drawerSection = (CustomDrawerSection) navDrawerItem;
		SectionHolder sHolder = null;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.drawer_section, parentView, false);
			
			sHolder = new SectionHolder();
			sHolder.txtTitle = (TextView) convertView.findViewById(R.id.section_title);
			
			convertView.setTag(sHolder);
		}
		
		if (sHolder == null) {
			sHolder = (SectionHolder) convertView.getTag();
		}
		
		sHolder.txtTitle.setText(drawerSection.getTitle());
		
		return convertView;
	}
	
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return this.getItem(position).getType();
	}
	
	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return getItem(position).isEnabled();
	}

	static class DrawerHolder {
		ImageView imgIcon;
		TextView txtTitle;
	}
	
	static class SectionHolder {
		TextView txtTitle;
	}

}
