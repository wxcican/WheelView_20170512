package com.fuicuiedu.xc.wheelview_20170512;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.fuicuiedu.xc.wheelview_20170512.view.MyWheelView;
import com.fuicuiedu.xc.wheelview_20170512.view.WheelView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyWheelView wheelView = (MyWheelView) findViewById(R.id.main_wheel_view);

        ArrayList<String> list = new ArrayList<>();
        list.add("北京");
        list.add("天津");
        list.add("河北");
        list.add("保定");
        list.add("南京");
        list.add("厦门");
        list.add("三亚");
        list.add("海南");
        list.add("成都");
        list.add("重庆");
        list.add("安徽");
        list.add("上海");
        list.add("新疆");
        list.add("拉萨");
        list.add("青海");
        list.add("东南");
        list.add("西南");
        list.add("东北");
        list.add("东西");
        list.add("东东");
        list.add("南北");
        list.add("南西");
        list.add("西北");
        list.add("西东");
        list.add("北南");
        list.add("北东");
        list.add("北西");

        wheelView.setData(list);

        //添加监听
        wheelView.getWheelView().setOnSelectedListener(new WheelView.OnSelectedListener() {
            @Override
            public void selected(int id, String text) {
                Toast.makeText(MainActivity.this, "选择了" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
