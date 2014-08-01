package com.anko.ui;


import com.anko.R;
import com.anko.base.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LoadActivity extends BaseActivity {
	
	private ImageView mLoadItem_iv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_load);
		mHandler = new Handler(getMainLooper());
		findViewById();
		initView();
	}

	protected void findViewById() {
		// TODO Auto-generated method stub
		mLoadItem_iv = (ImageView) findViewById(R.id.splash_loading_item);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		Animation translate = AnimationUtils.loadAnimation(this,
				R.anim.splash_loading);
		
		translate.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				//openActivity(LoginActivity.class);
				Intent intent = new Intent(LoadActivity.this,LoginActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				LoadActivity.this.finish();
			}
			
		});
		mLoadItem_iv.setAnimation(translate);
		
	}

}
