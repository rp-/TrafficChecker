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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TrafficOverlay extends Overlay {

	protected List<GeoPoint> mGeoList;

	public TrafficOverlay( List<GeoPoint> points) {
		mGeoList = points;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		if( mapView.getZoomLevel() > 10 )
		{
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor( Color.argb( 128, 255, 0, 0) );
//			Point p = new Point();
			
			if( mGeoList.size() > 1)
			{
				paint.setStrokeWidth(9);
				canvas.drawLines( geoPointsToLine(mGeoList, mapView), paint);
			}
//			else
//			{
//				mapView.getProjection().toPixels( mGeoList.get(0), p);
//				canvas.drawCircle(p.x, p.y, mapView.getZoomLevel() - 3, paint);
//			}
		}
	}
	
	private static float[] geoPointsToLine(List<GeoPoint> points, MapView mapView) {
		float[] retfloat = new float[points.size() * 2];
		Point p = new Point();
		int i = 0;
		for (GeoPoint geopoint : points) {
			mapView.getProjection().toPixels( geopoint, p);
			retfloat[i++] = p.x;
			retfloat[i++] = p.y;
		}
		return retfloat;
	}
}
