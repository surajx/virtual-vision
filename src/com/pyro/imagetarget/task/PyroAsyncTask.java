package com.pyro.imagetarget.task;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.pyro.imagetarget.activity.TaskCallBack;

public class PyroAsyncTask extends AsyncTask<String, String, String> {
	ProgressDialog progressDialog;
	TaskCallBack taskCallBack;
	String title;
	String description;

	public PyroAsyncTask(TaskCallBack taskCallBack) {
		this.taskCallBack = taskCallBack;
	}

	@Override
	protected void onPreExecute() {
		// progressDialog = ProgressDialog
		// .show(activity, title, description, true);

	}

	@Override
	protected String doInBackground(String... url) {
		// TODO Auto-generated method stub
		String urlLink = url[0];
		System.out.println(urlLink);
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(urlLink);
		String jsonString = null;
		try {

			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();
			jsonString = getASCIIContentFromEntity(entity);
		} catch (Exception e) {
			return e.getLocalizedMessage();

		}
		return jsonString;
	}

	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {

		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		// progressDialog.dismiss();
		taskCallBack.done(result);
		super.onPostExecute(result);
	}
}
