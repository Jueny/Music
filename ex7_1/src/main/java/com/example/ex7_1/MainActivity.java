package com.example.ex7_1;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ex7_1.adapter.ImgAdapter;
import com.example.ex7_1.adapter.NewsAdapter;
import com.example.ex7_1.bean.News;
import com.example.ex7_1.task.JSONTask;
import com.example.ex7_1.utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    ImgAdapter adapter;
    LinearLayout layout;
    List<View> views=new ArrayList<>();
    int []imgIds=new int[]{
            R.drawable.loge1,R.drawable.loge2,R.drawable.loge3
    };
    ImageView[] dots=new ImageView[3];
    int curpage;//存放当前页（第几页
    Button recommend;
//    View loge1,loge2,loge3;

//    private ArrayList<Music> musicList=new ArrayList<Music>();

    //    int [] imageIds=new int[]{};
//    String[] MusicName=new String[]{"apple","banana","cherry","grape","mango",
//            "orange", "pear", "pineapple","strawberry","watermelon"};
//    String[] MusicContent=new String[]{"","","",""};
//    MusicAdapter mucAdapter=new MusicAdapter(this,fruitList,R.layout.music_item);
//    MucListView.setAdapter(mucAdapter);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView=(ListView)findViewById(R.id.list_value);
        initView();
        initData();
        pager.setAdapter(adapter);
        recommend=(Button)findViewById(R.id.recommend);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Music1Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        for(int i=0;i<imgIds.length;i++){
            ImageView image=new ImageView(this);
            image.setImageResource(imgIds[i]);
            views.add(image);
        }
        adapter=new ImgAdapter(views);
    }

    public void setDots(int index) {
        if (index<0||index>dots.length||index==curpage){//curpage当前页
            return;
        }
        dots[index].setEnabled(false);
        dots[curpage].setEnabled(true);
        curpage=index;
    }
    public void setPage(int index) {
        if(index<0||index>imgIds.length){
            return;
        }
        pager.setCurrentItem(index);
    }

    private void initView() {
        pager=(ViewPager)findViewById(R.id.view_pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//侧滑容器监听器
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        layout=(LinearLayout)findViewById(R.id.dots_layout);
        for(int i=0;i<dots.length;i++){
            dots[i]=(ImageView)layout.getChildAt(i);
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
        curpage=0;//当前页0下标
        dots[curpage].setEnabled(false);
    }
}
