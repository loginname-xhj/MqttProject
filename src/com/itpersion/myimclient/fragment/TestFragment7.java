package com.itpersion.myimclient.fragment;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.activity.ShowImage;
import com.itpersion.myimclient.activity.ShowImageView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class TestFragment7 extends Fragment {
	private View rootView;
	private Button btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			rootView = inflater.inflate(R.layout.lay3, container,
					false);
			btn=(Button)rootView.findViewById(R.id.btn_view);
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
                  	intent.setClass(getActivity(), ShowImageView.class);
                  	startActivity(intent);
				}
			});
		}
		return rootView;
	}
}
