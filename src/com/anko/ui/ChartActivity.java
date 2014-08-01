package com.anko.ui;

import com.anko.R;
import com.anko.R.layout;
import com.anko.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChartActivity extends Activity  {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cart, menu);
		return true;
	}
	
	public void onClickAddWeight(View v){
		LinearLayout loginLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.activity_category, null);
		AlertDialog dialog = new AlertDialog.Builder(ChartActivity.this)  
        .setTitle("添加体重") .setView(loginLayout)  
        .setPositiveButton("确定",  
       new DialogInterface.OnClickListener() {  
       @Override  
      public void onClick(DialogInterface dialog,int which) {  
                             	                       	  
         AlertDialog ad = (AlertDialog)dialog;  
	     EditText editWeight = (EditText) ad.findViewById(R.id.edit_weight);	                			  
         String settingWeight=editWeight.getText().toString().trim();	                        
                //settingIdealWeight.setText(idealweightString+"kg");
                    }  
       }).setNegativeButton("取消",
			new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int whichButton) {
			// 取消用户登录，退出程序

			        }
				}).show();
		
	}

	

}
