package com.example.music;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

//import com.example.music.Fragment.ContentFragment;

public class ContentActivity extends AppCompatActivity {
    TextView title,content;
    View view;


    TextView startText,maxText;
    SeekBar bar;
    MusicBroadcastReceiver receiver;
    //服务的遥控器
    MusicService.MusicControl control;
    //活动和服务的“纽带”
    ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            control=(MusicService.MusicControl)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    class MusicBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int max=intent.getIntExtra("max",0);
            int current=intent.getIntExtra("current",0);
            bar.setMax(max);
            bar.setProgress(current);

            java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("mm:ss");
            maxText.setText(formatter.format(max));
            startText.setText(formatter.format(current));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intentCon=getIntent();
        String titleString=intentCon.getStringExtra("title");
        String contentString=intentCon.getStringExtra("content");

        View layout=view.findViewById(R.id.visible_layout);
        layout.setVisibility(View.VISIBLE);//设置可见
        title=(TextView)view.findViewById(R.id.title);
        content=(TextView)view.findViewById(R.id.content);
        title.setText(titleString);
        content.setText(contentString);
//        ListFragment fragment=(ListFragment)getSupportFragmentManager()
//                .findFragmentById(R.id.activity_content);
//        fragment.refresh(title,content);
//    }
//}
        Intent intent=new Intent(this,MusicService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);

        bar=(SeekBar)findViewById(R.id.seek_bar);
        startText=(TextView)findViewById(R.id.start);
        maxText=(TextView)findViewById(R.id.max);
        //设置进度条拖动的事件监听器
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                control.musicSeekTo(seekBar.getProgress());
            }
        });
        //注册广播接收器
        IntentFilter filter=new IntentFilter("com.example.music.MUSICBROCTASK");
        receiver=new MusicBroadcastReceiver();
        registerReceiver(receiver,filter);
    }
    public void myClick(View view){
        switch (view.getId()){
            case R.id.play:
                control.musicPlay(bar.getProgress());

                break;
            case R.id.pause:
                control.musicPause();
                break;
            case R.id.stop:
                control.musicStop();
                break;
            default:
                ;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
