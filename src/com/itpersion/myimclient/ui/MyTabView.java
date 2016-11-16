package com.itpersion.myimclient.ui;

import com.itpersion.myimclient.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class MyTabView extends View {

	public MyTabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyTabView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public MyTabView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Bitmap mBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.icon_info);
		canvas.drawBitmap(mBitmap, 0, 0, null);
		
	}

}
