package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.widget.*;
import android.os.*;
import com.dhian.Utils;

public class CustomEditText extends EditText
{
	private int colorEditText;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";
	
	public CustomEditText(final Context context, AttributeSet attrs){
		super(context,attrs);
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorEditText = prefs.getInt("warnaEditText",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorEditText = intent.getIntExtra("warnaTema", Utils.setColor());
										 CustomEditText.this.getBackground().setColorFilter(colorEditText,PorterDuff.Mode.SRC_IN);
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaEditText", colorEditText);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));


		this.getBackground().setColorFilter(colorEditText, PorterDuff.Mode.SRC_IN);
		
		
	}

	
}
