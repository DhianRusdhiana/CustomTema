package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.widget.*;
import android.os.*;
import com.dhian.Utils;

public class CustomProgressBar extends ProgressBar
{
	private int colorProgressbar;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";
	
	public CustomProgressBar(final Context context, AttributeSet attrs){
		super(context, attrs);
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorProgressbar = prefs.getInt("warnaProgressBar",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorProgressbar = intent.getIntExtra("warnaTema", Utils.setColor());
										 CustomProgressBar.this.getIndeterminateDrawable().setColorFilter(colorProgressbar,PorterDuff.Mode.SRC_IN);
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaProgressBar", colorProgressbar);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));
		
		
		this.getIndeterminateDrawable().setColorFilter(colorProgressbar, PorterDuff.Mode.SRC_IN);
		
	}
}
