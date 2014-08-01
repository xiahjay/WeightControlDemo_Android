package com.anko.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.anko.R;
import com.anko.base.GetNetData;
import com.anko.base.MyFragmentPagerAdapter;
import com.anko.base.MyGallery;
import com.anko.base.RemoteImageHelper;






import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleAdapter;

public class IndexActivity extends FragmentActivity {
	
	
	//用于下面点点指示
	private LinearLayout ll_focus_indicator_container = null;
	//gallery的id
    private MyGallery gallery = null;
    //存储图片
    RemoteImageHelper lazyImageHelper = new RemoteImageHelper();
    List<Map<String, String>> list = new ArrayList<Map<String,  String>>(); // 图片缓存
    public boolean timeFlag = true;
    //屏幕宽度
	WindowManager windowManager;
    int sw,sh;
    
    MyAdapter myAdapter;
    /**
     * 存储上一个选择项的Index
     */
    private int preSelImgIndex = 0;
    
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine;
    private RadioButton tvTabActivity, tvTabGroups, tvTabFriends, tvTabChat;

    private int currIndex = 0;
    private int position_one;
    private int position_two;
    private int position_three;
	//@Override
	//protected void onCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.activity_index);
		
		//resources = getResources();
		//windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		//屏幕宽度
		//sw = windowManager.getDefaultDisplay().getWidth();
		//屏幕高度
		//sh = windowManager.getDefaultDisplay().getHeight();
		//initView();

		//ll_focus_indicator_container = (LinearLayout) findViewById(R.id.ll_focus_indicator_container);
		//InitFocusIndicatorContainer();
		//gallery = (MyGallery) findViewById(R.id.banner_gallery);
		//gallery.setAdapter(myAdapter);
		//gallery.setFocusable(true);
		
		//gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			//public void onItemSelected(AdapterView<?> arg0, View arg1,
					//int selIndex, long arg3) {
				// 修改上一次选中项的背景
				//selIndex = selIndex % list.size();

				//ImageView preSelImg = (ImageView) ll_focus_indicator_container
						//.findViewById(preSelImgIndex);
				//preSelImg.setImageDrawable(IndexActivity.this.getResources()
						//.getDrawable(R.drawable.ic_focus));
				// 修改当前选中项的背景
				//ImageView curSelImg = (ImageView) ll_focus_indicator_container
						//.findViewById(selIndex);
				//curSelImg.setImageDrawable(IndexActivity.this.getResources()
					//	.getDrawable(R.drawable.ic_focus_select));
				//preSelImgIndex = selIndex;
			//}

		//	public void onNothingSelected(AdapterView<?> arg0) {
			//}
		//});
		
		//InitWidth();
        //InitTextView();
        //InitViewPager();		
	//}
	
   /* private void InitWidth() {
    	//获取宽度
        ivBottomLine = (ImageView) findViewById(R.id.iv_bottom_line);
        position_one = (int) (sw / 4.0);
        LayoutParams para;
        para = ivBottomLine.getLayoutParams();     
        para.width=position_one;
        para.height= 2;      
        ivBottomLine.setLayoutParams(para);
        position_two = position_one * 2;
        position_three = position_one * 3;
    }*/
    
	/*private void InitTextView() {
		//tvTabActivity = (RadioButton) findViewById(R.id.tv_tab_activity);
		//tvTabGroups = (RadioButton) findViewById(R.id.tv_tab_groups);
		//tvTabFriends = (RadioButton) findViewById(R.id.tv_tab_friends);
		//tvTabChat = (RadioButton) findViewById(R.id.tv_tab_chat);		

		//tvTabActivity.setOnClickListener(new MyOnClickListener(0));
		//tvTabGroups.setOnClickListener(new MyOnClickListener(1));
		//tvTabFriends.setOnClickListener(new MyOnClickListener(2));
		//tvTabChat.setOnClickListener(new MyOnClickListener(3));
	}*/

