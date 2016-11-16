package com.itpersion.myimclient.fragment;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.activity.ShowImage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class TestFragment6 extends Fragment {
	private View rootView;
	private Button btn_show_tt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			rootView = inflater.inflate(R.layout.lay2, container,
					false);
			btn_show_tt=(Button)rootView.findViewById(R.id.btn_show_tt);
			btn_show_tt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				  	Intent intent=new Intent();
                  	intent.setClass(getActivity(), OtherShowImage.class);
                  	startActivity(intent);
				}
			});
		}
		return rootView;
	}
}
