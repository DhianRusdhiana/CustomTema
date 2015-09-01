package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.widget.*;
import android.os.*;
import com.dhian.Utils;

public class CustomLinearLayout extends LinearLayout
{
	private int colorLinearLayout;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";
	
	public CustomLinearLayout(final Context context, AttributeSet attrs){
		super(context,attrs);
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorLinearLayout = prefs.getInt("warnaLinearLayout",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorLinearLayout = intent.getIntExtra("warnaTema", Utils.setColor());
										 CustomLinearLayout.this.setBackgroundColor(colorLinearLayout);
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaLinearLayout", colorLinearLayout);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));
		this.setBackgroundColor(colorLinearLayout);
	}
	
	
}
