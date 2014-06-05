package com.service;

import java.lang.reflect.Field;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.mod.bar.drawertools.MODrawerLayout;
import com.service.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class BaseActivity extends SherlockFragmentActivity {

	private final String data[] = {"Seccion 1","Seccion 2"};
	private MODrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	final int DEVICE_VERSION   = Build.VERSION.SDK_INT;
	final int DEVICE_HONEYCOMB = Build.VERSION_CODES.HONEYCOMB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		// drawer set
		
		mDrawerList = (ListView)findViewById(R.id.left_drawer );
		
		
		mDrawerLayout = (MODrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerToggle = new DIActionBarDrawerToggle(
				this, 
				mDrawerLayout, 
				R.drawable.ic_drawer, 
				R.string.drawer_open, 
				R.string.drawer_close);
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// actionbar set
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		// item set
		insertItemToDrawerListView();
		
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
	         
            @Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            		switchActivity(position);

            }
        }); 
		
		//Force actionbar overflow menu for Android 3.x (honeycomb) and upper,
		if (DEVICE_VERSION >= DEVICE_HONEYCOMB){
			try {
		        ViewConfiguration config = ViewConfiguration.get(this);
		        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
		        if(menuKeyField != null) {
		            menuKeyField.setAccessible(true);
		            menuKeyField.setBoolean(config, false);
		        }
		    } catch (Exception ex) {
		        // Ignore
		    }
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
	   inflater.inflate(R.menu.main, menu);
	   return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (DEVICE_VERSION < DEVICE_HONEYCOMB) {
	        if (event.getAction() == KeyEvent.ACTION_UP &&
	            keyCode == KeyEvent.KEYCODE_MENU) {
	        	if (this.mDrawerLayout.isDrawerVisible(MODrawerLayout.DRAWER_POSITION))
					this.mDrawerLayout.closeDrawer(MODrawerLayout.DRAWER_POSITION);
				else {
					this.mDrawerLayout.openDrawer(MODrawerLayout.DRAWER_POSITION);
				}
	            return true;
	        }
	    }
	    return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if ((item != null) && (item.getItemId() == 16908332) && (mDrawerToggle.isDrawerIndicatorEnabled())) {
			if (this.mDrawerLayout.isDrawerVisible(MODrawerLayout.DRAWER_POSITION))
				this.mDrawerLayout.closeDrawer(MODrawerLayout.DRAWER_POSITION);
			else {
				this.mDrawerLayout.openDrawer(MODrawerLayout.DRAWER_POSITION);
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private class DIActionBarDrawerToggle extends ActionBarDrawerToggle {
		public DIActionBarDrawerToggle(
				Activity activity, DrawerLayout drawerLayout, int drawerImageRes, 
				int openDrawerContentDescRes, int closeDrawerContentDescRes) {
			super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes,closeDrawerContentDescRes);
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			super.onDrawerOpened(drawerView);
			getSupportActionBar().setTitle("Google Bi");
		}

		@Override
		public void onDrawerClosed(View drawerView) {
			super.onDrawerClosed(drawerView);
			getSupportActionBar().setTitle(R.string.app_name);
		}
	}
	
	private void insertItemToDrawerListView() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.menu_item, data);
		mDrawerList.setAdapter(adapter);
	}
	
	 public void switchActivity(int position){
			
			
			switch(position){
			case 0:
				
				break;
			case 1:
				
				break;
			
			
			}
	 }

	 protected void onPostExecute() {
		// TODO Auto-generated method stub
		
 }

	
	
	
}
