package com.example.music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.music.Adapter.MusicAdapter;
import com.example.music.task.JsonTask;
import com.example.music.utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;

public class Music1Activity extends AppCompatActivity {
    ListView musicsList;
    View footerView;
    TextView load;
    MusicAdapter adapter;
    List<Music> data;
    int curpage=1;
    boolean isDown,isLoad=true;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music1);
        musicsList=(ListView)findViewById(R.id.musics_list);
        footerView= LayoutInflater.from(this).inflate(R.layout.footview,null);
        load=(TextView)footerView.findViewById(R.id.load);
        musicsList.addFooterView(footerView);
        data=new ArrayList<>();
        adapter=new MusicAdapter(this,data,R.layout.music_item2);
        musicsList.setAdapter(adapter);
        loadData();
        musicsList.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        home=(Button)findViewById(R.id.home_page);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Music1Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void loadData(){
        if(isLoad){
            load.setText("正在加载数据，请稍后...");
            isLoad=false;
            new JsonTask(new JsonTask.CallBack() {
                @Override
                public void getData(String result) {
                    data.addAll(JSONUtils.parseJson(result));
                    adapter.notifyDataSetChanged();
                }
            }).execute("http://storage.googleapis.com/automotive-media/music.json");
            curpage++;
            isLoad=true;
            load.setText("加载数据");
        }
    }
}
