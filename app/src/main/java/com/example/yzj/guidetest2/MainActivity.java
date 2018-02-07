package com.example.yzj.guidetest2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4;
    private GuideView guideView;
    private GuideView guideView3;
    private GuideView guideView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button4:
                showGuideView();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showGuideView();
    }

    private void showGuideView() {

        // 使用图片
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.img_new_task_guide);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);

        // 使用文字
        final TextView tv = new TextView(this);
        tv.setText("开始引导");
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);

        // 使用文字
        final TextView tv2 = new TextView(this);
        tv2.setText("第一步");
        tv2.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextSize(30);
        tv2.setGravity(Gravity.CENTER);

        // 使用文字
        final TextView tv3 = new TextView(this);
        tv3.setText("第二步");
        tv3.setTextColor(getResources().getColor(R.color.white));
        tv3.setTextSize(30);
        tv3.setGravity(Gravity.CENTER);


        guideView = GuideView.Builder
                .newInstance(this)                                           // 必须调用
                .setTargetView(button1)//设置目标                             // 必须调用，设置需要Guide的View
                .setCustomGuideView(tv)                                      // 必须调用，设置GuideView，可以使任意View的实例，比如ImageView 或者TextView
                .setDirction(GuideView.Direction.LEFT_BOTTOM)                // 设置GuideView 相对于TargetView的位置，有八种，不设置则默认在屏幕左上角,其余的可以显示在右上，右下等等
                .setShape(GuideView.MyShape.CIRCULAR)   // 设置圆形显示区域，  // 设置显示形状，支持圆形，椭圆，矩形三种样式，矩形可以是圆角矩形
                .setBgColor(getResources().getColor(R.color.shadow))         // 设置背景颜色，默认透明
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView.hide();
                        guideView2.show();                                   // 必须调用，显示GuideView
                    }
                })
                .build();                                                    // 必须调用，Buider模式，返回GuideView实例


        guideView2 = GuideView.Builder
                .newInstance(this)
                .setTargetView(button2)
                .setCustomGuideView(tv2)
                .setDirction(GuideView.Direction.RIGHT_BOTTOM)
                .setShape(GuideView.MyShape.ELLIPSE)   // 设置椭圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView2.hide();
                        guideView3.show();
                    }
                })
                .build();


        guideView3 = GuideView.Builder
                .newInstance(this)
                .setTargetView(button3)
                .setCustomGuideView(tv3)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.RECTANGULAR)   // 设置矩形显示区域，
                .setRadius(80)          // 设置圆形或矩形透明区域半径，默认是targetView的显示矩形的半径，如果是矩形，这里是设置矩形圆角大小
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView3.hide();
                        //guideView.show();
                    }
                })
                .build();

        guideView.show();
    }
}
