package com.anko.ui;

import java.util.ArrayList;
import java.util.List;

import com.anko.R;
import com.anko.R.layout;
import com.anko.R.menu;
import com.anko.ui.HomeDiagram;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.StaticLayout;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SearchActivity extends Activity {
	
	LinearLayout arc;
    RelativeLayout pillars,linear;
  
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		LinearLayout arc;
	    RelativeLayout pillars,linear;
	    List<Integer> lists = new ArrayList<Integer>();//绾挎�у浘  鑼冨洿10-100
		for (int i = 0; i < 20; i++) {
			
				lists.add(getRandom(0, 500));
				}
		linear= (RelativeLayout) findViewById(R.id.linear);
		linear.addView(new HomeDiagram(this,lists));
			
		}
		
	

	public int getRandom(int min,int max){
		return (int) Math.round(Math.random()*(max-min)+min);
	}
}
