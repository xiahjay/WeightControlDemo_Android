package com.anko.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MotionEvent;

public class MyDialog extends AlertDialog {

	protected MyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
     dismiss();
     return super.onTouchEvent(event);
}
}