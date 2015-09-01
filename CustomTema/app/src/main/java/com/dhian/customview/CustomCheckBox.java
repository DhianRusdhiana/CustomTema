package com.dhian.customview;

import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.widget.*;
import android.os.*;
import com.dhian.Utils;
import com.dhian.R;

public class CustomCheckBox extends CheckBox
{
	private int colorCheckBox;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";
	
	public CustomCheckBox(final Context context, AttributeSet attrs){
		super(context,attrs);
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorCheckBox = prefs.getInt("warnaCheckBox",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorCheckBox = intent.getIntExtra("warnaTema", Utils.setColor());
										 setChecked(isChecked());
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaCheckBox", colorCheckBox);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));
		
		
		setChecked(isChecked());
	}
	
	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		
		
		changeColor(checked);
	}

	private void changeColor(boolean isChecked) {
		if (Build.VERSION.SDK_INT >= 16) {
			int checkBoxColor = colorCheckBox;
			Resources res = getResources();
            Drawable d = res.getDrawable(getID("abc_btn_check_material","drawable"));
		 
			if(isChecked) {
				d.setColorFilter(checkBoxColor, PorterDuff.Mode.SRC_IN);
				
			} else {
				
				d.setColorFilter(0xff9e9e9e, PorterDuff.Mode.SRC_IN);
				
			}

			try {
				CustomCheckBox.this.setButtonDrawable(d);

			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	public int getID(String name, String Type) {
		return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
	}
}
