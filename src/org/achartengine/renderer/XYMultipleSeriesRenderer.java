/**
 * Copyright (C) 2009 - 2012 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.achartengine.renderer;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.achartengine.util.MathHelper;

import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * Multiple XY series renderer.
 * ���XYϵ����Ⱦ����
 */
public class XYMultipleSeriesRenderer extends DefaultRenderer {
  /** The X axis title. X����� */
  private String mXTitle = "";
  /** The Y axis title. Y����� */
  /*wwww.javaapk.com �ṩ����*/
  private String[] mYTitle;
  /** The axis title text size. ������ı��Ĵ�С�� */
  private float mAxisTitleTextSize = 12;
  /** The start value in the X axis range. X�Ὺʼֵ����X����Сֵ*/
  private double[] mMinX;
  /** The end value in the X axis range. X�����ֵ����X�����ֵ */
  private double[] mMaxX;
  /** The start value in the Y axis range. Y�Ὺʼֵ����Y����Сֵ */
  private double[] mMinY;
  /** The end value in the Y axis range. Y�����ֵ����Y�����ֵ */
  private double[] mMaxY;
  /** The approximative number of labels on the x axis. ���Ƶ������ı�ǩ��x���ϡ�(Ӧ�þ��Ǽ�������С)*/
  private int mXLabels = 5;
  /** The approximative number of labels on the y axis. ���Ƶ������ı�ǩ��y���ϡ�(Ӧ�þ��Ǽ�������С)*/
  private int mYLabels = 5;
  /** The current orientation of the chart. ��ǰ�ķ���ͼ��(��ֱ���Ǻ���) */
  private Orientation mOrientation = Orientation.HORIZONTAL;
  /** The X axis text labels. X����ı���ǩ��*/
  private Map<Double, String> mXTextLabels = new HashMap<Double, String>();
  /** The Y axis text labels. Y����ı���ǩ��*/
  private Map<Integer, Map<Double, String>> mYTextLabels = new LinkedHashMap<Integer, Map<Double, String>>();
  /** A flag for enabling or not the pan on the X axis. һ�����,ʹX���ϵ�ƽ�ơ�(X���Ƿ�ƽ��)*/
  private boolean mPanXEnabled = true;
  /** A flag for enabling or not the pan on the Y axis. һ�����,ʹY���ϵ�ƽ�ơ�(Y���Ƿ�ƽ��)*/
  private boolean mPanYEnabled = true;
  /** A flag for enabling or not the zoom on the X axis. һ�����,ʹX���ϵ����š�(X���Ƿ�����)*/
  private boolean mZoomXEnabled = true;
  /** A flag for enabling or not the zoom on the Y axis . һ�����,ʹY���ϵ����š�(Y���Ƿ�����)*/
  private boolean mZoomYEnabled = true;
  /** The spacing between bars, in bar charts. ������ͼ�У�������ͼ֮��ļ�ࡣ*/
  private double mBarSpacing = 0;
  /** The margins colors. ��Ե����ɫ��*/
  private int mMarginsColor = NO_COLOR;
  /** The pan limits. ƽ�Ƶ����ơ� */
  private double[] mPanLimits;
  /** The zoom limits. ���ŵ����ơ�*/
  private double[] mZoomLimits;
  /** The X axis labels rotation angle. X���ǩ��ת�Ƕȡ�*/
  private float mXLabelsAngle;
  /** The Y axis labels rotation angle. Y���ǩ��ת�Ƕȡ�*/
  private float mYLabelsAngle;
  /** The initial axis range. ��ʼ�᷶Χ��*/
  private Map<Integer, double[]> initialRange = new LinkedHashMap<Integer, double[]>();
  /** The point size for charts displaying points. ͼ������ʾ��Ĵ�С��*/
  private float mPointSize = 3;
  /** The grid color. �������ɫ��*/
  private int mGridColor = Color.argb(75, 200, 200, 200);
  /** The number of scales. �߶ȵ�������*/
  private int scalesCount;
  /** The X axis labels alignment. X���ǩ���롣*/
  private Align xLabelsAlign = Align.CENTER;
  /** The Y axis labels alignment. Y���ǩ���롣*/
  private Align[] yLabelsAlign;
  /** The X text label padding. X����ֵ������� Padding ֵ��*/
  private float mXLabelsPadding = 0;
  /** The Y text label padding. Y����ֵ������� Padding ֵ��*/
  private float mYLabelsPadding = 0;
  /** The Y axis labels vertical padding. Y���ǩ�Ĵ�ֱ��ࡣ*/
  private float mYLabelsVerticalPadding = 2;
  /** The Y axis alignment. Y����롣*/
  private Align[] yAxisAlign;
  /** The X axis labels color. X���ǩ����ɫ��*/
  private int mXLabelsColor = TEXT_COLOR;
  /** The Y axis labels color. Y���ǩ����ɫ��*/
  private int[] mYLabelsColor = new int[] { TEXT_COLOR };
  /**
   * If X axis value selection algorithm to be used. Only used by the time
   * charts.
   * ���Ҫʹ��X��ֵѡ���㷨����ʹ����ʱ��ͼ��
   */
  private boolean mXRoundedLabels = true;
  /** The label format. ��ǩ��ʽ�� */
  private NumberFormat mLabelFormat;

