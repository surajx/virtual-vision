package com.pyro.imagetarget.activity;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyro.imagetarget.R;

public class ItemsAdapter extends BaseAdapter {

	private final Context context;
	private ArrayList<String> items;
	private ModelListActivity modelListActivity;

	public ItemsAdapter(Context context, ArrayList<String> items,
			ModelListActivity modelListActivity) {
		this.context = context;
		this.items = items;
		this.modelListActivity = modelListActivity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_model, null);
		}

		ImageView iv = (ImageView) v.findViewById(R.id.logo);
		if (iv != null) {
			Bitmap bitmap = BitmapFactory.decodeResource(
					context.getResources(), R.drawable.skull100);
			iv.setImageBitmap(bitmap);
		}
		TextView tv = (TextView) v.findViewById(R.id.label);
		tv.setText(items.get(position));
		v.setOnClickListener(modelListActivity);
		return v;
	}

	@Override
	public int getCount() {
	return	items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

}
