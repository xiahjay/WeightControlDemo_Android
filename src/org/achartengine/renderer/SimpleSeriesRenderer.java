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

import java.io.Serializable;
import java.text.NumberFormat;

import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * A simple series renderer.
 * һ���򵥵���Ⱦ��ϵ�С�
 */
public class SimpleSeriesRenderer implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -3065433371441957730L;
  /** The series color. ϵ�е���ɫ�� */
  private int mColor = Color.BLUE;
  /** If the values should be displayed above the chart points. ͼ�������Ƿ���Ҫ��ʾ��ֵ�㡣 */
  private boolean mDisplayChartValues;
  /** The minimum distance between displaying chart values. ͼ����ʾֵ֮�����С���롣 */
  private int mDisplayChartValuesDistance = 100;
  /** The chart values text size. ͼ�����ִ�Сֵ�� */
  private float mChartValuesTextSize = 10;
  /** The chart values text alignment. ��ֵ�ı����뷽ʽ��*/
  private Align mChartValuesTextAlign = Align.CENTER;
  /** The chart values spacing from the data point. ͼ�����ݵ�ļ��ֵ�� */
  private float mChartValuesSpacing = 5f;
  /** The stroke style. ��߷��*/
  private BasicStroke mStroke;
  /** If gradient is enabled. �Ƿ��������ݶȡ�(����ͼ�к�����Ҫ��ֵ)*/
  private boolean mGradientEnabled = false;
  /** The gradient start value. �ݶ�-��ʼֵ�� */
  private double mGradientStartValue;
  /** The gradient start color. ���俪ʼ��ɫ�� */
  private int mGradientStartColor;
  /** The gradient stop value. �ݶ�-����ֵ��*/
  private double mGradientStopValue;
  /** The gradient stop color. ���������ɫ��*/
  private int mGradientStopColor;
  /** If the legend item for this renderer is visible. ��Ⱦ����ͼ����Ŀ�Ƿ�ɼ���*/
  private boolean mShowLegendItem = true;
  /** The chart values format. ֵ��ͼ���ʽ�� */
  private NumberFormat mChartValuesFormat;
  /** If this is a highlighted slice (pie chart displays slice as exploded). �Ƿ���ʾΪһ������Ƭ������(��ͼ��ʾƬ�ֽ���ͼ)�� */
  private boolean mHighlighted;

  /**
   * Returns the series color.
   * ����ϵ�е���ɫ��
   * @return the series color
   */
  public int getColor() {
    return mColor;
  }

  /**
   * Sets the series color.
   * ����ϵ�е���ɫ��
   * @param color the series color
   */
  public void setColor(int color) {
    mColor = color;
  }

  /**
   * Returns if the chart point values should be displayed as text.
   * ���� ͼ���ֵ�Ƿ���Ҫ��ʾΪ�ı���
   * @return if the chart point values should be displayed as text
   */
  public boolean isDisplayChartValues() {
    return mDisplayChartValues;
  }

  /**
   * Sets if the chart point values should be displayed as text.
   * ���� ͼ���ֵ�Ƿ���Ҫ��ʾΪ�ı���
   * @param display if the chart point values should be displayed as text
   */
  public void setDisplayChartValues(boolean display) {
    mDisplayChartValues = display;
  }

  /**
   * Returns the chart values minimum distance.
   * ����ֵ��С�����ͼ��
   * @return the chart values minimum distance
   */
  public int getDisplayChartValuesDistance() {
    return mDisplayChartValuesDistance;
  }

  /**
   * Sets chart values minimum distance.
   * ���� ֵ��С�����ͼ��
   * @param distance the chart values minimum distance
   */
  public void setDisplayChartValuesDistance(int distance) {
    mDisplayChartValuesDistance = distance;
  }

  /**
   * Returns the chart values text size.
   * ���� ͼ�����ִ�Сֵ��
   * @return the chart values text size
   */
  public float getChartValuesTextSize() {
    return mChartValuesTextSize;
  }

  /**
   * Sets the chart values text size.
   * ���� ͼ�����ִ�Сֵ��
   * @param textSize the chart values text size
   */
  public void setChartValuesTextSize(float textSize) {
    mChartValuesTextSize = textSize;
  }

  /**
   * Returns the chart values text align.
   * ���� ��ֵ�ı����뷽ʽ��
   * @return the chart values text align
   */
  public Align getChartValuesTextAlign() {
    return mChartValuesTextAlign;
  }

  /**
   * Sets the chart values text align.
   * ���� ��ֵ�ı����뷽ʽ��
   * @param align the chart values text align
   */
  public void setChartValuesTextAlign(Align align) {
    mChartValuesTextAlign = align;
  }

  /**
   * Returns the chart values spacing from the data point.
   * ���� ͼ�����ݵ�ļ��ֵ��
   * @return the chart values spacing
   */
  public float getChartValuesSpacing() {
    return mChartValuesSpacing;
  }

  /**
   * Sets the chart values spacing from the data point.
   * ���� ͼ�����ݵ�ļ�ࡣ
   * @param spacing the chart values spacing (in pixels) from the chart data
   *          point
   */
  public void setChartValuesSpacing(float spacing) {
    mChartValuesSpacing = spacing;
  }

  /**
   * Returns the stroke style.
   * ���� ��߷��
   * @return the stroke style
   */
  public BasicStroke getStroke() {
    return mStroke;
  }

  /**
   * Sets the stroke style.
   * ���� ��߷��
   * @param stroke the stroke style
   */
  public void setStroke(BasicStroke stroke) {
    mStroke = stroke;
  }

  /**
   * Returns the gradient is enabled value.
   * ���� �Ƿ��������ݶȡ�
   * @return the gradient enabled
   */
  public boolean isGradientEnabled() {
    return mGradientEnabled;
  }

  /**
   * Sets the gradient enabled value.
   * ���� �Ƿ������ݶ�
   * @param enabled the gradient enabled
   */
  public void setGradientEnabled(boolean enabled) {
    mGradientEnabled = enabled;
  }

  /**
   * Returns the gradient start value.
   * ���� �ݶ�-��ʼֵ
   * @return the gradient start value
   */
  public double getGradientStartValue() {
    return mGradientStartValue;
  }

  /**
   * Returns the gradient start color.
   * ���� �ݶ�-��ʼֵ
   * @return the gradient start color
   */
  public int getGradientStartColor() {
    return mGradientStartColor;
  }

  /**
   * Sets the gradient start value and color.
   * ���� �ݶ�-��ʼֵ �� ���俪ʼ��ɫ
   * @param start the gradient start value
   * @param color the gradient start color
   */
  public void setGradientStart(double start, int color) {
    mGradientStartValue = start;
    mGradientStartColor = color;
  }

  /**
   * Returns the gradient stop value.
   * ���� �ݶ�-����ֵ��
   * @return the gradient stop value
   */
  public double getGradientStopValue() {
    return mGradientStopValue;
  }

  /**
   * Returns the gradient stop color.
   * ���� ���������ɫ��
   * @return the gradient stop color
   */
  public int getGradientStopColor() {
    return mGradientStopColor;
  }

  /**
   * Sets the gradient stop value and color.
   * ���� �ݶ�-����ֵ �� ���������ɫ��
   * @param start the gradient stop value
   * @param color the gradient stop color
   */
  public void setGradientStop(double start, int color) {
    mGradientStopValue = start;
    mGradientStopColor = color;
  }

  /**
   * Returns if the legend item for this renderer should be visible.
   * ���� ��Ⱦ����ͼ����Ŀ�Ƿ�ɼ���
   * @return the visibility flag for the legend item for this renderer
   */
  public boolean isShowLegendItem() {
    return mShowLegendItem;
  }

  /**
   * Sets if the legend item for this renderer should be visible.
   * ���� ��Ⱦ����ͼ����Ŀ�Ƿ�ɼ���
   * @param showLegend the visibility flag for the legend item for this renderer
   */
  public void setShowLegendItem(boolean showLegend) {
    mShowLegendItem = showLegend;
  }

  /**
   * Returns if the item is displayed highlighted.
   * ���� �Ƿ���ʾΪһ������Ƭ������(��ͼ��ʾƬ�ֽ���ͼ)�� 
   * @return the highlighted flag for the item for this renderer
   */
  public boolean isHighlighted() {
    return mHighlighted;
  }

  /**
   * Sets if the item for this renderer should be highlighted. Pie chart is supported for now.
   * ���� �Ƿ���ʾΪһ������Ƭ�����򣬱�ͼ��֧�֡� 
   * @param highlighted the highlighted flag for the item for this renderer
   */
  public void setHighlighted(boolean highlighted) {
    mHighlighted = highlighted;
  }

  /**
   * Returns the number format for displaying chart values.
   * ���� ���ָ�ʽ��ʾ���ֵ��
   * @return the number format for chart values
   */
  public NumberFormat getChartValuesFormat() {
    return mChartValuesFormat;
  }

  /**
   * Sets the number format for displaying chart values.
   * ���� ���ָ�ʽ��ʾ���ֵ��
   * @param format the number format for chart values
   */
  public void setChartValuesFormat(NumberFormat format) {
    mChartValuesFormat = format;
  }

}
