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

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.oldsch00l.TrafficChecker.TrafficChecker.Country;

public class TrafficRegion implements Comparable<TrafficRegion> {
		private String mRegionUrlAppend = "";
		private GeoPoint mCenterPoint = null;
		private int mZoom;
		private boolean mSelected;
		private int mOrder;
		private Country mCountry;
		private List<GeoPoint> mAreaPolygon = null;

//		public TrafficRegion( String sUrlAppend, GeoPoint center) {
//			mRegionUrlAppend = sUrlAppend;
//			mCenterPoint = center;
//			mZoom = 12;
//			mSelected = false;
//		}

		public TrafficRegion( Country country, String sUrlAppend, GeoPoint center, int order, int zoom) {
			mRegionUrlAppend = sUrlAppend;
			mCenterPoint = center;
			mZoom = zoom;
			mSelected = false;
			mCountry = country;
			mOrder = order;
		}

		public TrafficRegion( Country country, String sUrlAppend, GeoPoint center, int order, int zoom, List<GeoPoint> areaPolygon) {
			mRegionUrlAppend = sUrlAppend;
			mCenterPoint = center;
			mZoom = zoom;
			mSelected = false;
			mCountry = country;
			mAreaPolygon = areaPolygon;
			mOrder = order;
		}

		public String getRegionUrlAppend() {
			return mRegionUrlAppend;
		}

		public GeoPoint getGeoPoint() {
			return mCenterPoint;
		}

		public int getZoom() {
			return mZoom;
		}

		public void setSelected(boolean sel) {
			mSelected = sel;
		}

		public boolean isSelected() {
			return mSelected;
		}
		
		public Country getCountry() {
			return mCountry;
		}
		
		public List<GeoPoint> getAreaPolygon() {
			return mAreaPolygon;
		}

		public int compareTo(TrafficRegion other) {
			if( other == null)
				return 1;
			else
				return mOrder - other.mOrder;
		}
	}