package com.fuicuiedu.xc.wheelview_20170512.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fuicuiedu.xc.wheelview_20170512.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class MyWheelView extends FrameLayout {

    /**
     * 滚轮选择器
     */
    private WheelView wheelView;
    /**
     * 选择线的高度
     */
    private int lineHeight;
    /**
     * 选择线的颜色
     */
    private int lineColor;


    public MyWheelView(Context context) {
        this(context, null);
    }

    public MyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化滚轮选择器和上下的选择线并且显示到屏幕上
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wheelView = new WheelView(context, attrs);
        wheelView.setLayoutParams(params);
        wheelView.setBackgroundColor(Color.TRANSPARENT);
        int height = wheelView.getHeight2();

        //获取xml里的配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyWheelView);
        lineHeight = (int) array.getDimension(R.styleable.MyWheelView_lineHeight, wheelView.changeUnit(TypedValue.COMPLEX_UNIT_DIP, 2));
        lineColor = array.getColor(R.styleable.MyWheelView_lineColor, Color.BLACK);
        array.recycle();

        //计算出上下两条线距离顶端的距离
        int lineTopY = height / 2 - wheelView.getUnitHeight() / 2 + lineHeight;
        int lineBottomY = height / 2 + wheelView.getUnitHeight() / 2 - lineHeight;

        //初始化两条线的高宽
        LayoutParams lineParams1 = new LayoutParams(LayoutParams.MATCH_PARENT, lineHeight);
        LayoutParams lineParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, lineHeight);


        //创建两条线并且设置距离顶部距离以及线的颜色，并且把线添加到屏幕上
        View line1 = new View(context);
        line1.setBackgroundColor(lineColor);
        lineParams1.topMargin = lineTopY;
        Log.e("aaa","lineTopY=" + lineTopY);
        line1.setLayoutParams(lineParams1);
        addView(line1);

        View line2 = new View(context);
        line2.setBackgroundColor(lineColor);
        lineParams2.topMargin = lineBottomY;
        Log.e("aaa","lineBottomY=" + lineBottomY);
        line2.setLayoutParams(lineParams2);
        addView(line2);

        addView(wheelView);
    }

    public WheelView getWheelView(){
        return wheelView;
    }

    //设置数据
    public void setData(ArrayList<String> list){
        wheelView.setData(list);
    }
}
