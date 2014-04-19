package com.pyro.imagetarget.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.pyro.imagetarget.R;
import com.pyro.imagetarget.R.id;
import com.pyro.imagetarget.R.layout;
import com.pyro.imagetarget.R.menu;

public class ModelListActivity extends Activity  implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model_list);
		ArrayList<String> itemList= new ArrayList<String>();
		itemList.add("Jet");
		itemList.add("Elephant");
		ListView listView= (ListView) findViewById(R.id.list);
		listView.setAdapter(new ItemsAdapter(getApplicationContext(), itemList,this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.model_list, menu);
		return true;
	}
	@Override
	public void onClick(View arg0) {
		Intent i = new Intent(ModelListActivity.this, ImageActivity.class);
		TextView tv = (TextView) arg0.findViewById(R.id.label);
		i.putExtra("Obj", tv.getText());
		startActivity(i);
		
	}

}
