package com.itpersion.myimclient.fragment;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.test.fragment.LazyFragment;

public class TestFragment1 extends LazyFragment {
	private ImageView mImageView;
	private PhotoViewAttacher mAttacher;
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.show_image);
		mImageView = (ImageView) findViewById(R.id.iv_photo);
	    // Set the Drawable displayed
	    Drawable bitmap = getResources().getDrawable(R.drawable.activity_splashbg);
	    mImageView.setImageDrawable(bitmap);
	    // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
	    
		System.out.println("初始化lay1");
	}

	@Override
	protected void onFragmentStartLazy() {
		super.onFragmentStartLazy();
		Drawable bitmap = getResources().getDrawable(R.drawable.activity_splashbg);
	    mImageView.setImageDrawable(bitmap);
		//可见的时候调用
		mAttacher = new PhotoViewAttacher(mImageView);
		
		Log.d("config", "TestFragment1的onFragmentStartLazy");
	}

	@Override
	protected void onResumeLazy() {
		super.onResumeLazy();
		super.onFragmentStartLazy();
		Log.d("config", "TestFragment1的onResumeLazy");
	}
}
