package com.oldsch00l.TrafficChecker;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	public static final String KEY_LIST_ORDERBY = "orderby";
	
	private ListPreference mListPrefSortOrder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		
		mListPrefSortOrder = (ListPreference)getPreferenceScreen().findPreference(KEY_LIST_ORDERBY);
	}
	
	private String getStringResourceByName(String aString)
	{
	  String packageName = "com.oldsch00l.TrafficChecker";
	  int resId = getResources().getIdentifier(aString, "string", packageName);
	  return getString(resId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
		mListPrefSortOrder.setSummary(getStringResourceByName(sp.getString(KEY_LIST_ORDERBY, "date")));
		
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if(key.equals(KEY_LIST_ORDERBY)) {
			mListPrefSortOrder.setSummary(getStringResourceByName(sharedPreferences.getString(KEY_LIST_ORDERBY, "date")));
		}
	}
}
