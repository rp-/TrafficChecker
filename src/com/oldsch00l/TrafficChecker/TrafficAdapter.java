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

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrafficAdapter extends BaseAdapter {
	private ArrayList<Message> mItems;
	private LayoutInflater mInflater;
	private Context mContext;

    public TrafficAdapter(Context context, java.util.List<Message> items) {
    	mContext = context;
    	mItems = (ArrayList<Message>) items;
    	mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addHeader(final String header) {

    }

	public int getCount() {
		return mItems.size();
	}

    @Override
    public int getViewTypeCount() {
        return Message.Type.TRAFFIC.ordinal() + 1;
    }


	public String getItem(int index) {
		return mItems.get(index).toString();
	}

	public long getItemId(int index) {
		return index;
	}

    @Override
    public int getItemViewType(int index) {
        return mItems.get(index).getType().ordinal();
    }

	public View getView(int index, View convertView, ViewGroup parent) {
		Message msg = mItems.get(index);
		if( getItemViewType(index) == Message.Type.HEADER.ordinal() )
		{
			convertView = mInflater.inflate(R.layout.list_item_header, null);
			TextView tv = (TextView) convertView.findViewById(R.id.listtextview);

			int count = index + 1;
			while( count < mItems.size() && mItems.get(count).getType() != Message.Type.HEADER) count++;
			tv.setText(msg.getTitle() + " (" + (count - index - 1) + ")");
		}
		else
		{
			convertView = mInflater.inflate(R.layout.list_item, null);
			TextView tvTitle = (TextView) convertView.findViewById(R.id.listitemTVTitle);
			TextView tv = (TextView) convertView.findViewById(R.id.listtextview);
			ImageView ivIcon = (ImageView) convertView.findViewById(R.id.listitemicon);

			tvTitle.setText(msg.getTitle());
			StringBuilder sb = new StringBuilder();
			sb.append(mContext.getString(R.string.date));
			sb.append(": ");
			sb.append(msg.getDateAsString());
			if( !msg.getTitle().equals(msg.getDescription()) )
			{
				sb.append('\n');
				sb.append(msg.getDescription());
			}
			tv.setText(sb.toString());

			switch(msg.getSubtype()) {
			case ROADWORKS: {
				ivIcon.setImageResource(R.drawable.menu_roadworks);
			}
			break;
			case ROADCONDITION: {
				ivIcon.setImageResource(R.drawable.m_roadcondition);
			}
			default: {
				ivIcon.setImageResource(R.drawable.menu_traffic);
			}
			}
		}
		return convertView;
	}
}