  /**
   * An enum for the XY chart orientation of the X axis.
   * һ��ö��X���XYͼ��ķ���
   */
  public enum Orientation {
    HORIZONTAL(0), VERTICAL(90);
    /** The rotate angle. ��ת�Ƕȡ�*/
    private int mAngle = 0;

    private Orientation(int angle) {
      mAngle = angle;
    }

    /**
     * Return the orientation rotate angle.
     * ���ط�����ת�Ƕȡ�
     * @return the orientaion rotate angle
     */
    public int getAngle() {
      return mAngle;
    }
  }

  public XYMultipleSeriesRenderer() {
    this(1);
  }

  public XYMultipleSeriesRenderer(int scaleNumber) {
    scalesCount = scaleNumber;
    initAxesRange(scaleNumber);
  }

  public void initAxesRange(int scales) {
    mYTitle = new String[scales];
    yLabelsAlign = new Align[scales];
    yAxisAlign = new Align[scales];
    mYLabelsColor = new int[scales];
    mMinX = new double[scales];
    mMaxX = new double[scales];
    mMinY = new double[scales];
    mMaxY = new double[scales];
    for (int i = 0; i < scales; i++) {
      mYLabelsColor[i] = TEXT_COLOR;
      initAxesRangeForScale(i);
    }
  }

  public void initAxesRangeForScale(int i) {
    mMinX[i] = MathHelper.NULL_VALUE;
    mMaxX[i] = -MathHelper.NULL_VALUE;
    mMinY[i] = MathHelper.NULL_VALUE;
    mMaxY[i] = -MathHelper.NULL_VALUE;
    double[] range = new double[] { mMinX[i], mMaxX[i], mMinY[i], mMaxY[i] };
    initialRange.put(i, range);
    mYTitle[i] = "";
    mYTextLabels.put(i, new HashMap<Double, String>());
    yLabelsAlign[i] = Align.CENTER;
    yAxisAlign[i] = Align.LEFT;
  }

  /**
   * Returns the current orientation of the chart X axis.
   * ���ص�ǰ��ͼ��X�᷽��
   * @return the chart orientation
   */
  public Orientation getOrientation() {
    return mOrientation;
  }

  /**
   * Sets the current orientation of the chart X axis.
   * ���õ�ǰ��ͼ��X�᷽��
   * @param orientation the chart orientation
   */
  public void setOrientation(Orientation orientation) {
    mOrientation = orientation;
  }

  /**
   * Returns the title for the X axis.
   * ����X��ı��⡣
   * @return the X axis title
   */
  public String getXTitle() {
    return mXTitle;
  }

  /**
   * Sets the title for the X axis.
   * ����X��ı��⡣
   * @param title the X axis title
   */
  public void setXTitle(String title) {
    mXTitle = title;
  }

  /**
   * Returns the title for the Y axis.
   * ����Y��ı��⡣
   * @return the Y axis title
   */
  public String getYTitle() {
    return getYTitle(0);
  }

  /**
   * Returns the title for the Y axis.
   * 
   * @param scale the renderer scale
   * @return the Y axis title
   */
  public String getYTitle(int scale) {
    return mYTitle[scale];
  }

