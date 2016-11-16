package com.itpersion.myimclient.fragment;

import android.os.Bundle;
import android.util.Log;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.test.fragment.LazyFragment;

public class TestFragment2 extends LazyFragment {

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.lay2);
		System.out.println("��ʼ��lay2");
	}
	@Override
	protected void onFragmentStartLazy() {
		super.onFragmentStartLazy();
		Log.d("config", "TestFragment2��onFragmentStartLazy");
	}

	@Override
	protected void onResumeLazy() {
		super.onResumeLazy();
		super.onFragmentStartLazy();
		Log.d("config", "TestFragment2��onResumeLazy");
	}
}
