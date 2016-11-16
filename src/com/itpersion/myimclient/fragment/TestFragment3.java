package com.itpersion.myimclient.fragment;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.test.fragment.LazyFragment;

import android.os.Bundle;

public class TestFragment3 extends LazyFragment {

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.lay3);
		System.out.println("≥ı ºªØlay3");
	}
}