  /**
   * Sets the title for the Y axis.
   * ����Y��ı��⡣
   * @param title the Y axis title
   */
  public void setYTitle(String title) {
    setYTitle(title, 0);
  }

  /**
   * Sets the title for the Y axis.
   * 
   * @param title the Y axis title
   * @param scale the renderer scale
   */
  public void setYTitle(String title, int scale) {
    mYTitle[scale] = title;
  }

  /**
   * Returns the axis title text size.
   * ����������ı��Ĵ�С��
   * @return the axis title text size
   */
  public float getAxisTitleTextSize() {
    return mAxisTitleTextSize;
  }

  /**
   * Sets the axis title text size.
   * ����������ı��Ĵ�С��
   * @param textSize the chart axis text size
   */
  public void setAxisTitleTextSize(float textSize) {
    mAxisTitleTextSize = textSize;
  }

  /**
   * Returns the start value of the X axis range.
   * ���ؿ�ʼֵX��ķ�Χ��
   * @return the X axis range start value
   */
  public double getXAxisMin() {
    return getXAxisMin(0);
  }

  /**
   * Sets the start value of the X axis range.
   * ���ÿ�ʼֵX��ķ�Χ��
   * @param min the X axis range start value
   */
  public void setXAxisMin(double min) {
    setXAxisMin(min, 0);
  }

  /**
   * Returns if the minimum X value was set.
   * ���������СXֵ����
   * @return the minX was set or not
   */
  public boolean isMinXSet() {
    return isMinXSet(0);
  }

  /**
   * Returns the end value of the X axis range.
   * ����X��Ķ�ֵ��Χ��(����������Xֵ����)
   * @return the X axis range end value
   */
  public double getXAxisMax() {
    return getXAxisMax(0);
  }

  /**
   * Sets the end value of the X axis range.
   * 
   * @param max the X axis range end value
   */
  public void setXAxisMax(double max) {
    setXAxisMax(max, 0);
  }

  /**
   * Returns if the maximum X value was set.
   * 
   * @return the maxX was set or not
   */
  public boolean isMaxXSet() {
    return isMaxXSet(0);
  }

  /**
   * Returns the start value of the Y axis range.
   * 
   * @return the Y axis range end value
   */
  public double getYAxisMin() {
    return getYAxisMin(0);
  }

  /**
   * Sets the start value of the Y axis range.
   * 
   * @param min the Y axis range start value
   */
  public void setYAxisMin(double min) {
    setYAxisMin(min, 0);
  }

  /**
   * Returns if the minimum Y value was set.
   * 
   * @return the minY was set or not
   */
  public boolean isMinYSet() {
    return isMinYSet(0);
  }

  /**
   * Returns the end value of the Y axis range.
   * 
   * @return the Y axis range end value
   */
  public double getYAxisMax() {
    return getYAxisMax(0);
  }

  /**
   * Sets the end value of the Y axis range.
   * 
   * @param max the Y axis range end value
   */
  public void setYAxisMax(double max) {
    setYAxisMax(max, 0);
  }

  /**
   * Returns if the maximum Y value was set.
   * 
   * @return the maxY was set or not
   */
  public boolean isMaxYSet() {
    return isMaxYSet(0);
  }

  /**
   * Returns the start value of the X axis range.
   * ���ؿ�ʼֵX��ķ�Χ��
   * @param scale the renderer scale X�Ὺʼֵ����X����Сֵ
   * @return the X axis range start value
   */
  public double getXAxisMin(int scale) {
    return mMinX[scale];
  }

  /**
   * Sets the start value of the X axis range.
   * 
   * @param min the X axis range start value
   * @param scale the renderer scale
   */
  public void setXAxisMin(double min, int scale) {
    if (!isMinXSet(scale)) {
      initialRange.get(scale)[0] = min;
    }
    mMinX[scale] = min;
  }

  /**
   * Returns if the minimum X value was set.
   * 
   * @param scale the renderer scale
   * @return the minX was set or not
   */
  public boolean isMinXSet(int scale) {
    return mMinX[scale] != MathHelper.NULL_VALUE;
  }

