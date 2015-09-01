package com.dhian;

import android.content.*;

public class Utils
{
	static Context vv;
	public Utils(Context cc){
		vv = cc;
	}
	
	
	private static int defaultColor = 0xff0070ff;
	
	public static int setColor(){
		return defaultColor;
	}
	
	public static int getID(String name, String Type) {
		return vv.getResources().getIdentifier(name, Type, vv.getPackageName());
	}
}
