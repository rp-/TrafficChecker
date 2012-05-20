/*
 *  This File is licensed under GPL v3.
 *  Copyright (C) 2012 Rene Peinthor.
 *
 *  This file is part of TrafficChecker.
 *
 *  BlueMouse is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TrafficChecker is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with TrafficChecker.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.oldsch00l.TrafficChecker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oldsch00l.TrafficChecker.TrafficChecker.Country;

public class SelectRegionActivity extends Activity {
	private enum ListItemMode {
		Countries,
		Austria,
		Germany,
		England
	}
	
	private ListItemMode mCurrentListItems = ListItemMode.Countries;
	private ListView listview = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.selectregionactivity);
		
		listview = (ListView) findViewById(R.id.ListViewRegion);
		listview.setOnItemClickListener(new ListItemClickListener());
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setListView();
	}
	
	protected void setListView() {
		switch( mCurrentListItems )
		{
			case Countries:
			{
				listview.setAdapter(new ImageAdapter(this));
			}
			break;
			case Austria:
			{
				listview.setAdapter(new CheckboxAdapter(this, TrafficChecker.getCountryList(Country.Austria)));
			}
			break;
			case Germany:
			{
				listview.setAdapter(new CheckboxAdapter(this, TrafficChecker.getCountryList(Country.Germany)));
			}
			break;
			case England:
			{
				listview.setAdapter(new CheckboxAdapter(this, TrafficChecker.getCountryList(Country.England)));
			}
			break;
		}
	}
	
	private class ListItemClickListener implements AdapterView.OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch( mCurrentListItems )
			{
				case Countries:
					switch(position)
					{
					case 0:
						mCurrentListItems = ListItemMode.Austria;
					break;
					case 1:
						mCurrentListItems = ListItemMode.Germany;
					break;
					case 2:
						mCurrentListItems = ListItemMode.England;
					break;
					}
					setListView();
				break;
				default:
				{
					CheckboxAdapter ca = (CheckboxAdapter)listview.getAdapter();
					ca.setChecked(position, !ca.getItem(position).isSelected());
				}
				break;
			}
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        if(mCurrentListItems != ListItemMode.Countries)
	        {
	        	mCurrentListItems = ListItemMode.Countries;
	        	setListView();
	        	return true;
	        }
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	public void closeAndSave(View view) {
		this.finish();
	}
	
	private class CheckboxAdapter extends BaseAdapter {
		private java.util.List<TrafficRegion> mItems;
		private LayoutInflater mInflater;
		private Context mContext;

	    public CheckboxAdapter(Context context, java.util.List<TrafficRegion> items) {
	    	mContext = context;
	    	mItems = items;
	    	mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

		public int getCount() {
			return mItems.size();
		}

	    @Override
	    public int getViewTypeCount() {
	        return 1;
	    }


		public TrafficRegion getItem(int index) {
			return mItems.get(index);
		}

		public long getItemId(int index) {
			return index;
		}

	    @Override
	    public int getItemViewType(int index) {
	        return 0;
	    }
	    
	    public void setChecked(int index, boolean checked) {
	    	mItems.get(index).setSelected(checked);
	    }

		public View getView(int index, View convertView, ViewGroup parent) {
			TrafficRegion tr = mItems.get(index);
			convertView = mInflater.inflate(R.layout.list_item_countries, null);
			CheckedTextView ctv = (CheckedTextView) convertView.findViewById(R.id.CheckedTextView);
			ctv.setText(TrafficChecker.getRegionString(mContext, tr.getRegionUrlAppend()));
			listview.setItemChecked(index, tr.isSelected());
			return convertView;
		}
	}
	
	private class ImageAdapter extends BaseAdapter {
		private String[] mItems = getResources().getStringArray(R.array.countries_array);
		private LayoutInflater mInflater;

	    public ImageAdapter(Context context) {
	    	mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

		public int getCount() {
			return mItems.length;
		}

	    @Override
	    public int getViewTypeCount() {
	        return 1;
	    }


		public String getItem(int index) {
			return mItems[index];
		}

		public long getItemId(int index) {
			return index;
		}

	    @Override
	    public int getItemViewType(int index) {
	        return 0;
	    }

		public View getView(int index, View convertView, ViewGroup parent) {
			convertView = mInflater.inflate(R.layout.list_item_imagecountry, null);
			TextView tv = (TextView) convertView.findViewById(R.id.listtextview);
			StringBuffer sbText = new StringBuffer(mItems[index]);
			int count = TrafficChecker.getCountrySelectionCount(Country.values()[index]);
			
			ImageView iv = (ImageView) convertView.findViewById(R.id.ImageFlag);
			if( count > 0 )
			{
				iv.setImageResource(Country.values()[index].getFlag());
				sbText.append(" (");
				sbText.append(count);
				sbText.append(')');
			}
			else
				iv.setImageResource(Country.values()[index].getFlagInactive());
			
			tv.setText(sbText.toString());
			return convertView;
		}
	}
}