  /**
   * Returns the end value of the X axis range.
   * ���ؿ�ʼֵX��Ķ�ֵ��Χ��
   * @param scale the renderer scale X�����ֵ����X�����ֵ
   * @return the X axis range end value
   */
  public double getXAxisMax(int scale) {
    return mMaxX[scale];
  }

  /**
   * Sets the end value of the X axis range.
   * 
   * @param max the X axis range end value
   * @param scale the renderer scale
   */
  public void setXAxisMax(double max, int scale) {
    if (!isMaxXSet(scale)) {
      initialRange.get(scale)[1] = max;
    }
    mMaxX[scale] = max;
  }

  /**
   * Returns if the maximum X value was set.
   * 
   * @param scale the renderer scale
   * @return the maxX was set or not
   */
  public boolean isMaxXSet(int scale) {
    return mMaxX[scale] != -MathHelper.NULL_VALUE;
  }

  /**
   * Returns the start value of the Y axis range.
   * ���ؿ�ʼֵY��ķ�Χ��
   * @param scale the renderer scale Y�Ὺʼֵ����Y����Сֵ
   * @return the Y axis range end value
   */
  public double getYAxisMin(int scale) {
    return mMinY[scale];
  }

  /**
   * Sets the start value of the Y axis range.
   * 
   * @param min the Y axis range start value
   * @param scale the renderer scale
   */
  public void setYAxisMin(double min, int scale) {
    if (!isMinYSet(scale)) {
      initialRange.get(scale)[2] = min;
    }
    mMinY[scale] = min;
  }

  /**
   * Returns if the minimum Y value was set.
   * 
   * @param scale the renderer scale
   * @return the minY was set or not
   */
  public boolean isMinYSet(int scale) {
    return mMinY[scale] != MathHelper.NULL_VALUE;
  }

  /**
   * Returns the end value of the Y axis range.
   * ���ؿ�ʼֵY��Ķ�ֵ��Χ��
   * @param scale the renderer scale Y�����ֵ����Y�����ֵ
   * @return the Y axis range end value
   */
  public double getYAxisMax(int scale) {
    return mMaxY[scale];
  }

  /**
   * Sets the end value of the Y axis range.
   * 
   * @param max the Y axis range end value
   * @param scale the renderer scale
   */
  public void setYAxisMax(double max, int scale) {
    if (!isMaxYSet(scale)) {
      initialRange.get(scale)[3] = max;
    }
    mMaxY[scale] = max;
  }

  /**
   * Returns if the maximum Y value was set.
   * 
   * @param scale the renderer scale
   * @return the maxY was set or not
   */
  public boolean isMaxYSet(int scale) {
    return mMaxY[scale] != -MathHelper.NULL_VALUE;
  }

  /**
   * Returns the approximate number of labels for the X axis.
   * 
   * @return the approximate number of labels for the X axis
   */
  public int getXLabels() {
    return mXLabels;
  }

  /**
   * Sets the approximate number of labels for the X axis.
   * ���Ľ�������ΪX���ǩ��
   * @param xLabels the approximate number of labels for the X axis
   */
  public void setXLabels(int xLabels) {
    mXLabels = xLabels;
  }

  /**
   * Adds a new text label for the specified X axis value.
   * 
   * @param x the X axis value
   * @param text the text label
   * @deprecated use addXTextLabel instead
   */
  public void addTextLabel(double x, String text) {
    addXTextLabel(x, text);
  }

  /**
   * Adds a new text label for the specified X axis value.
   * 
   * @param x the X axis value
   * @param text the text label
   */
  public synchronized void addXTextLabel(double x, String text) {
    mXTextLabels.put(x, text);
  }

  /**
   * Removes text label for the specified X axis value.
   * 
   * @param x the X axis value
   */
  public synchronized void removeXTextLabel(double x) {
    mXTextLabels.remove(x);
  }

  /**
   * Returns the X axis text label at the specified X axis value.
   * 
   * @param x the X axis value
   * @return the X axis text label
   */
  public synchronized String getXTextLabel(Double x) {
    return mXTextLabels.get(x);
  }

  /**
   * Returns the X text label locations.
   * 
   * @return the X text label locations
   */
  public synchronized Double[] getXTextLabelLocations() {
    return mXTextLabels.keySet().toArray(new Double[0]);
  }

