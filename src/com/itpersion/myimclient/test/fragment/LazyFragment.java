package com.itpersion.myimclient.test.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class LazyFragment extends BaseFragment {
	private boolean isInit = false;
	private Bundle savedInstanceState;
	public static final String INTENT_BOOLEAN_LAZYLOAD = "intent_boolean_lazyLoad";
	private boolean isLazyLoad = true;
	private FrameLayout layout;
	private boolean isStart = false;
	@Deprecated
	protected final void onCreateView(Bundle savedInstanceState) {
		super.onCreateView(savedInstanceState);
		Bundle bundle = getArguments();
		if (bundle != null) {
			isLazyLoad = bundle.getBoolean(INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
		}
		if (isLazyLoad) {
			if (getUserVisibleHint() && !isInit) {
				isInit = true;
				this.savedInstanceState = savedInstanceState;
				onCreateViewLazy(savedInstanceState);
			} else {
				layout = new FrameLayout(getApplicationContext());
				layout.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				super.setContentView(layout);
			}

		} else {
			isInit = true;
			onCreateViewLazy(savedInstanceState);
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		Log.d("config", "可见度判断");
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && !isInit && getContentView() != null) {
			//第一个fragment,isVisibleToUser=true,!isInit=true,(getContentView()!=null)是false;
			//第二个fragment,isVisibleToUser=false,!isInit=true,(getContentView()!=null)是false;
			//第三个fragment,isVisibleToUser=false,!isInit=true,(getContentView()!=null)是false;
            //第四个fragment,isVisibleToUser=false,!isInit=true,(getContentView()!=null)是false;
			isInit = true;
			onCreateViewLazy(savedInstanceState);
			onResumeLazy();
		}
		if (isInit && getContentView() != null) {
			//第一个fragment,isInit=false,(getContentView()!=null)是false;
			//第二个fragment,isInit=false,(getContentView()!=null)是false;
			//第三个fragment,isInit=false,(getContentView()!=null)是false;
            //第四个fragment,isInit=false,(getContentView()!=null)是false;
			if (isVisibleToUser) {
				isStart = true;
				onFragmentStartLazy();
			} else {
				isStart = false;
				onFragmentStopLazy();
			}
		}
	}

	@Deprecated
	@Override
	public final void onStart() {
		super.onStart();
		if (isInit && !isStart && getUserVisibleHint()) {
			isStart = true;
			onFragmentStartLazy();
		}
	}

	@Deprecated
	@Override
	public final void onStop() {
		super.onStop();
		if (isInit && isStart && getUserVisibleHint()) {
			isStart = false;
			onFragmentStopLazy();
		}
	}

	protected void onFragmentStartLazy() {

	}

	protected void onFragmentStopLazy() {

	}

	protected void onCreateViewLazy(Bundle savedInstanceState) {

	}

	protected void onResumeLazy() {

	}

	protected void onPauseLazy() {

	}

	protected void onDestroyViewLazy() {

	}

	@Override
	public void setContentView(int layoutResID) {
		if (isLazyLoad && getContentView() != null
				&& getContentView().getParent() != null) {
			layout.removeAllViews();
			View view = inflater.inflate(layoutResID, layout, false);
			layout.addView(view);
		} else {
			super.setContentView(layoutResID);
		}
	}

	@Override
	public void setContentView(View view) {
		if (isLazyLoad && getContentView() != null
				&& getContentView().getParent() != null) {
			layout.removeAllViews();
			layout.addView(view);
		} else {
			super.setContentView(view);
		}
	}

	@Override
	@Deprecated
	public final void onResume() {
		super.onResume();
		if (isInit) {
			onResumeLazy();
		}
	}

	@Override
	@Deprecated
	public final void onPause() {
		super.onPause();
		if (isInit) {
			onPauseLazy();
		}
	}

	@Override
	@Deprecated
	public final void onDestroyView() {
		super.onDestroyView();
		if (isInit) {
			onDestroyViewLazy();
		}
		isInit = false;
	}
}
