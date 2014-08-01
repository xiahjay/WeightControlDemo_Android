package com.anko.ui;

import javax.security.auth.PrivateCredentialPermission;

import com.anko.R;
import com.anko.R.layout;
import com.anko.R.menu;

import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.text.StaticLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

public class PersonalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal, menu);
		return true;
	}
	public void onClickInformation(View v){
		LinearLayout InformationLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.personal_information, null);
		
		AlertDialog nameDialog = new AlertDialog.Builder(this)
		.setTitle("修改昵称")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(InformationLayout)
		.setPositiveButton("确定", new OnClickListener() {
			 @Override  
             public void onClick(DialogInterface dialog,int which) {  
                         // 取消对话框  
				AlertDialog ad = (AlertDialog)dialog;  
			    EditText editName = (EditText) ad.findViewById(R.id.edit_name);
			    TextView settingName = (TextView) findViewById(R.id.setting_name);			    
                String nameString=editName.getText().toString().trim();               
                settingName.setText(nameString);                            
                     }  			
		})
		.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).create();
		nameDialog.show();
	}
	public void onClickAge(View v){
		LinearLayout InformationLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.age, null);
		
		new AlertDialog.Builder(this)
		.setTitle("修改年龄")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(InformationLayout)
		.setPositiveButton("确定", new OnClickListener() {
			 @Override  
             public void onClick(DialogInterface dialog,int which) {  
                         // 取消对话框  
				AlertDialog ad = (AlertDialog)dialog;  
			    EditText editAge = (EditText) ad.findViewById(R.id.edit_age);			   
			    TextView settingAge = (TextView) findViewById(R.id.setting_age);			    
                String ageString=editAge.getText().toString().trim();              
                settingAge.setText(ageString);        
                     }  			
		})
		.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	
	public void onClickHeight(View v){
		LinearLayout InformationLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.height, null);
		
		new AlertDialog.Builder(this)
		.setTitle("修改身高")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(InformationLayout)
		.setPositiveButton("确定", new OnClickListener() {
			 @Override  
             public void onClick(DialogInterface dialog,int which) {  
                         // 取消对话框  
				AlertDialog ad = (AlertDialog)dialog;  
			    EditText editHeight = (EditText) ad.findViewById(R.id.edit_height);
			    TextView settingHeight = (TextView) findViewById(R.id.setting_height);
                String heightString=editHeight.getText().toString().trim();
                settingHeight.setText(heightString+"cm");
                     }  			
		})
		.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	
	public void onClickRealWeight(View v){
		LinearLayout InformationLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.real_weight, null);
		
		new AlertDialog.Builder(this)
		.setTitle("修改实际体重")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(InformationLayout)
		.setPositiveButton("确定", new OnClickListener() {
			 @Override  
             public void onClick(DialogInterface dialog,int which) {  
                         // 取消对话框  
				AlertDialog ad = (AlertDialog)dialog;  
			    EditText editRealWeight = (EditText) ad.findViewById(R.id.edit_real_weight);
			    TextView settingRealWeight = (TextView) findViewById(R.id.setting_real_weight);
                String realweightString=editRealWeight.getText().toString().trim();
                settingRealWeight.setText(realweightString+"kg");
                     }  			
		})
		.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	
	public void onClickIdealWeight(View v){
		LinearLayout InformationLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.ideal_weight, null);
		
		new AlertDialog.Builder(this)
		.setTitle("修改理想体重")
		.setIcon(android.R.drawable.ic_dialog_info)
		.setView(InformationLayout)
		.setPositiveButton("确定", new OnClickListener() {
			 @Override  
             public void onClick(DialogInterface dialog,int which) {  
                         // 取消对话框  
				AlertDialog ad = (AlertDialog)dialog;  
			    EditText editIdealWeight = (EditText) ad.findViewById(R.id.edit_ideal_weight);
			    TextView settingIdealWeight = (TextView) findViewById(R.id.setting_ideal_weight);
                String idealweightString=editIdealWeight.getText().toString().trim();
                settingIdealWeight.setText(idealweightString+"kg");
                     }  			
		})
		.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		}).show();
	}
	
	public void onClickExit(View v){
		Intent intent = new Intent(PersonalActivity.this,LoginActivity.class);
		startActivity(intent);
		
	}
}
