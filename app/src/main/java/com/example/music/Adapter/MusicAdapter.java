package com.example.music.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.music.Music;
import com.example.music.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2018/6/9.
 */

public class MusicAdapter extends ArrayAdapter {
    private  int resourceId;
    public MusicAdapter(Context context, List<Music> objects, int resource) {
        super(context, resource, objects);
        resourceId=resource;
    }
    class ViewHoder{
        ImageView musicImage;
        TextView musicName;
        TextView musicContent;
    }
    @NonNull
    @Override
    //当显示新的列表项时，getView（）被调用
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        * 1.position：要获取布局视图的列表项的编号
        * getContext():获取上下文
        * LayoutInflater.from(getContext())获取“打气筒”对象（布局填充器）
        *
        * */
        //获取要显示的列表项数据类对象

        //获取要显示的列表项数据视图
        final Map<String,Bitmap> map=new HashMap<>();
        View view;
        ViewHoder hoder=new ViewHoder();
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            hoder.musicImage=(ImageView)view.findViewById(R.id.music_image);
            hoder.musicName=(TextView)view.findViewById(R.id.music_title);
            hoder.musicContent=(TextView)view.findViewById(R.id.music_content);
            view.setTag(hoder);//保存hoder对象
        }
        else{
            view=convertView;
            hoder= (ViewHoder) view.getTag();
        }
        Music music= (Music) getItem(position);
        //给各个控件设置要显示的内容
        hoder.musicName.setText(music.getMusicName());
        hoder.musicContent.setText(music.getMusicContent());
        hoder.musicImage.setImageResource(music.getImgId());
//        final String url=music.getImgUrl();
        //执行异步任务得到图片
//        Bitmap bitmap=map.get(url);
//        if(bitmap!=null){
//            holder.newsImage.setImageBitmap(bitmap);
//        }else{
//            //final只能赋值一次
//            final ViewHolder finalHolder = holder;
//            new ImageTask(new ImageTask.CallBack() {
//                @Override
//                public void getData(Bitmap pic) {
//                    finalHolder.newsImage.setImageBitmap(pic);
//                    //放入缓存
//                    map.put(url,pic);
//                }
//            }).execute("http://litchiapi.jstv.com"+url);
//        }
        return view;
    }
}
