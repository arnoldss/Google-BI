package com.mod.bar.drawertools;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

public class MODrawerLayout extends DrawerLayout {
	
	public final static int DRAWER_POSITION = Gravity.LEFT; 

	public MODrawerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(isDrawerOpen(DRAWER_POSITION)) {
			return super.onTouchEvent(ev);
		}
		return false;
	}
	
	@Override
	public boolean onInterceptHoverEvent(MotionEvent event) {
		if(isDrawerOpen(DRAWER_POSITION)) {
			return super.onInterceptHoverEvent(event);
		}
		return false;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(isDrawerOpen(DRAWER_POSITION)) {
			return super.onInterceptTouchEvent(ev);
		}
		return false;
	}
}
