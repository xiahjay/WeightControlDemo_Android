package com.anko.ui;

import com.anko.R;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class HomeActivity extends TabActivity {
	
	public static final String TAG = HomeActivity.class.getSimpleName();
	public final static String EXTRA_MESSAGE = "com.anko.ui.MESSAGE";
	private RadioGroup mTabButtonGroup;
	private TabHost mTabHost;

	public static final String TAB_MAIN = "MAIN_ACTIVITY";
	public static final String TAB_SEARCH = "SEARCH_ACTIVITY";
	public static final String TAB_CATEGORY = "CATEGORY_ACTIVITY";
	public static final String TAB_CHART = "CHART_ACTIVITY";
	public static final String TAB_PERSONAL = "PERSONAL_ACTIVITY";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		findViewById();
		initView();
	}

	private void findViewById() {
		mTabButtonGroup = (RadioGroup) findViewById(R.id.home_radio_button_group);
	}
	
	private void initView() {

		mTabHost = getTabHost();

		Intent i_main = new Intent(this, IndexActivity.class);
		Intent i_search = new Intent(this, SearchActivity.class);
		Intent i_category = new Intent(this, LoginActivity.class);
		Intent i_cart = new Intent(this, ChartActivity.class);
		Intent i_personal = new Intent(this, PersonalActivity.class);

		mTabHost.addTab(mTabHost.newTabSpec(TAB_MAIN).setIndicator(TAB_MAIN)
				.setContent(i_main));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_SEARCH)
				.setIndicator(TAB_SEARCH).setContent(i_search));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CATEGORY)
				.setIndicator(TAB_CATEGORY).setContent(i_category));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_CHART).setIndicator(TAB_CHART)
				.setContent(i_cart));
		mTabHost.addTab(mTabHost.newTabSpec(TAB_PERSONAL)
				.setIndicator(TAB_PERSONAL).setContent(i_personal));

		mTabHost.setCurrentTabByTag(TAB_PERSONAL);

		mTabButtonGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
				        //case R.id.home_tab_main:
							//mTabHost.setCurrentTabByTag(TAB_MAIN);
							//break;

						case R.id.home_tab_search:
							mTabHost.setCurrentTabByTag(TAB_SEARCH);							
							
							break;

						case R.id.home_tab_category:
							mTabHost.setCurrentTabByTag(TAB_CHART);
							break;
							/*LinearLayout loginLayout = (LinearLayout) getLayoutInflater().inflate(
									R.layout.activity_category, null);
							Dialog dialog = new AlertDialog.Builder(HomeActivity.this)  
	                        .setTitle("添加体重") .setView(loginLayout)  
	                        .setPositiveButton("确定",  
	                       new DialogInterface.OnClickListener() {  
	                       @Override  
	                      public void onClick(DialogInterface dialog,int which) {  
	                                             	                       	  
	                         AlertDialog ad = (AlertDialog)dialog;  
	                	     EditText editWeight = (EditText) ad.findViewById(R.id.edit_weight);	                			  
	                         String weightString=editWeight.getText().toString().trim();	                        
	                                //settingIdealWeight.setText(idealweightString+"kg");
	                                    }  
	                       }).setNegativeButton("取消",
	                			new DialogInterface.OnClickListener(){
	        					public void onClick(DialogInterface dialog,int whichButton) {
	        					// 取消用户登录，退出程序

	        					        }
	        						}).show();// 创建出一个“确定”按钮  
	                // 启动此对话框并且显示在屏幕上  */
	                  
							

						case R.id.home_tab_personal:
							mTabHost.setCurrentTabByTag(TAB_PERSONAL);
							break;

						default:
							break;
						}
					}
				});
	}
}

