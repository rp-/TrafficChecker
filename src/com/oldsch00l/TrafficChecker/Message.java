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

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.maps.GeoPoint;

public class Message implements Comparable<Message>{
	static SimpleDateFormat FORMATTER =
		new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	static SimpleDateFormat DATEOUTFORMAT =
		new SimpleDateFormat("dd.MM.yyyy HH:mm");
	private String title;
	private URL link;
	private String description;
	private Date date;
	private java.util.List<GeoPoint> geoDataList = null;
	private String guid;
	private SubType mSubType = SubType.UNDEFINED;

	private Type type;

	public enum Type {
		TRAFFIC,
		HEADER
	}

	enum SubType {
		UNDEFINED,
		TRAFFIC,
		ROADWORKS,
		ROADCONDITION
	}

	public Message() {
		date = new Date();
		link = null;
		description = "";
		title = "";
		type = Type.TRAFFIC;
	}

	public Message(String title) {
		date = new Date();
		link = null;
		description = "";
		this.title = title;
		type = Type.HEADER;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}
	// getters and setters omitted for brevity
	public URL getLink() {
		return link;
	}

	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setGUID(String sGuid) {
		guid = sGuid;
	}

	public String getGUID() {
		return guid;
	}

	public SubType getSubtype( ) {
		if( mSubType == SubType.UNDEFINED )
		{
			if( link != null )
			{
				int subtypePos = link.toString().lastIndexOf( "subtype");
				if( subtypePos != -1)
				{
					String subType = link.toString().substring( link.toString().lastIndexOf( "subtype") + "subtype=".length() );
					if( subType.equals("verkehr") ) return SubType.TRAFFIC;
					else if( subType.equals("strassenzustand")) return SubType.ROADCONDITION;
					else if( subType.equals("baustellen") ) return SubType.ROADWORKS;
					else return SubType.UNDEFINED;
				}
				else
				{
					String sDescLower = description.toLowerCase();
					if( (sDescLower.indexOf("baustelle") != -1 
						|| sDescLower.indexOf("bauarbeiten") != -1
						|| sDescLower.indexOf("tagesbaustelle") != -1
						|| sDescLower.indexOf("brückenarbeiten") != -1
						|| sDescLower.indexOf("dauerbaustelle") != -1)
						&& sDescLower.indexOf("unfall") == -1)
							return SubType.ROADWORKS;
					else return SubType.TRAFFIC;
				}
			}
		}
		return mSubType;
	}

	public void setSubType(SubType subtype) {
		mSubType = subtype;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type t) {
		type = t;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getDateAsString() {
		return DATEOUTFORMAT.format(this.date);
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(String date) {
		try {
			this.date = FORMATTER.parse(date.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setDate(String date, SimpleDateFormat format) {
		try {
			this.date = format.parse(date.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void setGeoDataList( java.util.List<GeoPoint> geoList) {
		geoDataList = geoList;
	}

	public java.util.List<GeoPoint> getGeoDataList() {
		return geoDataList;
	}

	public Message copyAndReset(){
		Message copy = new Message();
		copy.title = title;
		copy.link = link;
		copy.description = description;
		copy.date = date;
		copy.geoDataList = geoDataList;
		copy.mSubType = mSubType;
		
		reset();
		return copy;
	}

	public void reset() {
		title = "";
		link = null;
		description = "";
		date = new Date();
		geoDataList = null;
		mSubType = SubType.UNDEFINED;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		sb.append('\n');
		sb.append( "Date: ");
		sb.append(this.getDateAsString());
		sb.append('\n');
		if( !title.equals(description) )
			sb.append(description);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (!mSubType.equals(other.mSubType))
			return false;
		if (geoDataList != null) {
			if(other.geoDataList != null) {
				for(int i = 0; i < geoDataList.size(); i++) {
					if(i != other.geoDataList.size() && !geoDataList.get(i).equals(other.geoDataList.get(i)))
						return false;
				}
			} else {
				return false;
			}
		} else {
			if(other.geoDataList != null)
				return false;
		}
		return true;
	}

	public int compareTo(Message another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.date.compareTo(date);
	}
}