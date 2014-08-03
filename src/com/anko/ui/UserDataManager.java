package com.anko.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.anko.R;
//import com.example.headdiary.data.Config.DBConfig;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class UserDataManager {
	private final int BUFFER_SIZE = 400000;
	private static final String TAG = "UserDataManager";
	//private static final String DB_NAME = "user_data";
	private static final String TABLE_NAME = "login_information";
	public static final String ID = "_id";

	public static final String USER_NAME = "user_name";
	public static final String USER_PWD = "user_pwd";
	public static final String SILENT = "silent";
	public static final String VIBRATE = "vibrate";
	public static final String DB_PATH = "/sdcard";
	public static final String DB_NAME = "weightcontroldemo"; //保存的数据库文件名
	public static final String DB_FULLNAME = DB_PATH + "/" + DB_NAME;

	private static final int DB_VERSION = 2;
	private Context mContext = null;

	private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
			+ ID + " integer primary key," + USER_NAME + " nvarchar,"
			+ USER_PWD + " varchar" + ");";

	private SQLiteDatabase mSQLiteDatabase = null;
	
	//private DataBaseManagementHelper mDatabaseHelper = null;

	/*private static class DataBaseManagementHelper extends SQLiteOpenHelper {

		DataBaseManagementHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
	
			Log.i(TAG,"db.getVersion()="+db.getVersion());
			//db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
			//db.execSQL(DB_CREATE);
			Log.i(TAG, "db.execSQL(DB_CREATE)");
			Log.e(TAG, DB_CREATE);
		}

		

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i(TAG, "DataBaseManagementHelper onUpgrade");
			onCreate(db);
		}
	}*/

	public UserDataManager(Context context) {
		mContext = context;
		Log.i(TAG, "UserDataManager construction!");
	}

	public void openDatabase() {
		
		 this. mSQLiteDatabase = this.openDatabase(DB_PATH + "/" + DB_NAME); 
	}

	  private SQLiteDatabase openDatabase(String dbfile) { 
	        try { 
	            if (!(new File(dbfile).exists())) {
	            	//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库 
	                InputStream is = this.mContext.getResources().openRawResource( 
	                        R.raw.weightcontroldemo); //欲导入的数据库 
	                FileOutputStream fos = new FileOutputStream(dbfile); 
	                byte[] buffer = new byte[BUFFER_SIZE]; 
	                int count = 0; 
	                while ((count = is.read(buffer)) > 0) { 
	                    fos.write(buffer, 0, count); 
	                } 
	                fos.close(); 
	                is.close(); 
	            } 
	            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, 
	                    null); 
	            return db; 
	        } catch (FileNotFoundException e) { 
	            Log.e("Database", "File not found"); 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            Log.e("Database", "IO exception"); 
	            e.printStackTrace(); 
	        } 
	        return null; 
	    } 
	
	  public void closeDatabase() {
	        this. mSQLiteDatabase.close();
	    }

	public long insertUserData(UserData userData) {
		
		String userName=userData.getUserName();
		String userPwd=userData.getUserPwd();

		ContentValues values = new ContentValues();
		values.put(USER_NAME, userName);
		values.put(USER_PWD, userPwd);
		return mSQLiteDatabase.insert(TABLE_NAME, ID, values);
	}

	public boolean updateUserData(UserData userData) {

		int id = userData.getUserId();
		String userName = userData.getUserName();
		String userPwd = userData.getUserPwd();

		ContentValues values = new ContentValues();
		values.put(USER_NAME, userName);
		values.put(USER_PWD, userPwd);
		return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
	}

	public Cursor fetchUserData(int id) throws SQLException {

		Cursor mCursor = mSQLiteDatabase.query(false, TABLE_NAME, null, ID
				+ "=" + id, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor fetchAllUserDatas() {

		return mSQLiteDatabase.query(TABLE_NAME, null, null, null, null, null,
				null);
	}

	public boolean deleteUserData(int id) {

		return mSQLiteDatabase.delete(TABLE_NAME, ID + "=" + id, null) > 0;
	}

	public boolean deleteAllUserDatas() {

		return mSQLiteDatabase.delete(TABLE_NAME, null, null) > 0;
	}


	public String getStringByColumnName(String columnName, int id) {
		Cursor mCursor = fetchUserData(id);
		int columnIndex = mCursor.getColumnIndex(columnName);
		String columnValue = mCursor.getString(columnIndex);
		mCursor.close();
		return columnValue;
	}

	//Download by http://www.codefans.net
	public boolean updateUserDataById(String columnName, int id,
			String columnValue) {
		ContentValues values = new ContentValues();
		values.put(columnName, columnValue);
		return mSQLiteDatabase.update(TABLE_NAME, values, ID + "=" + id, null) > 0;
	}
	
	public int findUserByName(String userName){
		Log.i(TAG,"findUserByName , userName="+userName);
		int result=0;
		Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME+"="+userName, null, null, null, null);
		if(mCursor!=null){
			result=mCursor.getCount();
			mCursor.close();
			Log.i(TAG,"findUserByName , result="+result);
		}
		return result;
	}
	
	
	
	public int findUserByNameAndPwd(String userName,String pwd){
		Log.i(TAG,"findUserByNameAndPwd");
		int result=0;
		Cursor mCursor=mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME+"="+userName+" and "+USER_PWD+"="+pwd,
				null, null, null, null);
		if(mCursor!=null){
			result=mCursor.getCount();
			mCursor.close();
			Log.i(TAG,"findUserByNameAndPwd , result="+result);
		}
		return result;
	}
}
