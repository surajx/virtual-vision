package com.pyro.imagetarget.activity;





import rajawali.util.RajLog;
import rajawali.vuforia.RajawaliVuforiaActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.pyro.imagetarget.R;
import com.pyro.imagetarget.renderer.ImageRenderer;

public class ImageActivity extends RajawaliVuforiaActivity {
	private ImageRenderer mRenderer;
	private RajawaliVuforiaActivity mUILayout;
    private Button mStartScanButton;
    public static String objName="Jet";
    private Integer[] Imgid = {
            R.drawable.skull100,R.drawable.skull100,R.drawable.skull100
    };
public String getObjName() {
	return objName;
}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//useCloudRecognition(false);
		//setCloudRecoDatabase("93b551782a44a47d2d8c49df0eddc9d428f73109", "3a2c80ab60e7f306c1a2e4dfc50d01b6c786b89b");
		Intent intent = getIntent();
		objName = intent.getStringExtra("Obj");
startVuforia();

		
		
	}

	@Override
	protected void setupTracker() {
		int result = initTracker(TRACKER_TYPE_MARKER);
		if (result == 1) {
			result = initTracker(TRACKER_TYPE_IMAGE);
			if (result == 1) {
				super.setupTracker();
			} else {
				RajLog.e("Couldn't initialize image tracker.");
			}
		} else {
			RajLog.e("Couldn't initialize marker tracker.");
		}
	}
	
	@Override
	protected void initApplicationAR()
	{
		super.initApplicationAR();
		
		createFrameMarker(1, "Marker1", 50, 50);
		createFrameMarker(2, "Marker2", 50, 50);
		
		createImageMarker("StonesAndChips.xml");
		
		// -- this is how you add a cylinder target:
		//    https://developer.vuforia.com/resources/dev-guide/cylinder-targets
		// createImageMarker("MyCylinderTarget.xml");
		
		// -- this is how you add a multi-target:
		//    https://developer.vuforia.com/resources/dev-guide/multi-targets
		// createImageMarker("MyMultiTarget.xml");
	}
	
    public void showStartScanButton()
    {
        this.runOnUiThread(new Runnable() {
                public void run() {
                    if  (mStartScanButton != null)
                        mStartScanButton.setVisibility(View.VISIBLE);
                 }
         });
    }

	@Override
	protected void initRajawali() {
		super.initRajawali();
		mRenderer = new ImageRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		super.setRenderer(mRenderer);
		
//	    Add button for Cloud Reco:
        mStartScanButton = new Button(this);
        mStartScanButton.setText("Start Scanning CloudReco");
         
        mStartScanButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    enterScanningModeNative();
                     mStartScanButton.setVisibility(View.GONE);
                 }   
        });
        
        mUILayout = this;
      
        mUILayout.addContentView(mStartScanButton, 
            new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));	
        mStartScanButton.setVisibility(View.VISIBLE);
        
       
        
		 
	}    
	 public class AddImgAdp extends BaseAdapter {
	        int GalItemBg;
	        private Context cont;

	        public AddImgAdp(Context c) {
	            cont = c;
	            
	        }

	        public int getCount() {
	            return Imgid.length;
	        }

	        public Object getItem(int position) {
	            return position;
	        }

	        public long getItemId(int position) {
	            return position;
	        }

	        public View getView(int position, View convertView, ViewGroup parent) {
	            ImageView imgView = new ImageView(cont);

	            imgView.setImageResource(Imgid[position]);
	           
	            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
	            imgView.setBackgroundResource(GalItemBg);

	            return imgView;
	        }
	    }
}
