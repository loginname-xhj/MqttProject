package com.itpersion.myimclient.fragment;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.test.fragment.LazyFragment;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestFragment4 extends LazyFragment {
	private Button btn_lay4;
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.lay4);
		System.out.println("≥ı ºªØlay4");
		btn_lay4=(Button)findViewById(R.id.btn_lay4);
		btn_lay4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                       				
			}
		});
	}
}