    /*private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();
        Fragment activityfragment = TestFragment.newInstance("Hello Activity.");
        Fragment groupFragment = TestFragment.newInstance("Hello Group.");
        Fragment friendsFragment=TestFragment.newInstance("Hello Friends.");
        Fragment chatFragment=TestFragment.newInstance("Hello Chat.");

        fragmentsList.add(activityfragment);
        fragmentsList.add(groupFragment);
        fragmentsList.add(friendsFragment);
        fragmentsList.add(chatFragment);
        
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
      
    }*/
    

    
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};
    
    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:

                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, 0, 0, 0);                 
                    tvTabGroups.setTextColor(getResources().getColor(R.color.black));
                    tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
					tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(
									getResources().getDrawable(R.drawable.special_icon), null,null, null);

                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, 0, 0, 0);                 
                    tvTabFriends.setTextColor(getResources().getColor(R.color.black));
                    tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.recommend_icon), null,null, null);                                      
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, 0, 0, 0);                  
                    tvTabChat.setTextColor(getResources().getColor(R.color.black));
                    tvTabChat.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabChat.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.tuan_icon), null,null, null);                 
                }                
                tvTabActivity.setTextColor(getResources().getColor(R.color.pink));
                tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
				tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(
						getResources().getDrawable(R.drawable.fashion_icon_s),null, null, null);			          
                break;
            case 1:
            	
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_one, 0, 0);                 
                    tvTabActivity.setTextColor(getResources().getColor(R.color.black));
                    tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.fashion_icon),null, null, null);
                    
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two,position_one, 0, 0);                 
                    tvTabFriends.setTextColor(getResources().getColor(R.color.black));
                    tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.recommend_icon), null,null, null);                                                        
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_one, 0, 0);                  
                    tvTabChat.setTextColor(getResources().getColor(R.color.black));
                    tvTabChat.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabChat.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.tuan_icon), null,null, null);                     
                }
                tvTabGroups.setTextColor(getResources().getColor(R.color.pink));               
                tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
				tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(
						getResources().getDrawable(R.drawable.special_icon_s),null, null, null);                              
                break;
            case 2:           	
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_two, 0, 0);                 
                    tvTabActivity.setTextColor(getResources().getColor(R.color.black));
                    tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.fashion_icon),null, null, null);
                    
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_two, 0, 0);                 
                    tvTabGroups.setTextColor(getResources().getColor(R.color.black));
                    tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
					tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(
									getResources().getDrawable(R.drawable.special_icon), null,null, null);
                   
                } else if (currIndex == 3) {
                    animation = new TranslateAnimation(position_three, position_two, 0, 0);                  
                    tvTabChat.setTextColor(getResources().getColor(R.color.black));
                    tvTabChat.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabChat.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.tuan_icon), null,null, null);   
                    
                }
                tvTabFriends.setTextColor(getResources().getColor(R.color.pink));
                tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(
                		getResources().getDrawable(R.drawable.recommend_icon_s),null, null, null);               
                break;
            case 3:
            	
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, position_three, 0, 0);                 
                    tvTabActivity.setTextColor(getResources().getColor(R.color.black));
                    tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabActivity.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.fashion_icon),null, null, null);
                    
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, position_three, 0, 0);
                    tvTabGroups.setTextColor(getResources().getColor(R.color.black));
                    tvTabGroups.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.fashion_icon),null, null, null);
                    tvTabGroups.setButtonDrawable(R.drawable.special_icon);
                    
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(position_two, position_three, 0, 0);                 
                    tvTabFriends.setTextColor(getResources().getColor(R.color.black));
                    tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    tvTabFriends.setCompoundDrawablesWithIntrinsicBounds(
                    		getResources().getDrawable(R.drawable.recommend_icon), null,null, null);   
                    
                }
                tvTabChat.setTextColor(getResources().getColor(R.color.pink));
                tvTabChat.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                tvTabChat.setCompoundDrawablesWithIntrinsicBounds(
                		getResources().getDrawable(R.drawable.tuan_icon_s),null, null, null);
               
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }
        


        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
	private void initView(){
		
		String[] from = {"img","name","url"};
		int[] to ={R.id.gallery_image,R.id.gallery_text,R.id.gallery_url};
		myAdapter = new MyAdapter(this,getCate(),R.layout.item,from,to);
	}
	
	private void InitFocusIndicatorContainer() {
		
		for (int i = 0; i < list.size(); i++) {
			ImageView localImageView = new ImageView(this);
			localImageView.setId(i);
			ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
			localImageView.setScaleType(localScaleType);
			LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
					15, 15);
			localImageView.setLayoutParams(localLayoutParams);
			localImageView.setPadding(5, 5, 5, 5);
			localImageView.setImageResource(R.drawable.ic_focus);
			localImageView.setBackgroundColor(getResources().getColor(R.color.trans));
			this.ll_focus_indicator_container.addView(localImageView);
		}
	}
	


    public List<Map<String,String>> getCate(){
    	    	    	
    	String url = "http://www.ankobeauty.com/my800/index.php/Index/Index/aad";
		try {
			String jsonstring = GetNetData.getResultForHttpGet(url);
			JSONObject result = new JSONObject(jsonstring);
			JSONArray cateList = result.getJSONArray("adlist");	
			int length = cateList.length();
			for(int i=0 ;i<length;i++){
				Map<String, String> map = new HashMap<String, String>();
				JSONObject oj = cateList.getJSONObject(i);                
                map.put("img", "http://www.ankobeauty.com"+oj.getString("img"));  
                map.put("name", oj.getString("name"));
                map.put("url", oj.getString("url"));
                list.add(map);                            
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		

    }

   private class MyAdapter extends SimpleAdapter{
	   
		public MyAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view =super.getView(position, convertView, parent);
			Map<String,Object> record = (Map<String, Object>) getItem(position);
			final String checkUrl = record.get("url").toString();
			ImageView imageView = (ImageView) view.findViewById(R.id.gallery_image);
			lazyImageHelper.loadImage(imageView, record.get("img").toString(), false);
			 LayoutParams params = imageView.getLayoutParams();
		     int imgWidth = sw;
		     int imgHeight =  (int) Math.ceil( (imgWidth * 240) / 640);
		     params.width = imgWidth;
		     params.height=imgHeight;  		     
		     imageView.setLayoutParams(params);  
			 imageView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					Intent it = new Intent();
					Intent intent = new Intent(IndexActivity.this,GraphicActivity.class);
					intent.putExtra("url", checkUrl);	
					startActivity(intent);	
				}
				
			});
            return view;		
		}
		
   }
   
	private void link(String url) {

		Intent intent = new Intent(Intent.ACTION_VIEW);

		intent.setData(Uri.parse(url));

		startActivity(intent);

   }


}
