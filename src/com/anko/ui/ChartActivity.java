package com.anko.ui;

import java.util.ArrayList;

import com.anko.R;
import com.anko.R.layout;
import com.anko.R.menu;

import android.os.Bundle;
import android.R.integer;
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

public class ChartActivity extends Activity implements OnItemSelectedListener, OnItemClickListener {
	
	private static ArrayList<Float> dataList;
	private ArrayAdapter<String> aaData;
	private ListView lvCommonListView;
	//private static String settingWeight = new String();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		lvCommonListView = (ListView) findViewById(R.id.lvCommonListView);
		dataList=new ArrayList<Float>();
		//dataList.add(settingWeight);
		//dataList.add(58.9f);
		//dataList.add(58.4f);
		
		refreshListView();
		lvCommonListView.setOnItemClickListener(this);
		lvCommonListView.setOnItemSelectedListener(this);

	}
	private void refreshListView() {
		// TODO Auto-generated method stub
		String[] data=convertArralyListToArray();
		aaData = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		
		lvCommonListView.setAdapter(aaData);			
		
	}

	private String[] convertArralyListToArray() {
		// TODO Auto-generated method stub
		String[] dataStrings;
		dataStrings=new String[dataList.size()];
		for (int i=0;i<dataList.size();i++){
			dataStrings[i]=Float.toString(dataList.get(i))+"kg";
			//dataStrings[i]=dataList.get(i)+"kg";
		}
		return dataStrings;
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
         Float currentWeight=Float.valueOf(settingWeight);
         dataList.add(currentWeight);
 		 refreshListView();
        
                    }  
       }).setNegativeButton("取消",
			new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int whichButton) {
			// 取消用户登录，退出程序

			        }
				}).show();
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