  /**
   * Clears the existing text labels.
   * 
   * @deprecated use clearXTextLabels instead
   */
  public void clearTextLabels() {
    clearXTextLabels();
  }

  /**
   * Clears the existing text labels on the X axis.
   */
  public synchronized void clearXTextLabels() {
    mXTextLabels.clear();
  }

  /**
   * If X axis labels should be rounded.
   * 
   * @return if rounded time values to be used
   */
  public boolean isXRoundedLabels() {
    return mXRoundedLabels;
  }

  /**
   * Sets if X axis rounded time values to be used.
   * 
   * @param rounded rounded values to be used
   */
  public void setXRoundedLabels(boolean rounded) {
    mXRoundedLabels = rounded;
  }

  /**
   * Adds a new text label for the specified Y axis value.
   * 
   * @param y the Y axis value
   * @param text the text label
   */
  public void addYTextLabel(double y, String text) {
    addYTextLabel(y, text, 0);
  }

  /**
   * Removes text label for the specified Y axis value.
   * 
   * @param y the Y axis value
   */
  public void removeYTextLabel(double y) {
    removeYTextLabel(y, 0);
  }

  /**
   * Adds a new text label for the specified Y axis value.
   * 
   * @param y the Y axis value
   * @param text the text label
   * @param scale the renderer scale
   */
  public synchronized void addYTextLabel(double y, String text, int scale) {
    mYTextLabels.get(scale).put(y, text);
  }

  /**
   * Removes text label for the specified Y axis value.
   * 
   * @param y the Y axis value
   * @param scale the renderer scale
   */
  public synchronized void removeYTextLabel(double y, int scale) {
    mYTextLabels.get(scale).remove(y);
  }

  /**
   * Returns the Y axis text label at the specified Y axis value.
   * 
   * @param y the Y axis value
   * @return the Y axis text label
   */
  public String getYTextLabel(Double y) {
    return getYTextLabel(y, 0);
  }

  /**
   * Returns the Y axis text label at the specified Y axis value.
   * 
   * @param y the Y axis value
   * @param scale the renderer scale
   * @return the Y axis text label
   */
  public synchronized String getYTextLabel(Double y, int scale) {
    return mYTextLabels.get(scale).get(y);
  }

  /**
   * Returns the Y text label locations.
   * 
   * @return the Y text label locations
   */
  public Double[] getYTextLabelLocations() {
    return getYTextLabelLocations(0);
  }

  /**
   * Returns the Y text label locations.
   * 
   * @param scale the renderer scale
   * @return the Y text label locations
   */
  public synchronized Double[] getYTextLabelLocations(int scale) {
    return mYTextLabels.get(scale).keySet().toArray(new Double[0]);
  }

  /**
   * Clears the existing text labels on the Y axis.
   */
  public void clearYTextLabels() {
    clearYTextLabels(0);
  }

  /**
   * Clears the existing text labels on the Y axis.
   * 
   * @param scale the renderer scale
   */
  public synchronized void clearYTextLabels(int scale) {
    mYTextLabels.get(scale).clear();
  }

  /**
   * Returns the approximate number of labels for the Y axis.
   * 
   * @return the approximate number of labels for the Y axis
   */
  public int getYLabels() {
    return mYLabels;
  }

  /**
   * Sets the approximate number of labels for the Y axis.
   * 
   * @param yLabels the approximate number of labels for the Y axis
   */
  public void setYLabels(int yLabels) {
    mYLabels = yLabels;
  }

  /**
   * Sets if the chart point values should be displayed as text.
   * 
   * @param display if the chart point values should be displayed as text
   * @deprecated use SimpleSeriesRenderer.setDisplayChartValues() instead
   */
  public void setDisplayChartValues(boolean display) {
    SimpleSeriesRenderer[] renderers = getSeriesRenderers();
    for (SimpleSeriesRenderer renderer : renderers) {
      renderer.setDisplayChartValues(display);
    }
  }

  /**
   * Sets the chart values text size.
   * 
   * @param textSize the chart values text size
   * @deprecated use SimpleSeriesRenderer.setChartValuesTextSize() instead
   */
  public void setChartValuesTextSize(float textSize) {
    SimpleSeriesRenderer[] renderers = getSeriesRenderers();
    for (SimpleSeriesRenderer renderer : renderers) {
      renderer.setChartValuesTextSize(textSize);
    }
  }

