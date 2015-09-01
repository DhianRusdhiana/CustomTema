package com.dhian.customview;

import android.support.v7.widget.SwitchCompat;
import android.content.*;
import android.util.*;
import android.graphics.drawable.*;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;
import com.dhian.Utils;

public class CustomSwitchCompat extends SwitchCompat
{
	private int colorThumb;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";

    public CustomSwitchCompat(final Context context, AttributeSet attrs) {
		super(context, attrs);
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorThumb = prefs.getInt("warnaSwitch",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

										  public void onReceive(Context context2, Intent intent) {
											  colorThumb = intent.getIntExtra("warnaTema", Utils.setColor());
                                              setChecked(isChecked());
											  prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
											  SharedPreferences.Editor editor = prefs.edit();
											  editor.putInt("warnaSwitch", colorThumb);
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
			int thumbColor;
			int trackColor;
			
	        


			if(isChecked) {
				thumbColor = colorThumb;
				trackColor = 80 * 0x01000000;			
			} else {
				thumbColor = Color.argb(255, 236, 236, 236);
				trackColor = 0xff9e9e9e;
							}

			try {
				getThumbDrawable().setColorFilter(thumbColor, PorterDuff.Mode.MULTIPLY);
				getTrackDrawable().setColorFilter(trackColor+thumbColor, PorterDuff.Mode.MULTIPLY);
				
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}

