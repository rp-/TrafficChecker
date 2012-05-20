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
import java.util.List;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

import com.google.android.maps.GeoPoint;
import com.oldsch00l.TrafficChecker.Message.SubType;

public class AndroidSaxRSSParser extends BaseFeedParser {
	final static String NSgeorss = "http://www.georss.org/georss";
	boolean mSkipItem = false;
	
    public AndroidSaxRSSParser(String feedUrl) {
        super(feedUrl);
    }

    @Override
    public List<Message> parse() {
        final Message currentMessage = new Message();
        mSkipItem = false;
        RootElement root = new RootElement("rss");
        final List<Message> messages = new ArrayList<Message>();
        Element channel = root.getChild("channel");
        Element item = channel.getChild(ITEM);
        item.setEndElementListener(new EndElementListener(){
            public void end() {
            	if( !mSkipItem)
            		messages.add(currentMessage.copyAndReset());
            	mSkipItem = false;
            }
        });
        item.getChild(TITLE).setEndTextElementListener(new EndTextElementListener(){
            public void end(String title) {
            	if( title.equals("Details of incidents will appear here. Click for map") )
            	{
            		mSkipItem = true;
            	}
            	else
            		currentMessage.setTitle(title);
            }
        });
//        item.getChild(LINK).setEndTextElementListener(new EndTextElementListener(){
//            public void end(String link) {
//            	if( !mSkipItem )
//            	{
//	            	if( link.startsWith("http://www.trafficengland.co.uk/map.aspx") )
//	            	{
//	            		//parse geo data
//	                	GeoPoint point = parseGeoPoint(link);
//	                	if( point != null)
//	                	{
//	                    	ArrayList<GeoPoint> geoList = new ArrayList<GeoPoint>();
//	                		geoList.add( point);
//	                		currentMessage.setGeoDataList( geoList );
//	                	}
//	            	}
//	                currentMessage.setLink(link);
//            	}
//            }
//        });
        item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setDescription(body);
            }
        });
        item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
            	if( !mSkipItem )
            		currentMessage.setDate(body);
            }
        });
        item.getChild(GUID).setEndTextElementListener(new EndTextElementListener() {
			public void end(String sGuid) {
				currentMessage.setGUID(sGuid);
			}
		});
        item.getChild(CATEGORY).setEndTextElementListener(new EndTextElementListener() {
			public void end(String sCategory) {
				if( sCategory.equals("roadworks") ) {
					currentMessage.setSubType(SubType.ROADWORKS);
				}
				else if( sCategory.equals("congestion") ) {
					currentMessage.setSubType(SubType.TRAFFIC);
				}
			}
		});
        item.getChild( NSgeorss, GEOPOINT).setEndTextElementListener(new EndTextElementListener(){
        	public void end(String point) {
        		String[] points = point.split( " ");
       			double nPoint = Double.parseDouble( points[0] );
       			int latpoint= (int)(nPoint * 1e6);
       			nPoint = Double.parseDouble( points[1] );
       			int longpoint= (int)(nPoint * 1e6);
        		GeoPoint p = new GeoPoint(latpoint, longpoint);
        		ArrayList<GeoPoint> pointlist = new ArrayList<GeoPoint>();
        		pointlist.add( p);
        		currentMessage.setGeoDataList( pointlist);
        	}
        });
        item.getChild( NSgeorss, GEOLINE).setEndTextElementListener(new EndTextElementListener(){
        	public void end(String point) {
        		String [] points = point.split(" ");
        		ArrayList<GeoPoint> pointlist = new ArrayList<GeoPoint>();
        		for( int i = 0; i < points.length; i += 2)
        		{
           			double nPoint = Double.parseDouble( points[i] );
           			int latpoint= (int)(nPoint * 1e6);
           			nPoint = Double.parseDouble( points[i+1] );
           			int longpoint= (int)(nPoint * 1e6);
               		GeoPoint p = new GeoPoint(latpoint, longpoint);
               		pointlist.add( p);
        		}
        		currentMessage.setGeoDataList( pointlist);
        	}
        });
        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.ISO_8859_1, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}

