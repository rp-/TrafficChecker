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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

import com.google.android.maps.GeoPoint;

// Used for germany
public class AndroidSaxFeedParser extends BaseFeedParser {
	final static String ns = "http://www.w3.org/2005/Atom";
	final static SimpleDateFormat SFormat = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss'Z'");
    public AndroidSaxFeedParser(String feedUrl) {
        super(feedUrl);
    }
    
    public List<Message> parse() {
        final Message currentMessage = new Message();
        RootElement root = new RootElement(ns, "feed");
        final List<Message> messages = new ArrayList<Message>();
        Element item = root.getChild(ns, "entry");
        item.setEndElementListener(new EndElementListener(){
            public void end() {
                messages.add(currentMessage.copyAndReset());
            }
        });
        item.getChild(ns, TITLE).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setTitle(body);
            }
        });
		item.getChild(ns, LINK).setEndTextElementListener(new EndTextElementListener(){
			public void end(String link) {
				GeoPoint point = parseGeoPoint(link);
				if( point != null)
				{
					ArrayList<GeoPoint> geoList = new ArrayList<GeoPoint>();
					geoList.add( point);
					currentMessage.setGeoDataList( geoList );
				}
				currentMessage.setLink(link);
			}
		});
        item.getChild(ns, SUMMARY).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setDescription(body);
            }
        });
        item.getChild(ns, "updated").setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentMessage.setDate(body, SFormat);
            }
        });
        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (AssertionError e) {
        	throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
