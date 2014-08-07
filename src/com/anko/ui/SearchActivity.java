package com.anko.ui;

import java.util.ArrayList;
import java.util.Timer;  
import java.util.TimerTask;  
  
import org.achartengine.ChartFactory;   
import org.achartengine.GraphicalView;  
import org.achartengine.chart.PointStyle;   
import org.achartengine.model.XYMultipleSeriesDataset;   
import org.achartengine.model.XYSeries;   
import org.achartengine.renderer.XYMultipleSeriesRenderer;   
import org.achartengine.renderer.XYSeriesRenderer;   

import com.anko.R;
//import android.R;
import com.anko.R.layout;
import com.anko.R.menu;

import android.R.integer;
import android.app.Activity;   
import android.content.Context;  
import android.graphics.Color;   
import android.graphics.Paint.Align;  
import android.os.Bundle;   
import android.os.Handler;  
import android.os.Message;  
import android.util.Log;
import android.view.ViewGroup.LayoutParams;  
import android.widget.LinearLayout;  
  
public class SearchActivity extends Activity {  
      
    private Timer timer = new Timer();  
    private TimerTask task;  
    private Handler handler;  
    private String title = "Signal Strength";  
    private XYSeries series;  
    private XYMultipleSeriesDataset mDataset;  
    private GraphicalView chart;  
    private XYMultipleSeriesRenderer renderer;  
    private Context context;  
    private int addX = -1, addY;  
    private UserDataManager mUserDataManager;
      