  /**
   * Returns the enabled state of the pan on at least one axis.
   * 
   * @return if pan is enabled
   */
  public boolean isPanEnabled() {
    return isPanXEnabled() || isPanYEnabled();
  }

  /**
   * Returns the enabled state of the pan on X axis.
   * 
   * @return if pan is enabled on X axis
   */
  public boolean isPanXEnabled() {
    return mPanXEnabled;
  }

  /**
   * Returns the enabled state of the pan on Y axis.
   * 
   * @return if pan is enabled on Y axis
   */
  public boolean isPanYEnabled() {
    return mPanYEnabled;
  }

  /**
   * Sets the enabled state of the pan.
   * 
   * @param enabledX pan enabled on X axis
   * @param enabledY pan enabled on Y axis
   */
  public void setPanEnabled(boolean enabledX, boolean enabledY) {
    mPanXEnabled = enabledX;
    mPanYEnabled = enabledY;
  }
  
  /**
   * Override {@link DefaultRenderer#setPanEnabled(boolean)} so it can be
   * delegated to {@link #setPanEnabled(boolean, boolean)}.
   */
  @Override
  public void setPanEnabled(final boolean enabled) {
    setPanEnabled(enabled, enabled);
  }

  /**
   * Returns the enabled state of the zoom on at least one axis.
   * ���� ��X�����Y�����Ƿ���Ա����ţ���һ�������򷵻�true; ������Ҳ��
   * @return if zoom is enabled
   */
  public boolean isZoomEnabled() {
    return isZoomXEnabled() || isZoomYEnabled();
  }

  /**
   * Returns the enabled state of the zoom on X axis.
   * ���� ��X���Ƿ���Ա�����;
   * @return if zoom is enabled on X axis
   */
  public boolean isZoomXEnabled() {
    return mZoomXEnabled;
  }

  /**
   * Returns the enabled state of the zoom on Y axis.
   * ���� ��Y�����Ƿ���Ա�����;
   * @return if zoom is enabled on Y axis
   */
  public boolean isZoomYEnabled() {
    return mZoomYEnabled;
  }

  /**
   * Sets the enabled state of the zoom.
   * ����X���Y���Ƿ���Ա����ţ�����Ҳ������������������Ϊһ��ֵ��Ҳ����X��Y�ᶼ�������Ż��Ƕ�����
   * @param enabledX zoom enabled on X axis
   * @param enabledY zoom enabled on Y axis
   */
  public void setZoomEnabled(boolean enabledX, boolean enabledY) {
    mZoomXEnabled = enabledX;
    mZoomYEnabled = enabledY;
  }

  /**
   * Returns the spacing between bars, in bar charts.
   * 
   * @return the spacing between bars
   * @deprecated use getBarSpacing instead
   */
  public double getBarsSpacing() {
    return getBarSpacing();
  }

  /**
   * Returns the spacing between bars, in bar charts.
   * 
   * @return the spacing between bars
   */
  public double getBarSpacing() {
    return mBarSpacing;
  }

  /**
   * Sets the spacing between bars, in bar charts. Only available for bar
   * charts. This is a coefficient of the bar width. For instance, if you want
   * the spacing to be a half of the bar width, set this value to 0.5.
   * 
   * @param spacing the spacing between bars coefficient
   */
  public void setBarSpacing(double spacing) {
    mBarSpacing = spacing;
  }

  /**
   * Returns the margins color.
   * ���� ��Ե����ɫ��
   * @return the margins color
   */
  public int getMarginsColor() {
    return mMarginsColor;
  }

  /**
   * Sets the color of the margins.
   * ���� ��Ե����ɫ��
   * @param color the margins color
   */
  public void setMarginsColor(int color) {
    mMarginsColor = color;
  }

  /**
   * Returns the grid color.
   * 
   * @return the grid color
   */
  public int getGridColor() {
    return mGridColor;
  }

  /**
   * Sets the color of the grid.
   * 
   * @param color the grid color
   */
  public void setGridColor(int color) {
    mGridColor = color;
  }

  /**
   * Returns the pan limits.
   * 
   * @return the pan limits
   */
  public double[] getPanLimits() {
    return mPanLimits;
  }

