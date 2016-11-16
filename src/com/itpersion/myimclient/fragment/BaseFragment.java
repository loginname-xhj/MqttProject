package com.itpersion.myimclient.fragment;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
	public boolean isinitView_testFragment1;//预加载初始化
	public boolean isinitView_testFragment2;//预加载初始化
	public boolean isinitView_testFragment3;//预加载初始化
	public boolean isinitView_testFragment4;//预加载初始化
    /**
     * 
    * @Title: InItUI 
    * @Description: 加载数据
     */
	public abstract void InItUI();
}
