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

// This provider is mostly unused, currently its only use is to
// get the current selected regions and if the text or map view is shown.

package com.oldsch00l.TrafficChecker;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class TrafficProvider extends ContentProvider {

	public static final String trafficURI = "content://com.oldsch00l.provider.TrafficChecker/traffic";
	public static final String geopointsURI = "content://com.oldsch00l.provider.TrafficChecker/geopoints";
	public static final String settingsURI = "content://com.oldsch00l.provider.TrafficChecker/settings";
	public static final Uri CONTENT_URI_TRAFFIC = Uri.parse(trafficURI);
	public static final Uri CONTENT_URI_GEOPOINTS = Uri.parse(geopointsURI);
	public static final Uri CONTENT_URI_SETTINGS = Uri.parse(settingsURI);

	private SQLiteDatabase trafficDB;

	public static final String DATABASE_NAME = "trafficChecker.db";
	public static final int DATABASE_VERSION = 10;

	public static final String TABLE_TRAFFIC = "traffic";
	public static final String TABLE_GEOPOINTS = "geopoints";
	public static final String TABLE_SETTINGS = "settings";

	//traffic
	public static final String COLT_ID = "_id";
	public static final String COLT_REGION = "region";
	public static final String COLT_TITLE = "title";
	public static final String COLT_LINK = "link";
	public static final String COLT_DESC = "description";
	public static final String COLT_DATE = "date";
	public static final String COLT_SUBTYPE = "subtype";

	public static final int KEYT_ID = 0;
	public static final int KEYT_REGION = 1;
	public static final int KEYT_TITLE = 2;
	public static final int KEYT_LINK = 3;
	public static final int KEYT_DESC = 4;
	public static final int KEYT_SUBTYPE = 5;
	public static final int KEYT_DATE = 6;

	//geopoints
	public static final String COLP_ID = "_id";
	public static final String COLP_TRAFFICID = "_trafficId";
	public static final String COLP_LONG = "long";
	public static final String COLP_LAT = "lat";

	public static final int KEYP_ID = 0;
	public static final int KEYP_TRAFFICID = 1;
	public static final int KEYP_LAT = 2;
	public static final int KEYP_LONG = 3;

	//settings
	public static final String COLS_KEY = "key";
	public static final String COLS_VALUE = "value";

	public static final int KEYS_KEY = 0;
	public static final int KEYS_VALUE = 1;

	public static final String SET_REGION = "region";
	public static final String SET_VIEW = "view";

	@Override
	public boolean onCreate() {
		Context context = getContext();

		trafficDatabaseHelper dbHelper =
			new trafficDatabaseHelper( context, DATABASE_NAME, null, DATABASE_VERSION);
		trafficDB = dbHelper.getWritableDatabase();
		return trafficDB != null;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
			case GEOPOINTS_ALL:
			case SETTINGS_ALL:
			case TRAFFIC_ALL:
				return "vnd.android.cursor.dir/vnd.oldsch00l.TrafficChecker";
			case GEOPOINTS_SINGLE:
			case SETTINGS_SINGLE:
			case TRAFFIC_SINGLE:
				return "vnd.android.cursor.item/vnd.oldsch00l.TrafficChecker";
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sort) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		Cursor c = null;

		switch( uriMatcher.match(uri)) {
			case TRAFFIC_SINGLE:
				qb.appendWhere( COLT_ID + "=" + uri.getPathSegments().get(1));
			case TRAFFIC_ALL:
			{
				qb.setTables( TABLE_TRAFFIC);

				String orderBy =  TextUtils.isEmpty(sort) ? COLT_DATE : sort;

				c = qb.query(trafficDB,
									projection,
									selection,
									selectionArgs,
									null,
									null,
									orderBy);
			}
			break;
			case GEOPOINTS_SINGLE:
				qb.appendWhere( COLP_ID + "=" + uri.getPathSegments().get(1));
			case GEOPOINTS_ALL:
			{
				qb.setTables( TABLE_GEOPOINTS);

				String orderBy =  TextUtils.isEmpty(sort) ? COLP_TRAFFICID : sort;

				c = qb.query(trafficDB,
									projection,
									selection,
									selectionArgs,
									null,
									null,
									orderBy);
			}
			break;
			case SETTINGS_SINGLE:
				qb.appendWhere( COLS_KEY + "='" + uri.getPathSegments().get(1) + "'");
			case SETTINGS_ALL:
			{
				qb.setTables( TABLE_SETTINGS);

				c = qb.query(trafficDB,
									projection,
									selection,
									selectionArgs,
									null,
									null,
									null);
			}
			break;
		}

		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public Uri insert(Uri _uri, ContentValues initialValues) {
		switch( uriMatcher.match(_uri) )
		{
			case TRAFFIC_ALL:
			{
				long rowId = trafficDB.insert(TABLE_TRAFFIC, "traffic", initialValues);
				if( rowId > 0) {
					Uri uri = ContentUris.withAppendedId(CONTENT_URI_TRAFFIC, rowId);
					getContext().getContentResolver().notifyChange(uri, null);
					return uri;
				}
			}
			break;
			case GEOPOINTS_ALL:
			{
				long rowId = trafficDB.insert(TABLE_GEOPOINTS, "geopoints", initialValues);
				if( rowId > 0) {
					Uri uri = ContentUris.withAppendedId(CONTENT_URI_GEOPOINTS, rowId);
					getContext().getContentResolver().notifyChange(uri, null);
					return uri;
				}
			}
			break;
			case SETTINGS_ALL:
			{
				long rowId = trafficDB.insert(TABLE_SETTINGS, "settings", initialValues);
				if( rowId > 0) {
					Uri uri = ContentUris.withAppendedId(CONTENT_URI_SETTINGS, rowId);
					getContext().getContentResolver().notifyChange(uri, null);
					return uri;
				}
			}
			break;
		}
		//throw new SQLException("Failed to insert row into " + _uri);
		return null;
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		int count;
		switch (uriMatcher.match(uri)) {
			case TRAFFIC_ALL:
				count = trafficDB.delete(TABLE_TRAFFIC, where, whereArgs);
			break;
			case TRAFFIC_SINGLE:
			{
				String segment = uri.getPathSegments().get(1);
				count = trafficDB.delete(TABLE_TRAFFIC, COLT_ID + "="
						+ segment
						+ (!TextUtils.isEmpty(where) ? " AND ("
						+ where + ')' : ""), whereArgs);
			}
			break;
			case GEOPOINTS_ALL:
				count = trafficDB.delete(TABLE_GEOPOINTS, where, whereArgs);
			break;
			case GEOPOINTS_SINGLE:
			{
				String segment = uri.getPathSegments().get(1);
				count = trafficDB.delete(TABLE_GEOPOINTS, COLP_ID + "="
						+ segment
						+ (!TextUtils.isEmpty(where) ? " AND ("
						+ where + ')' : ""), whereArgs);
			}
			break;
			case SETTINGS_ALL:
				count = trafficDB.delete(TABLE_SETTINGS, where, whereArgs);
			break;
			case SETTINGS_SINGLE:
			{
				String segment = uri.getPathSegments().get(1);
				count = trafficDB.delete(TABLE_SETTINGS, COLS_KEY + "="
						+ segment
						+ (!TextUtils.isEmpty(where) ? " AND ("
						+ where + ')' : ""), whereArgs);
			}
			break;
			default: throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		int count;
		switch (uriMatcher.match(uri)) {
			case TRAFFIC_ALL:
				count = trafficDB.update(TABLE_TRAFFIC, values, where, whereArgs);
			break;
			case TRAFFIC_SINGLE:
			{
				String segment = uri.getPathSegments().get(1);
				count = trafficDB.update(TABLE_TRAFFIC, values, COLT_ID
						+ "=" + segment
						+ (!TextUtils.isEmpty(where) ? " AND ("
						+ where + ')' : ""), whereArgs);
			}
			break;
			case SETTINGS_ALL:
				count = trafficDB.update(TABLE_SETTINGS, values, where, whereArgs);
			break;
			case SETTINGS_SINGLE:
			{
				String segment = uri.getPathSegments().get(1);
				count = trafficDB.update(TABLE_SETTINGS, values, COLS_KEY
						+ "=" + segment
						+ (!TextUtils.isEmpty(where) ? " AND ("
						+ where + ')' : ""), whereArgs);
			}
			break;
			default: throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	public static String getSetting( ContentResolver cr, String setting) {
		Cursor cSetting = cr.query(
				CONTENT_URI_SETTINGS,
				null,
				COLS_KEY + "=?",
				new String[] { setting },
				null);
		cSetting.moveToFirst();
		String sReturn = cSetting.getString(KEYS_VALUE);
		cSetting.close();
		return sReturn;
	}
	
	public static int getSettingAsInt( ContentResolver cr, String setting) {
		Cursor cSetting = cr.query(
				CONTENT_URI_SETTINGS,
				null,
				COLS_KEY + "=?",
				new String[] { setting },
				null);
		cSetting.moveToFirst();
		int nReturn = cSetting.getInt(KEYS_VALUE);
		cSetting.close();
		return nReturn;
	}

	public static void setSetting( ContentResolver cr, String setting, String value) {
		ContentValues cv = new ContentValues();
		cv.put( COLS_VALUE, value);
		cr.update(
			CONTENT_URI_SETTINGS,
			cv,
			TrafficProvider.COLS_KEY + "=?",
			new String[] { setting }
		);
	}

	// Create the constants used to differentiate between the different
	// URI requests.
	private static final int TRAFFIC_ALL = 1;
	private static final int TRAFFIC_SINGLE = 2;
	private static final int GEOPOINTS_ALL = 3;
	private static final int GEOPOINTS_SINGLE = 4;
	private static final int SETTINGS_ALL = 5;
	private static final int SETTINGS_SINGLE = 6;

	private static final UriMatcher uriMatcher;
	// Populate the UriMatcher object, where a URI ending in 'items' will
	// correspond to a request for all items, and 'items/[rowID]'
	// represents a single row.
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.oldsch00l.provider.TrafficChecker", "traffic", TRAFFIC_ALL);
		uriMatcher.addURI("com.oldsch00l.provider.TrafficChecker", "traffic/#", TRAFFIC_SINGLE);
		uriMatcher.addURI("com.oldsch00l.provider.TrafficChecker", "geopoints", GEOPOINTS_ALL);
		uriMatcher.addURI("com.oldsch00l.provider.TrafficChecker", "geopoints/#", GEOPOINTS_SINGLE);
		uriMatcher.addURI("com.oldsch00l.provider.TrafficChecker", "settings", SETTINGS_ALL);
		uriMatcher.addURI("com.oldsch00l.provider.TrafficChecker", "settings/#", SETTINGS_SINGLE);
	}

	public static class trafficDatabaseHelper extends SQLiteOpenHelper {

//		private static final String CREATETABLE_TRAFFIC =
//			"CREATE TABLE " + TABLE_TRAFFIC + " ("
//			+ COLT_ID + " integer primary key autoincrement, "
//			+ COLT_REGION + " TEXT, "
//			+ COLT_TITLE + " TEXT, "
//			+ COLT_LINK + " TEXT, "
//			+ COLT_DESC + " TEXT, "
//			+ COLT_SUBTYPE + " INTEGER, "
//			+ COLT_DATE + " INTEGER );";
//		private static final String CREATETABLE_GEOPOINTS =
//			"CREATE TABLE " + TABLE_GEOPOINTS + " ("
//			+ COLP_ID + " integer primary key autoincrement, "
//			+ COLP_TRAFFICID + " integer, "
//			+ COLP_LAT + " integer, "
//			+ COLP_LONG + " integer);";
		private static final String CREATETABLE_SETTINGS =
			"CREATE TABLE " + TABLE_SETTINGS + " ("
			+ COLS_KEY + " TEXT primary key, "
			+ COLS_VALUE + " TEXT);";

		public trafficDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super( context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//db.execSQL( CREATETABLE_TRAFFIC);
			//db.execSQL( CREATETABLE_GEOPOINTS);
			db.execSQL( CREATETABLE_SETTINGS);
			db.execSQL( "INSERT INTO " + TABLE_SETTINGS + "( "
					+ COLS_KEY + ", " + COLS_VALUE + ") VALUES ('" + SET_REGION + "', '')" );
			db.execSQL( "INSERT INTO " + TABLE_SETTINGS + "( "
					+ COLS_KEY + ", " + COLS_VALUE + ") VALUES ('" + SET_VIEW + "', 'text')" );
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w( "TrafficProvider", "Upgrading database from version " + oldVersion + " to "
					+ newVersion);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAFFIC);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEOPOINTS);

			if( newVersion <= 9)
			{
				db.execSQL( "INSERT INTO " + TABLE_SETTINGS + "( "
					+ COLS_KEY + ", " + COLS_VALUE + ") VALUES ('" + SET_VIEW + "', 'text')" );
			}
			Log.w("TrafficProvider", "Finished upgrade");
		}

	}

}
