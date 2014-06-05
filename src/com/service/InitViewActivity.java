package com.service;

import com.service.R;

import android.app.Activity;
import android.os.Bundle;

public class InitViewActivity extends Activity {
	
//	private Handler handler = new Handler();
//	
//	private Runnable runnable = new Runnable() {
//		   @Override
//		   public void run() {
//			   Intent intent = new Intent(InitViewActivity.this,MainActivity.class);
//			   startActivity(intent);
//			    handler.removeCallbacks(runnable);
//			    finish();
//
//			   return;
//		   }
//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
//		handler.postDelayed(runnable, 3000);
		super.onResume();
	}



	@Override
	public void onBackPressed() {
		
//	    handler.removeCallbacks(runnable);
	    
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
//		handler.removeCallbacks(runnable);
		super.onPause();
	}

	@Override
	protected void onStop() {
//		handler.removeCallbacks(runnable);
		super.onStop();
	}
	

}