package com.example.ex7_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ex7_1.adapter.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoadingActivity extends AppCompatActivity {
    ViewPager pager;
    ImgAdapter adapter;
    List<View> views=new ArrayList<>();
    //存放视图图片id
    int[] imgIds=new int[]{
            R.drawable.slide1,R.drawable.slide2
    };
    //存放圆点的图片控件对象
    ImageView[] dots=new ImageView[3];
    int curpage;
    SharedPreferences spf;
    boolean isFirst =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

//        spf = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean isFirst=spf.getBoolean("isFirst",true);
//        if (isFirst){
//            SharedPreferences.Editor editor=spf.edit();
//            editor.putBoolean("isFirst",false);
//            editor.apply();
//        }else{
//            Intent intent=new Intent(LoadingActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
        initView();
        initData();
    }
    public void setDots(int index){
        if (index<0||index>dots.length||index==curpage){
            return;
        }
        dots[index].setEnabled(false);
        dots[curpage].setEnabled(true);
        curpage=index;
    }
    public void setPage(int index){
        if (index<0||index>dots.length){
            return;
        }
        pager.setCurrentItem(index);
    }
    private void initData() {
        for (int i=0;i<imgIds.length;i++){
            ImageView view=new ImageView(this);
            view.setImageResource(imgIds[i]);
            views.add(view);
        }
        LayoutInflater inflater=LayoutInflater.from(this);
        View view3=inflater.inflate(R.layout.start_layout,null);
        View login=view3.findViewById(R.id.imageView);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        views.add(view3);
        adapter=new ImgAdapter(views);
        pager.setAdapter(adapter);
    }

    private void initView() {
        pager=(ViewPager)findViewById(R.id.view_pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setDots(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LinearLayout layout=(LinearLayout)findViewById(R.id.dots_layout);
        for (int i=0;i<dots.length;i++){
            dots[i]=(ImageView) layout.getChildAt(i);//小圆点的编号赋值给dots
            dots[i].setEnabled(true);
            dots[i].setTag(i);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=(int)v.getTag();
                    setDots(index);
                    setPage(index);
                }
            });
        }
        curpage=0;
        dots[curpage].setEnabled(false);
    }
}
