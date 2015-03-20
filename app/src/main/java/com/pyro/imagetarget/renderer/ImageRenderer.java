package com.pyro.imagetarget.renderer;

import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;

import javax.microedition.khronos.opengles.GL10;

import rajawali.Object3D;
import rajawali.SerializedObject3D;
import rajawali.animation.mesh.SkeletalAnimationObject3D;
import rajawali.animation.mesh.SkeletalAnimationSequence;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.methods.DiffuseMethod;
import rajawali.materials.methods.SpecularMethod;
import rajawali.materials.textures.Texture;
import rajawali.math.Quaternion;
import rajawali.math.vector.Vector3;
import rajawali.parser.LoaderOBJ;
import rajawali.parser.md5.LoaderMD5Anim;
import rajawali.parser.md5.LoaderMD5Mesh;
import rajawali.util.RajLog;
import rajawali.vuforia.RajawaliVuforiaRenderer;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.Toast;

import com.pyro.imagetarget.R;
import com.pyro.imagetarget.activity.ImageActivity;

public class ImageRenderer extends RajawaliVuforiaRenderer {
	private DirectionalLight mLight;
//	private SkeletalAnimationObject3D mBob;
	private Object3D mF22;
//	private Object3D mAndroid;
	private ImageActivity activity;
	private Object3D elephantObj;

	public ImageRenderer(Context context) {
		super(context);
		activity = (ImageActivity) context;
	}

	protected void initScene() {
		mLight = new DirectionalLight(.1f, 0, -1.0f);
		mLight.setColor(1.0f, 1.0f, 0.8f);
		mLight.setPower(1);

		getCurrentScene().addLight(mLight);

		try {
			//
			// -- Load Bob (model by Katsbits
			// http://www.katsbits.com/download/models/)
			//

//			LoaderMD5Mesh meshParser = new LoaderMD5Mesh(this,
//					R.raw.boblampclean_mesh);
//			meshParser.parse();
//			mBob = (SkeletalAnimationObject3D) meshParser
//					.getParsedAnimationObject();
//			mBob.setScale(2);
//
//			LoaderMD5Anim animParser = new LoaderMD5Anim("dance", this,
//					R.raw.boblampclean_anim);
//			animParser.parse();
//			mBob.setAnimationSequence((SkeletalAnimationSequence) animParser
//					.getParsedAnimationSequence());

			// ObjParser objParser = new ObjParser(mContext.getResources(),
			// mTextureManager, R.raw.myobject_obj);
			// objParser.parse();
			// BaseObject3D mObject = objParser.getParsedObject();
			// mObject.setLight(mLight);
			// addChild(mObject);

//			LoaderOBJ objParserNew = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.globe_complete_obj);
//			objParserNew.parse();
//			globeObj = objParserNew.getParsedObject();
//			globeObj.setRotation(45, 0, 45);
//			globeObj.setScale(40);
//			addChild(globeObj);
			
			LoaderOBJ objParserNew = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.elephant_obj);
			objParserNew.parse();
			elephantObj = objParserNew.getParsedObject();
			elephantObj.setRotation(45, 0, 45);
			elephantObj.setScale(40);
			addChild(elephantObj);
			
//			mBob.play();
//			mBob.setVisible(false);

			//
			// -- Load F22 (model by KuhnIndustries
			// http://www.blendswap.com/blends/view/67634)
			//

			GZIPInputStream gzi = new GZIPInputStream(mContext.getResources()
					.openRawResource(R.raw.f22));
			ObjectInputStream fis = new ObjectInputStream(gzi);
			SerializedObject3D serializedObj = (SerializedObject3D) fis
					.readObject();
			fis.close();

			mF22 = new Object3D(serializedObj);
			mF22.setScale(30);
			addChild(mF22);

			Material f22Material = new Material();
			f22Material.enableLighting(true);
			f22Material.setDiffuseMethod(new DiffuseMethod.Lambert());
			f22Material.addTexture(new Texture("f22Texture", R.drawable.f22));
			f22Material.setColorInfluence(0);

			mF22.setMaterial(f22Material);

			//
			// -- Load Android
			//

//			gzi = new GZIPInputStream(mContext.getResources().openRawResource(
//					R.raw.android));
//			fis = new ObjectInputStream(gzi);
//			serializedObj = (SerializedObject3D) fis.readObject();
//			fis.close();
//
//			mAndroid = new Object3D(serializedObj);
//			mAndroid.setScale(14);
//			addChild(mAndroid);

//			Material androidMaterial = new Material();
//			androidMaterial.enableLighting(true);
//			androidMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
//			androidMaterial.setSpecularMethod(new SpecularMethod.Phong());
//			mAndroid.setColor(0x00dd00);
//			mAndroid.setMaterial(androidMaterial);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void foundFrameMarker(int markerId, Vector3 position,
			Quaternion orientation) {

//		if (markerId == 0) {
//			mBob.setVisible(true);
//			mBob.setPosition(position);
//			mBob.setOrientation(orientation);
//		} else if (markerId == 1) {
//			mAndroid.setVisible(true);
//			mAndroid.setPosition(position);
//			mAndroid.setOrientation(orientation);
//		}
	}

	@Override
	protected void foundImageMarker(String trackableName, Vector3 position,
			Quaternion orientation) {
		activity.showStartScanButton();
		RajLog.d("objname"+ImageActivity.objName);
//		if (trackableName.equals("SamsungGalaxyS4")) {
//			mBob.setVisible(true);
//			mBob.setPosition(position);
//			mBob.setOrientation(orientation);
//			
//			
//			RajLog.d(activity.getMetadataNative());
//		}
		if (trackableName.equals("stones")) {
//			mF22.setVisible(true);
//			mF22.setPosition(position);
//			mF22.setOrientation(orientation);
			if(activity.getObjName().equalsIgnoreCase("Elephant"))
			{
			elephantObj.setVisible(true); 
			
			elephantObj.setPosition(position);
			elephantObj.setOrientation(orientation); 
			}
			else if(activity.getObjName().equalsIgnoreCase("Jet"))
			{
				mF22.setVisible(true);
				mF22.setPosition(position);
				mF22.setOrientation(orientation);
			}
		}
		if (trackableName.equals("muthoot")) {
//			mF22.setVisible(true);
//			mF22.setPosition(position);
//			mF22.setOrientation(orientation);
			elephantObj.setVisible(true);
			elephantObj.setPosition(position);
			elephantObj.setOrientation(orientation);
			System.out.println("muthoot found");
		}
		if (trackableName.equals("himalayas")) {
//			mF22.setVisible(true);
//			mF22.setPosition(position);
//			mF22.setOrientation(orientation);
			elephantObj.setVisible(true);
			elephantObj.setPosition(position);
			elephantObj.setOrientation(orientation);
			System.out.println("himalayas found");
		}
		// -- also handle cylinder targets here
		// -- also handle multi-targets here
	}

	@Override
	public void noFrameMarkersFound() {
	}

	public void onDrawFrame(GL10 glUnused) {
//		mBob.setVisible(false);
		mF22.setVisible(false);
//		mAndroid.setVisible(false);
		elephantObj.setVisible(false);

		super.onDrawFrame(glUnused);

//		if (!activity.getScanningModeNative()) {
			
			activity.showStartScanButton();
//		}
	}
}