    int[] xv = new int[10];  
    int[] yv = new int[10];  
    
    
      
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_graphic);
        series = new XYSeries(title); 
        mDataset = new XYMultipleSeriesDataset();
        
          
    }  
    
    private void initGraph() {
		// TODO Auto-generated method stub
    	context = getApplicationContext();  
        
        //这里获得main界面上的布局，下面会把图表画在这个布局里面  
        LinearLayout layout = (LinearLayout)findViewById(R.id.linear);  
        //series = new XYSeries(title); 
        mDataset.removeSeries(series);
        series.clear();
        if (mUserDataManager == null) {
			mUserDataManager = new UserDataManager(this);
		} 
        //这个类用来放置曲线上的所有点，是一个点的集合，根据这些点画出曲线         
        //ArrayList<Float> weightList= new  ArrayList<Float>();
       // String id= WeightDAO.getInstance().getUseId();
        //weightList=mUserDataManager.getWeightData(id);
        ArrayList<Weight> weightList= new  ArrayList<Weight>();
        String id= WeightDAO.getInstance().getUseId();
        mUserDataManager.openDatabase();
        weightList=mUserDataManager.getWeightList(id);
        mUserDataManager.closeDatabase();
        
        for(int i=0; i<weightList.size(); i++){
        	Weight weight=weightList.get(i);
        	Float fy= weight.getUserWeight();
        	String a= weight.getUserDate();
        	String suba=a.substring(8, 10);
        	float x=Float.parseFloat(suba);
        	float y=fy.floatValue();
        	series.add(x, y);
        	}
        
        //创建一个数据集的实例，这个数据集将被用来创建图表  
       // mDataset = new XYMultipleSeriesDataset();  
          
        //将点集添加到这个数据集中  
        mDataset.addSeries(series);  
          
        //以下都是曲线的样式和属性等等的设置，renderer相当于一个用来给图表做渲染的句柄  
        int color = Color.RED;  
        PointStyle style = PointStyle.DIAMOND;  
        renderer = buildRenderer(color, style, true);  
          
        //设置好图表的样式  
        setChartSettings(renderer, "X", "Y", 0, 31, 30, 100, Color.BLACK, Color.BLACK);  
          
        //生成图表  
        chart = ChartFactory.getLineChartView(context, mDataset, renderer);  
          
        //将图表添加到布局中去  
        layout.addView(chart, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));  
          
        //这里的Handler实例将配合下面的Timer实例，完成定时更新图表的功能  
        /*handler = new Handler() {  
            @Override  
            public void handleMessage(Message msg) {  
                //刷新图表  
                updateChart();  
                super.handleMessage(msg);  
            }  
        };  
          
        task = new TimerTask() {  
            @Override  
            public void run() {  
                Message message = new Message();  
                message.what = 1;  
                handler.sendMessage(message);  
            }  
        };  
          
        timer.schedule(task, 500, 500);  */
	}

	@Override
    protected void onResume(){
    	super.onResume();
    	Log.d("onResume", "onResume Method is executed");
    	initGraph();
    }
    
    
    

	@Override  
    public void onDestroy() {  
        //当结束程序时关掉Timer  
        timer.cancel();  
        super.onDestroy();  
    }  
      
    protected XYMultipleSeriesRenderer buildRenderer(int color, PointStyle style, boolean fill) {  
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();  
          
        //设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等  
        XYSeriesRenderer r = new XYSeriesRenderer();  
        r.setColor(color);  
        r.setPointStyle(style);  
        r.setFillPoints(fill);  
        r.setLineWidth(3);  
        renderer.addSeriesRenderer(r);  
          
        return renderer;  
    }  
      
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String xTitle, String yTitle,  
                                    double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {  
        //有关对图表的渲染可参看api文档  
    	//renderer.setApplyBackgroundColor(false);
       // renderer.setChartTitle("8月体重变化趋势");  
        renderer.setXTitle(xTitle);  
        renderer.setYTitle(yTitle);  
        renderer.setXAxisMin(xMin);  
        renderer.setXAxisMax(xMax);  
        renderer.setYAxisMin(yMin);  
        renderer.setYAxisMax(yMax);  
        renderer.setAxesColor(axesColor);  
        //render.setLabelsColor(Color.BLACK);
        renderer.setLabelsColor(labelsColor);  
        renderer.setLabelsColor(Color.BLACK);
        renderer.setShowGrid(true);  
        renderer.setGridColor(Color.GRAY);  
        renderer.setXLabels(20);  
        renderer.setYLabels(10);  
        renderer.setXTitle("日期"); 
        renderer.setYTitle("体重");  
        renderer.setMarginsColor(Color.argb(0, 0xff, 0, 0));
        renderer.setBackgroundColor(Color.WHITE);
        //renderer.setMarginsColor(Color.GRAY);
        //renderer.setLabelsColor(Color.WHITE);
        renderer.setMargins(new int[] {50, 40, 40,20});
        renderer.setChartTitleTextSize((float) 40);
        renderer.setAxisTitleTextSize((float) 20);
        renderer.setLabelsTextSize((float) 30);
        renderer.setYLabelsAlign(Align.RIGHT);  
        renderer.setPointSize((float) 8);  
        renderer.setShowLegend(false);  
        renderer.setPanLimits(new double[]{0,31,0,200});
        renderer.setZoomLimits(new double[]{0,31,0,200});
        
    }  
}
    /*private void updateChart() {  
          
        //设置好下一个需要增加的节点  
        addX = 0;  
        addY = (int)(Math.random() * 90);  
          
        //移除数据集中旧的点集  
        mDataset.removeSeries(series);  
          
        //判断当前点集中到底有多少点，因为屏幕总共只能容纳100个，所以当点数超过100时，长度永远是100  
        int length = series.getItemCount();  
        if (length > 100) {  
            length = 100;  
        }  
          
        //将旧的点集中x和y的数值取出来放入backup中，并且将x的值加1，造成曲线向右平移的效果  
        for (int i = 0; i < length; i++) {  
            xv[i] = (int) series.getX(i) + 1;  
            yv[i] = (int) series.getY(i);  
        }  
          
        //点集先清空，为了做成新的点集而准备  
        series.clear();  
          
        //将新产生的点首先加入到点集中，然后在循环体中将坐标变换后的一系列点都重新加入到点集中  
        //这里可以试验一下把顺序颠倒过来是什么效果，即先运行循环体，再添加新产生的点  
        series.add(addX, addY);  
        for (int k = 0; k < length; k++) {  
            series.add(xv[k], yv[k]);  
        }  
          
        //在数据集中添加新的点集  
        mDataset.addSeries(series);  
          
        //视图更新，没有这一步，曲线不会呈现动态  
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api  
        chart.invalidate();  
    }  
}  */