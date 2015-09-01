package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.support.v7.widget.*;
import com.dhian.Utils;

public class CustomToolbar extends Toolbar
{
	private int warnaToolbar;
	private SharedPreferences prefs;
    private String prefName = "custom_tema";
	
	public CustomToolbar(final Context context, AttributeSet attrs){
		super(context, attrs);
		
		prefs = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        warnaToolbar = prefs.getInt("warnaToolbar",Utils.setColor());
		this.setBackgroundColor(warnaToolbar);
		
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

			public void onReceive(Context context2, Intent intent) {
				warnaToolbar = intent.getIntExtra("warnaTema", Utils.setColor());
				CustomToolbar.this.setBackgroundColor(warnaToolbar);
				prefs = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("warnaToolbar", warnaToolbar);
				editor.commit();
				}
			}, new IntentFilter("GANTI_TEMA"));
		
		
	}
}