  /**
   * Sets the pan limits as an array of 4 values. Setting it to null or a
   * different size array will disable the panning limitation. Values:
   * [panMinimumX, panMaximumX, panMinimumY, panMaximumY]
   * ����ƽ�Ƶ����ơ�{-10, 20, -10, 40} ��ʾ�������Ƶ�-10�������Ƶ�20�������Ƶ�-10�������Ƶ�40 
   */
  public void setPanLimits(double[] panLimits) {
    mPanLimits = panLimits;
  }

  /**
   * Returns the zoom limits.
   * ���� ���ŵ����ơ�{-10, 20, -10, 40} ��ʾ��������С��-10��������С��20��������С��-10��������С��40
   */
  public double[] getZoomLimits() {
    return mZoomLimits;
  }

  /**
   * Sets the zoom limits as an array of 4 values. Setting it to null or a
   * different size array will disable the zooming limitation. Values:
   * [zoomMinimumX, zoomMaximumX, zoomMinimumY, zoomMaximumY]
   * ���� ���ŵ����ơ�{-10, 20, -10, 40} ��ʾ��������С��-10��������С��20��������С��-10��������С��40
   * @param zoomLimits the zoom limits
   */
  public void setZoomLimits(double[] zoomLimits) {
    mZoomLimits = zoomLimits;
  }

  /**
   * Returns the rotation angle of labels for the X axis.
   * 
   * @return the rotation angle of labels for the X axis
   */
  public float getXLabelsAngle() {
    return mXLabelsAngle;
  }

  /**
   * Sets the rotation angle (in degrees) of labels for the X axis.
   * 
   * @param angle the rotation angle of labels for the X axis
   */
  public void setXLabelsAngle(float angle) {
    mXLabelsAngle = angle;
  }

  /**
   * Returns the rotation angle of labels for the Y axis.
   * 
   * @return the approximate number of labels for the Y axis
   */
  public float getYLabelsAngle() {
    return mYLabelsAngle;
  }

  /**
   * Sets the rotation angle (in degrees) of labels for the Y axis.
   * 
   * @param angle the rotation angle of labels for the Y axis
   */
  public void setYLabelsAngle(float angle) {
    mYLabelsAngle = angle;
  }

  /**
   * Returns the size of the points, for charts displaying points.
   * 
   * @return the point size
   */
  public float getPointSize() {
    return mPointSize;
  }

  /**
   * Sets the size of the points, for charts displaying points.
   * 
   * @param size the point size
   */
  public void setPointSize(float size) {
    mPointSize = size;
  }

  public void setRange(double[] range) {
    setRange(range, 0);
  }

  /**
   * Sets the axes range values.
   * 
   * @param range an array having the values in this order: minX, maxX, minY,
   *          maxY
   * @param scale the renderer scale
   */
  public void setRange(double[] range, int scale) {
    setXAxisMin(range[0], scale);
    setXAxisMax(range[1], scale);
    setYAxisMin(range[2], scale);
    setYAxisMax(range[3], scale);
  }

  public boolean isInitialRangeSet() {
    return isInitialRangeSet(0);
  }

  /**
   * Returns if the initial range is set.
   * 
   * @param scale the renderer scale
   * @return the initial range was set or not
   */
  public boolean isInitialRangeSet(int scale) {
    return initialRange.get(scale) != null;
  }

  /**
   * Returns the initial range.
   * 
   * @return the initial range
   */
  public double[] getInitialRange() {
    return getInitialRange(0);
  }

  /**
   * Returns the initial range.
   * 
   * @param scale the renderer scale
   * @return the initial range
   */
  public double[] getInitialRange(int scale) {
    return initialRange.get(scale);
  }

  /**
   * Sets the axes initial range values. This will be used in the zoom fit tool.
   * 
   * @param range an array having the values in this order: minX, maxX, minY,
   *          maxY
   */
  public void setInitialRange(double[] range) {
    setInitialRange(range, 0);
  }

  /**
   * Sets the axes initial range values. This will be used in the zoom fit tool.
   * 
   * @param range an array having the values in this order: minX, maxX, minY,
   *          maxY
   * @param scale the renderer scale
   */
  public void setInitialRange(double[] range, int scale) {
    initialRange.put(scale, range);
  }

