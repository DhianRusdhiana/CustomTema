package com.dhian.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.content.*;
import com.dhian.Utils;

/**
 * User: eluleci
 * Date: 23.10.2013
 * Time: 22:18
 */
public class CustomButton extends Button {


	private int colorButton;
	private SharedPreferences prefs;
	private String prefName = "custom_tema";

    // default values of specific attributes
    private int bottom = 0;

    public CustomButton(final Context context, AttributeSet attrs) {
        super(context, attrs);
        
		
		prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
		colorButton = prefs.getInt("warnaButton",Utils.setColor());
		context.registerReceiver((BroadcastReceiver) new BroadcastReceiver() {

									 public void onReceive(Context context2, Intent intent) {
										 colorButton = intent.getIntExtra("warnaTema", Utils.setColor());
										 init();
										 prefs = getContext().getSharedPreferences(prefName, getContext().MODE_PRIVATE);
										 SharedPreferences.Editor editor = prefs.edit();
										 editor.putInt("warnaButton", colorButton);
										 editor.commit();
									 }
								 }, new IntentFilter("GANTI_TEMA"));
		init();
    }


    @Override
    protected void onDraw(final Canvas canvas) {

        super.onDraw(canvas);
    }

    private void init() {

        // saving padding values for using them after setting background drawable
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingLeft = getPaddingLeft();
        final int paddingBottom = getPaddingBottom();

        int buttonColor;
		
		buttonColor = colorButton;
        

        // creating normal state drawable
        ShapeDrawable normalFront = new ShapeDrawable(new RoundRectShape(getRadius(), null, null));
        normalFront.getPaint().setColor(buttonColor);

        ShapeDrawable normalBack = new ShapeDrawable(new RoundRectShape(getRadius(), null, null));
        normalBack.getPaint().setColor(buttonColor);

        normalBack.setPadding(0, 0, 0, bottom);

        Drawable[] d = {normalBack, normalFront};
        LayerDrawable normal = new LayerDrawable(d);

        // creating pressed state drawable
        ShapeDrawable pressedFront = new ShapeDrawable(new RoundRectShape(getRadius(), null, null));
        pressedFront.getPaint().setColor(0x50ffffff);

        ShapeDrawable pressedBack = new ShapeDrawable(new RoundRectShape(getRadius(), null, null));
        pressedBack.getPaint().setColor(buttonColor);
        if (bottom != 0) pressedBack.setPadding(0, 0, 0, bottom / 2);

        Drawable[] d2 = {pressedBack, pressedFront};
        LayerDrawable pressed = new LayerDrawable(d2);

        // creating disabled state drawable
        ShapeDrawable disabledFront = new ShapeDrawable(new RoundRectShape(getRadius(), null, null));
        disabledFront.getPaint().setColor(0xff9e9e9e);
        disabledFront.getPaint().setAlpha(0xA0);

        ShapeDrawable disabledBack = new ShapeDrawable(new RoundRectShape(getRadius(), null, null));
        disabledBack.getPaint().setColor(0xff9e9e9e);

        Drawable[] d3 = {disabledBack, disabledFront};
        LayerDrawable disabled = new LayerDrawable(d3);

        StateListDrawable states = new StateListDrawable();


		states.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        states.addState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, pressed);
        states.addState(new int[]{android.R.attr.state_enabled}, normal);
        states.addState(new int[]{-android.R.attr.state_enabled}, disabled);

        setBackgroundDrawable(states);
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);


    }

	private float[] getRadius(){
		return new float[]{0,0,0,0,0,0,0,0};
	}


}
