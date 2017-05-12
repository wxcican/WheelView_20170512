package com.fuicuiedu.xc.wheelview_20170512.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.fuicuiedu.xc.wheelview_20170512.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class WheelView extends LinearLayout{
    private Context context;
    /**使得控件可以滑动*/
    private Scroller scroller;
    /**控件的高度*/
    private int Height;
    /**控件的宽度*/
    private int Width;
    /**显示的条目个数*/
    private int itemCount;
    /**条目单元格的高度*/
    private int unitHeight;
    /**选中内容的字体大小*/
    private int selectedFontSize = 20;
    /**选中内容的字体颜色*/
    private int selectedFontColor;
    /**未选中内容的字体大小*/
    private int normalFontSize = 16;
    /**未选中内容的字体颜色*/
    private int normalFontColor;
    /**条目集合*/
    private ArrayList<TextView> wheelItems;
    /**每一次操作屏幕时的y值*/
    private int lastY;
    /**每一个条目的布局设置*/
    private LayoutParams params;
    /**滑动速度跟踪器*/
    private VelocityTracker tracker;
    /**当前选中项的索引*/
    private int currentIndex;
    /**最初设置的选中项的索引*/
    private int firstSelectedIndex;
    //xml中的属性
    private AttributeSet attrs;

    public WheelView(Context context) {
        this(context,null);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.attrs = attrs;
        initView();//初始化数据
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        this.context = getContext();
        scroller = new Scroller(context);
        tracker = VelocityTracker.obtain();//滑动速度跟踪器

        itemCount = 7;//显示的条目个数
        unitHeight = changeUnit(TypedValue.COMPLEX_UNIT_DIP,50);//条目单元格高度
        selectedFontColor = Color.BLACK;//选择为黑色
        normalFontColor = Color.GRAY;//选择为灰色

        //获取xml里的配置
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyWheelView);
        itemCount = array.getInteger(R.styleable.MyWheelView_itemNumber,itemCount);
        unitHeight = (int) array.getDimension(R.styleable.MyWheelView_unitHeight, unitHeight);
        selectedFontSize = array.getInteger(R.styleable.MyWheelView_selectedTextSize, selectedFontSize);
        selectedFontColor = array.getColor(R.styleable.MyWheelView_selectedTextColor, selectedFontColor);
        normalFontSize = array.getInteger(R.styleable.MyWheelView_normalTextSize, normalFontSize);
        normalFontColor = array.getColor(R.styleable.MyWheelView_normalTextColor, normalFontColor);
        firstSelectedIndex = array.getInteger(R.styleable.MyWheelView_selectedDefaultIndex, (itemCount/2));//如果不设置,默认选中屏幕中间项
        array.recycle();//用完释放掉，防止多次creat占用内存

        //控件的高度
        Height = itemCount * unitHeight;
        //每一个条目的布局设置
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,unitHeight);
        params.gravity = Gravity.CENTER_HORIZONTAL;//水平居中
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Width = getMeasuredWidth();
        //根据期望显示的条目个数配置控件的高度
        setMeasuredDimension(Width,Height);
    }

    //控制控件的滑动
    @Override
    public void computeScroll() {
        //判断滑动动画未停止
        if (scroller.computeScrollOffset()){
            //指定滑动位置
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    //处理触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO: 2017/5/12 0012 摁下，滑动，松开 
        return super.onTouchEvent(event);
    }

    //将传进来的值变为期望的值
    //unit 期望转换得到值的类型（TypedValue.COMPLEX_UNIT_DIP,得到dp类型的值）
    //value 宽度或者字体大小
    public int changeUnit(int unit,int value){
        int result = (int)TypedValue.applyDimension(unit,value,context.getResources().getDisplayMetrics());
        return result;
    }
}
