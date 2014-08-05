package com.anko.ui;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.anko.R;
import com.anko.R.layout;
import com.anko.R.menu;

import android.os.Bundle;
import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChartActivity extends Activity implements OnItemSelectedListener, OnItemClickListener, OnDateChangedListener {
	
	private static ArrayList<Float> dataList;
	private static ArrayList<String> datelist;
	private ArrayAdapter<String> aaData;
	private ListView lvCommonListView;
	private DatePicker datePicker;
	private String getdate;
	private UserDataManager mUserDataManager;
	//public static int  id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
		lvCommonListView = (ListView) findViewById(R.id.lvCommonListView);
		dataList=new ArrayList<Float>();
		datelist=new ArrayList<String>();
		if (mUserDataManager == null) {
			mUserDataManager = new UserDataManager(this);
			mUserDataManager.openDatabase();
			}
		String id=WeightDAO.getInstance().getUseId();
		ArrayList<Weight> weightList=mUserDataManager.getWeightList(id);
		for(int i=0;i<weightList.size();i++){
			Weight weight= weightList.get(i);
			dataList.add(weight.getUserWeight());
			datelist.add(weight.getUserDate());	
		}
		/*id= mUserDataManager.getRowNumber();
		for(int i=1; i<id+1; i++ ){
			String weight= new String();
			String date = new String();	
			weight=mUserDataManager.getStringByColumnName("user_weight",i);
			date=mUserDataManager.getStringByColumnName("user_date",i);
			Float a= Float.valueOf(weight);
			dataList.add(a);
			datelist.add(date);	
		}*/
		//importExitedData();
		refreshListView();
		lvCommonListView.setOnItemClickListener(this);
		lvCommonListView.setOnItemSelectedListener(this);
		mUserDataManager.closeDatabase();
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
			dataStrings[i]=datelist.get(i)+": "+Float.toString(dataList.get(i))+"kg";
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
         datePicker = (DatePicker) ad.findViewById(R.id.datepicker);
	     EditText editWeight = (EditText) ad.findViewById(R.id.edit_weight);	                			  
         String settingWeight=editWeight.getText().toString().trim();	
         Float currentWeight=Float.valueOf(settingWeight);
         dataList.add(currentWeight);
         onDateChanged(null, 0, 0, 0);
        mUserDataManager.openDatabase();
        mUserDataManager.insertWeightData(currentWeight,getdate);
        mUserDataManager.closeDatabase();
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
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth)
	{
		Calendar calendar = Calendar.getInstance();

		calendar.set(datePicker.getYear(),datePicker.getMonth(), datePicker
				.getDayOfMonth());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		getdate=sdf.format(calendar.getTime());
		datelist.add(getdate);
	}

	

}
