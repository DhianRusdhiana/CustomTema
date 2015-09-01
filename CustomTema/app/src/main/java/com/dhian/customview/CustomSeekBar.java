package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.widget.*;
import android.os.*;
import com.dhian.Utils;

public class CustomSeekBar extends SeekBar
{
	private int colorSeekBar;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";
	
	public CustomSeekBar(final Context context, AttributeSet attrs){
		super(context,attrs);
		
        prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorSeekBar = prefs.getInt("warnaSeekBar",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorSeekBar = intent.getIntExtra("warnaTema", Utils.setColor());
										 CustomSeekBar.this.getProgressDrawable().setColorFilter(colorSeekBar,PorterDuff.Mode.SRC_IN);
										 CustomSeekBar.this.getThumb().setColorFilter(colorSeekBar, PorterDuff.Mode.SRC_IN);
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaSeekBar", colorSeekBar);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));
		
		
		this.getProgressDrawable().setColorFilter(colorSeekBar,PorterDuff.Mode.SRC_IN);
		this.getThumb().setColorFilter(colorSeekBar, PorterDuff.Mode.SRC_IN);
	}
}
