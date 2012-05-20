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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.android.maps.GeoPoint;

public abstract class BaseFeedParser {

    // names of the XML tags
    static final String PUB_DATE = "pubDate";
    static final String DESCRIPTION = "description";
    static final String SUMMARY = "summary";
    static final String LINK = "link";
    static final String TITLE = "title";
    static final String ITEM = "item";
    static final String GEOPOINT = "point";
    static final String GEOLINE = "line";
    static final String GUID = "guid";
    static final String CATEGORY = "category";

    final URL feedUrl;

    protected BaseFeedParser(String feedUrl){
        try {
            this.feedUrl = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInputStream() {
        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public java.util.List<Message> parse() {
    	return new java.util.ArrayList<Message>();
    }
    
    protected GeoPoint parseGeoPoint(String link) {
    	GeoPoint point = null;
    	int longPos = link.indexOf("lon=");
    	if( longPos >= 0 && link.charAt(longPos+4) != '&')
    	{
    		int nextVarPos = link.indexOf( '&', longPos);
    		nextVarPos = nextVarPos != -1 ? nextVarPos : link.length();
    		double dlong = Double.parseDouble( link.substring( longPos + 4, nextVarPos ) );
    		int longi = (int)(dlong * 1e6);
    		int latPos = link.indexOf("lat=");
    		nextVarPos = link.indexOf( '&', latPos);
    		nextVarPos = nextVarPos != -1 ? nextVarPos : link.length();
    		double dlat = Double.parseDouble( link.substring( latPos + 4, nextVarPos ) );
    		int lati = (int)(dlat * 1e6);
    		point = new GeoPoint( lati, longi);
    	}
    	return point;
    }
}

