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

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class AboutDialog {
	public static AlertDialog create(Context context) {
		final TextView message = new TextView(context);
		message.setPadding( 3, 3, 3, 3);
		// i.e.: R.string.dialog_message =>
		// "Test this dialog following the link to dtmilano.blogspot.com"
		ComponentName comp = new ComponentName(context, TrafficChecker.class);
		PackageInfo pinfo = null;
		try {
			pinfo = context.getPackageManager().getPackageInfo(
					comp.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String sMessage = String.format(context.getString(R.string.aboutdialogmessage), pinfo.versionName);
		final SpannableString s = new SpannableString(sMessage);
		Linkify.addLinks(s, Linkify.WEB_URLS);
		message.setText(s);
		message.setMovementMethod(LinkMovementMethod.getInstance());

		return new AlertDialog.Builder(context).setTitle(R.string.about)
				.setCancelable(true).setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton(R.string.ok, null).setView(message).create();
	}
}
