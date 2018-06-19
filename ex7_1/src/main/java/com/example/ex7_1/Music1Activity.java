package com.example.ex7_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ex7_1.adapter.NewsAdapter;
import com.example.ex7_1.bean.News;
import com.example.ex7_1.task.JSONTask;
import com.example.ex7_1.utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;

public class Music1Activity extends AppCompatActivity {
    ListView newsList;
    View footerView;
    TextView load;
    NewsAdapter adapter;
    List<News> data;
    int curpage=1;
    boolean isDown,isLoad=true;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music1); newsList=(ListView)findViewById(R.id.news_list);
        footerView= LayoutInflater.from(this).inflate(R.layout.footview,null);
        load=(TextView)footerView.findViewById(R.id.load);
        newsList.addFooterView(footerView);
        data=new ArrayList<>();
        adapter=new NewsAdapter(this,R.layout.news_item,data);
        newsList.setAdapter(adapter);
        home=(Button)findViewById(R.id.home_page);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Music1Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        loadData();
        newsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(isDown&&scrollState==SCROLL_STATE_IDLE){
                    //加载数据
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount){
                    isDown=true;
                }else{
                    isDown=false;
                }
            }
        });

    }
    public void loadData(){
        if(isLoad){
            load.setText("正在加载数据，请稍后...");
            isLoad=false;
            new JSONTask(new JSONTask.CallBack() {
                @Override
                public void getData(String result) {
                    data.addAll(JSONUtils.parseJson(result));
                    adapter.notifyDataSetChanged();
                }
            }).execute("https://www.sojson.com/api/qqmusic/1084857413");
            curpage++;
            isLoad=true;
            load.setText("加载数据");
        }
    }
}
