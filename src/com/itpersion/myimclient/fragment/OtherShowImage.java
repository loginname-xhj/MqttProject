package com.itpersion.myimclient.fragment;

import uk.co.senab.photoview.PhotoViewAttacher;

import com.itpersion.myimclient.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class OtherShowImage extends Activity {
	private ImageView mImageView;
	private PhotoViewAttacher mAttacher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_image);
		mImageView = (ImageView) findViewById(R.id.iv_photo);
	    // Set the Drawable displayed
	    Drawable bitmap = getResources().getDrawable(R.drawable.temp_show);
	    mImageView.setImageDrawable(bitmap);
	    // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
	    mAttacher = new PhotoViewAttacher(mImageView);
	}
}
