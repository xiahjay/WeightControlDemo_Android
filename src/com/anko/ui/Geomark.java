package com.anko.ui;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Geomark extends SurfaceView implements SurfaceHolder.Callback {
	private int currentX;

	private int oldX;

	private SurfaceHolder sfh;

	private boolean isRunning = true;

	private static float[] temp = { -9, -10.2f, 1, 1, 1, 3, 8, 10, 11, 12, 15,
			14, 18, 12, 15, 17, 13, 15, 12, 14, 11, 12, 14, 17 };// 24涓俯搴﹀��
	// private static float[] temp = { 29, 26, 25, 20, 20, 20, 26, 32, 36, 22,
	// 25,
	// 24, 28, 22, 20, 27, 23, 28, 29, 32, 21, 22, 24, 37 };
	private String[] houres = { "00", "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23" };// 涓�澶╃殑鏃堕棿24H

	private int tick = 10; // 鏃堕棿闂撮殧(ms)
	private int bottom = 150; // 鍧愭爣绯诲湴娈佃窛绂绘鏋堕《绔殑璺濈
	private int top = 10; // 鍧愭爣绯婚《绔窛绂绘鏋堕《绔鐨勮窛绂�
	private int lift = 30; // 鍧愭爣绯诲乏杈硅窛绂绘鏋跺乏杈规鐨勮窛绂�
	static int right; // 鍧愭爣绯诲彸杈硅窛绂绘鏋跺乏杈圭殑璺濈(!)
	static int gapX; // 涓ゆ牴绔栫嚎闂寸殑闂撮殭(!)
	private int gapY = 20; // 涓ゆ牴妯嚎闂寸殑闂撮殭

	public Geomark(Context context) {
		super(context);
	}

	// 鍦ㄨ繖閲屽垵濮嬪寲鎵嶆槸鏈�鍒濆鍖栫殑銆�
	public Geomark(Context context, AttributeSet atr) {
		super(context, atr);

		setZOrderOnTop(true);// 璁剧疆缃《锛堜笉鐒跺疄鐜颁笉浜嗛�忔槑锛�
		sfh = this.getHolder();
		sfh.addCallback(this);
		sfh.setFormat(PixelFormat.TRANSLUCENT);// 璁剧疆鑳屾櫙閫忔槑
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i("绯荤粺娑堟伅", "surfaceCreated");

		// 鍔犲叆涓嬮潰杩欎笁鍙ユ槸褰撴娊灞夐殣钘忓悗锛屾墦寮�鏃堕槻姝㈠凡缁忕粯杩囧浘鐨勫尯鍩熼棯鐑侊紝鎵�浠ュ共鑴嗗氨浠庢柊寮�濮嬬粯鍒躲��
		isRunning = true;
		currentX = 0;
		clearCanvas();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				gridDraw();
				drawChartLine();
			}
		});

		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.i("绯荤粺淇℃伅", "surfaceChanged");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.i("绯荤粺淇℃伅", "surfaceDestroyed");

		// 鍔犲叆杩欎釜鍙橀噺鏄负浜嗘帶鍒舵娊灞夐殣钘忔椂涓嶄細鍑虹幇寮傚父銆�
		isRunning = false;
	}

	protected void gridDraw() {
		float max = temp[0];
		float temMax = 0;
		float min = temp[0];
		float temMin = 0;
		float space = 0f;// 骞冲潎鍊�
		for (int i = 1; i < temp.length; i++) {
			if (max < temp[i]) {
				max = temp[i];
			}
			if (min > temp[i]) {
				min = temp[i];
			}
			temMax = max;
			temMin = min;
		}
		space = (temMax - temMin) / 7;

		Canvas canvas = sfh.lockCanvas();

		Paint mbackLinePaint = new Paint();// 鐢ㄦ潵鐢诲潗鏍囩郴浜�
		mbackLinePaint.setColor(Color.WHITE);
		mbackLinePaint.setAntiAlias(true);
		mbackLinePaint.setStrokeWidth(1);
		mbackLinePaint.setStyle(Style.FILL);

		Paint mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(12F);// 璁剧疆娓╁害鍊肩殑瀛椾綋澶у皬
		// 缁樺埗鍧愭爣绯�
		for (int i = 0; i < 8; i++) {
			canvas.drawLine(lift, top + gapY * i, lift + gapX * 23, top + gapY
					* i, mbackLinePaint);
			// canvas.drawText(temMin+space*i, 10, bottom-20*i, mTextPaint);
			// Math.round(((temMin + space * i) * 100) / 100.0);
			mTextPaint.setTextAlign(Align.RIGHT);
			float result = temMin + space * i;// 绮剧‘鐨勫悇涓妭鐐圭殑鍊�
			BigDecimal b = new BigDecimal(result);// 鏂板缓涓�涓狟igDecimal
			float displayVar = b.setScale(1, BigDecimal.ROUND_HALF_UP)
					.floatValue();// 杩涜灏忔暟鐐逛竴浣嶄繚鐣欏鐞嗙幇瀹炲湪鍧愭爣绯讳笂鐨勬暟鍊�
			canvas.drawText("" + displayVar, lift - 2, bottom + 3 - 20 * i,
					mTextPaint);
		}
		for (int i = 0; i < 24; i++) {
			canvas.drawLine(lift + gapX * i, top, lift + gapX * i, bottom,
					mbackLinePaint);
			mTextPaint.setTextAlign(Align.CENTER);
			canvas.drawText(houres[i], lift + gapX * i, bottom + 14, mTextPaint);
		}

		sfh.unlockCanvasAndPost(canvas);
	}

	protected void GridDraw(Canvas canvas) {
		if (canvas == null) {
			return;
		}
		float max = temp[0];
		float temMax = 0;
		float min = temp[0];
		float temMin = 0;
		float space = 0f;// 骞冲潎鍊�
		for (int i = 1; i < temp.length; i++) {
			if (max < temp[i]) {
				max = temp[i];
			}
			if (min > temp[i]) {
				min = temp[i];
			}
			temMax = max;
			temMin = min;
		}
		space = (temMax - temMin) / 7;// 7娈垫湁鏁堟樉绀鸿寖鍥�
		// textY=Math.round(temMin + space * i);

		Paint mbackLinePaint = new Paint();// 鐢ㄦ潵鐢诲潗鏍囩郴浜�
		mbackLinePaint.setColor(Color.WHITE);
		mbackLinePaint.setAntiAlias(true);
		mbackLinePaint.setStrokeWidth(1);
		mbackLinePaint.setStyle(Style.FILL);

		Paint mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		// mTextPaint.setTextAlign(Align.RIGHT);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(12F);// 璁剧疆娓╁害鍊肩殑瀛椾綋澶у皬
		// 缁樺埗鍧愭爣绯�
		for (int i = 0; i < 8; i++) {
			canvas.drawLine(lift, top + gapY * i, lift + gapX * 23, top + gapY
					* i, mbackLinePaint);
			mTextPaint.setTextAlign(Align.RIGHT);
			float result = temMin + space * i;// 绮剧‘鐨勫悇涓妭鐐圭殑鍊�
			BigDecimal b = new BigDecimal(result);// 鏂板缓涓�涓狟igDecimal
			float displayVar = b.setScale(1, BigDecimal.ROUND_HALF_UP)
					.floatValue();// 杩涜灏忔暟鐐逛竴浣嶄繚鐣欏鐞嗙幇瀹炲湪鍧愭爣绯讳笂鐨勬暟鍊�
			canvas.drawText("" + displayVar, lift - 2, bottom + 3 - 20 * i,
					mTextPaint);
		}
		for (int i = 0; i < 24; i++) {
			canvas.drawLine(lift + gapX * i, top, lift + gapX * i, bottom,
					mbackLinePaint);
			mTextPaint.setTextAlign(Align.CENTER);
			canvas.drawText(houres[i], lift + gapX * i, bottom + 14, mTextPaint);
		}
	}

	private void drawChartLine() {
		while (isRunning) {
			try {
				drawChart(currentX);// 缁樺埗

				currentX++;// 寰�鍓嶈繘

				if (currentX == right) {
					// 濡傛灉鍒颁簡缁堢偣锛屽垯娓呭睆閲嶆潵
					clearCanvas();
					currentX = 0;
				}

				try {
					Thread.sleep(tick);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {

			}
		}
	}

	void drawChart(int length) {
		if (length == 0)
			oldX = 0;
		Canvas canvas = sfh.lockCanvas(new Rect(oldX, 0, oldX + length, 180));// 鑼冨洿閫夊彇姝ｇ‘
		// Log.i("绯荤粺娑堟伅", "oldX = " + oldX + "  length = " + length);
		Paint mPointPaint = new Paint();
		mPointPaint.setAntiAlias(true);
		mPointPaint.setColor(Color.YELLOW);

		Paint mLinePaint = new Paint();// 鐢ㄦ潵鐢绘姌绾�
		mLinePaint.setColor(Color.YELLOW);
		mLinePaint.setAntiAlias(true);
		mLinePaint.setStrokeWidth(2);
		mLinePaint.setStyle(Style.FILL);

		float max = temp[0];
		float temMax = 0;
		float min = temp[0];
		float temMin = 0;
		float spacePX = 0f;// 骞冲潎鍍忕礌鍊�
		for (int i = 1; i < temp.length; i++) {
			if (max < temp[i]) {
				max = temp[i];
			}
			if (min > temp[i]) {
				min = temp[i];
			}
			temMax = max;
			temMin = min;
		}
		spacePX = 140 / (temMax - temMin);// 骞冲潎姣忎釜娓╁害鍊艰鍗犵敤鐨勫儚绱犲��

		float cx = 0f;
		float cy = 0f;
		float dx = 0f;
		float dy = 0f;
		for (int j = 0; j < temp.length - 1; j++) {
			cx = lift + gapX * j;
			cy = bottom - (temp[j] - temMin) * spacePX;
			dx = lift + gapX * (j + 1);
			dy = bottom - (temp[j + 1] - temMin) * spacePX;
			canvas.drawCircle(cx, cy, 3, mPointPaint);
			canvas.drawLine(cx, cy, dx, dy, mLinePaint);
		}

		sfh.unlockCanvasAndPost(canvas);// 瑙ｉ攣鐢诲竷锛屾彁浜ょ敾濂界殑鍥惧儚
	}

	/**
	 * 鎶婄敾甯冩摝骞插噣锛屽噯澶囩粯鍥句娇鐢ㄣ��
	 */
	private void clearCanvas() {
		Canvas canvas = sfh.lockCanvas();

		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);// 娓呴櫎鐢诲竷

		GridDraw(canvas);

		sfh.unlockCanvasAndPost(canvas);
	}
}

