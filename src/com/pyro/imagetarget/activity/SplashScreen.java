package com.pyro.imagetarget.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.pyro.imagetarget.R;

public class SplashScreen extends Activity {
	 private static int SPLASH_TIME_OUT = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		 new Handler().postDelayed(new Runnable() {
			 
	            /*
	             * Showing splash screen with a timer. 
	             */
	 
	            @Override
	            public void run() {
	                // This method will be executed once the timer is over
	                // Start menu activity
	                Intent i = new Intent(SplashScreen.this, ModelListActivity.class);
	                startActivity(i);
	                // close this activity
	                finish();
	            }
	        }, SPLASH_TIME_OUT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
