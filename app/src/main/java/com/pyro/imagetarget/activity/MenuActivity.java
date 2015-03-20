package com.pyro.imagetarget.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pyro.imagetarget.R;
import com.pyro.imagetarget.task.PyroAsyncTask;

public class MenuActivity extends Activity implements TaskCallBack {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ImageButton btnStart = (ImageButton) findViewById(R.id.btnScan);
		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PyroAsyncTask pyroAsyncTask = new PyroAsyncTask(
						MenuActivity.this);
				String url = getString(R.string.url);
				pyroAsyncTask.execute(url, "dfgdf", "dfgdf");
				Intent i = new Intent(MenuActivity.this, ImageActivity.class);
				startActivity(i);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void done(String json) {

		Toast.makeText(getApplicationContext(), json, 2000).show();
		System.out.println(json);
	}

}
