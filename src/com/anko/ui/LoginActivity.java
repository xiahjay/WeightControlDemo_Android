package com.anko.ui;



import com.anko.R;
import com.anko.R.layout;
import com.anko.R.menu;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText mAccount;
	private EditText mPwd;
	private Button mRegisterButton;
	private Button mLoginButton;
	private Button mCancleButton;
	private View loginView;
	private View loginSuccessView;
	private TextView loginSuccessShow;
	private UserDataManager mUserDataManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		mAccount = (EditText) findViewById(R.id.login_edit_account);
		mPwd = (EditText) findViewById(R.id.login_edit_pwd);
		mRegisterButton = (Button) findViewById(R.id.login_btn_register);
		mLoginButton = (Button) findViewById(R.id.login_btn_login);
		mCancleButton = (Button) findViewById(R.id.login_btn_cancle);
		//loginView=findViewById(R.id.login_view);
		//loginSuccessView=findViewById(R.id.login_success_view);
		//loginSuccessShow=(TextView) findViewById(R.id.login_success_show);

		mRegisterButton.setOnClickListener(mListener);
		mLoginButton.setOnClickListener(mListener);
		mCancleButton.setOnClickListener(mListener);
		
		  
        }

	OnClickListener mListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_btn_register:
				register();
				break;
			case R.id.login_btn_login:
				login();
				break;
			case R.id.login_btn_cancle:
				cancle();
				break;
			}
		}
	};

	public void login() {
		if (isUserNameAndPwdValid()) {
			String userName = mAccount.getText().toString().trim();
			String userPwd = mPwd.getText().toString().trim();
			int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
			//int id=mUserDataManager.findIDByName(userName);
			//WeightDAO.getInstance().setUserId(id);
			if(result==1){
				//login success
				//mUserDataManager.openDatabase();
				//String id=mUserDataManager.findIDByName(userName);
				//mUserDataManager.closeDatabase();
				WeightDAO.getInstance().setUserId(userName);
				Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
				startActivity(intent);
				
				//loginView.setVisibility(View.GONE);
				//loginSuccessView.setVisibility(View.VISIBLE);
				//loginSuccessShow.setText(getString(R.string.user_login_success, userName));
				Toast.makeText(this, getString(R.string.login_success),
						Toast.LENGTH_SHORT).show();
			}else if(result==0){
				//login failed,user does't exist
				Toast.makeText(this, getString(R.string.login_fail),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	public void register() {
		if (isUserNameAndPwdValid()) {
			String userName = mAccount.getText().toString().trim();
			String userPwd = mPwd.getText().toString().trim();
			//check if user name is already exist
			int count=mUserDataManager.findUserByName(userName);
			
			if(count>0){
				Toast.makeText(this, getString(R.string.name_already_exit, userName),
						Toast.LENGTH_SHORT).show();
				return ;
			}
			
			UserData mUser = new UserData(userName, userPwd);
			mUserDataManager.openDatabase();
			long flag = mUserDataManager.insertUserData(mUser);
			if (flag == -1) {
				Toast.makeText(this, getString(R.string.register_fail),
						Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, getString(R.string.register_success),
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void cancle() {
		mAccount.setText("");
		mPwd.setText("");
	}

	public boolean isUserNameAndPwdValid() {
		if (mAccount.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.account_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (mPwd.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.pwd_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	@Override
	protected void onResume() {
		if (mUserDataManager == null) {
			mUserDataManager = new UserDataManager(this);
			mUserDataManager.openDatabase();
        }
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mUserDataManager != null) {
			mUserDataManager.closeDatabase();
			mUserDataManager = null;
        }
		super.onPause();
	}
}