  /**
   * Returns the X axis labels color.
   * 
   * @return the X axis labels color
   */
  public int getXLabelsColor() {
    return mXLabelsColor;
  }

  /**
   * Returns the Y axis labels color.
   * 
   * @return the Y axis labels color
   */
  public int getYLabelsColor(int scale) {
    return mYLabelsColor[scale];
  }

  /**
   * Sets the X axis labels color.
   * 
   * @param color the X axis labels color
   */
  public void setXLabelsColor(int color) {
    mXLabelsColor = color;
  }

  /**
   * Sets the Y axis labels color.
   * 
   * @param scale the renderer scale
   * @param color the Y axis labels color
   */
  public void setYLabelsColor(int scale, int color) {
    mYLabelsColor[scale] = color;
  }

  /**
   * Returns the X axis labels alignment.
   * ���� X���ǩ�Ķ��뷽ʽ��Ҳ����ֵ�����ı�����ߡ��ұ߻����м�
   * @return X labels alignment
   */
  public Align getXLabelsAlign() {
    return xLabelsAlign;
  }

  /**
   * Sets the X axis labels alignment.
   * ���� X���ǩ���롣Ҳ����ֵ�����ı�����ߡ��ұ߻����м�
   * @param align the X labels alignment
   */
  public void setXLabelsAlign(Align align) {
    xLabelsAlign = align;
  }

  /**
   * Returns the Y axis labels alignment.
   * ���� Y���ǩ�Ķ��뷽ʽ��Ҳ����ֵ�����ı�����ߡ��ұ߻����м�
   * @param scale the renderer scale
   * @return Y labels alignment
   */
  public Align getYLabelsAlign(int scale) {
    return yLabelsAlign[scale];
  }

  public void setYLabelsAlign(Align align) {
    setYLabelsAlign(align, 0);
  }

  public Align getYAxisAlign(int scale) {
    return yAxisAlign[scale];
  }

  public void setYAxisAlign(Align align, int scale) {
    yAxisAlign[scale] = align;
  }

  /**
   * Sets the Y axis labels alignment.
   * ���� Y���ǩ���롣Ҳ����ֵ�����ı�����ߡ��ұ߻����м�
   * @param align the Y labels alignment
   */
  public void setYLabelsAlign(Align align, int scale) {
    yLabelsAlign[scale] = align;
  }

  /**
   * Returns the X labels padding.
   * ���� X����ֵ������� Padding ֵ
   * @return X labels padding
   */
  public float getXLabelsPadding() {
    return mXLabelsPadding;
  }

  /**
    * Sets the X labels padding
    * ���� X����ֵ������� Padding ֵ
    * @param padding the amount of padding between the axis and the label
    */
  public void setXLabelsPadding(float padding) {
    mXLabelsPadding = padding;
  }

  /**
   * Returns the Y labels padding.
   * ���� Y����ֵ������� Padding ֵ
   * @return Y labels padding
   */
  public float getYLabelsPadding() {
    return mYLabelsPadding;
  }

  /**
    * Sets the Y labels vertical padding
    *
    * @param padding the amount of vertical padding
    */
  public void setYLabelsVerticalPadding(float padding) {
    mYLabelsVerticalPadding = padding;
  }

  /**
   * Returns the Y labels vertical padding.
   *
   * @return Y labels vertical padding
   */
  public float getYLabelsVerticalPadding() {
    return mYLabelsVerticalPadding;
  }

  /**
    * Sets the Y labels padding
    * ���� Y����ֵ������� Padding ֵ
    * @param padding the amount of padding between the axis and the label
    */
  public void setYLabelsPadding(float padding) {
    mYLabelsPadding = padding;
  }

  /**
   * Returns the number format for displaying labels.
   * 
   * @return the number format for labels
   */
  public NumberFormat getLabelFormat() {
    return mLabelFormat;
  }

  /**
   * Sets the number format for displaying labels.
   * 
   * @param format the number format for labels
   */
  public void setLabelFormat(NumberFormat format) {
    mLabelFormat = format;
  }

  /**
   * ���� �߶ȵ�������
   * @return
   */
  public int getScalesCount() {
    return scalesCount;
  }

}
