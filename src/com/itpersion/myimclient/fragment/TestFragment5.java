package com.itpersion.myimclient.fragment;

import uk.co.senab.photoview.PhotoViewAttacher;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.activity.ShowImage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class TestFragment5 extends Fragment {
	private View rootView;
    private ImageView btn_click;
    private PhotoViewAttacher mAttacher;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			rootView = inflater.inflate(R.layout.lay1, container, false);
			btn_click=(ImageView)rootView.findViewById(R.id.iv_tf);
			  // Set the Drawable displayed
		    Drawable bitmap = getResources().getDrawable(R.drawable.activity_splashbg);
		    btn_click.setImageDrawable(bitmap);
		    // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
		    mAttacher = new PhotoViewAttacher(btn_click);
	/*		btn_click=(Button)rootView.findViewById(R.id.btn_click);
			btn_click.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
                      	Intent intent=new Intent();
                      	intent.setClass(getActivity(), ShowImage.class);
                      	startActivity(intent);
				}
			});*/
		}
		return rootView;
	}
	@Override
	public void onResume() {
		super.onResume();
		Log.d("config", "onResume加载fragment5");
	}
	@Override
	public void onStart() {
		Log.d("config", "onStart加载fragment5");
		super.onStart();
	}
	@Override
	public void onPause() {
		Log.d("config", "销毁F5onDestroy");
		super.onPause();
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
    @Override
    public void onDestroy() {
    	Log.d("config", "销毁F5onDestroy");
    	super.onDestroy();
    }
	@Override
	public void onDetach() {
		Log.d("config", "销毁F5onDetach");
		super.onDetach();
	}
}
