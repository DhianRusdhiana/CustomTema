package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.widget.*;
import android.os.*;
import android.view.*;
import com.dhian.Utils;

public class CustomImageView extends ImageView
{
	private int colorImageView;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";
	
	public CustomImageView(final Context context, AttributeSet attrs){
		super(context,attrs);
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorImageView = prefs.getInt("warnaImageView",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorImageView = intent.getIntExtra("warnaTema", Utils.setColor());
										 CustomImageView.this.setColorFilter(colorImageView,PorterDuff.Mode.SRC_IN);
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaImageView", colorImageView);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));
		this.setColorFilter(colorImageView,PorterDuff.Mode.SRC_IN);
	}

	
}
