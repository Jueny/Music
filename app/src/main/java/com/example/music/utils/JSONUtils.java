package com.example.music.utils;

import android.text.TextUtils;

import com.example.music.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 * 解析JSON格式数据，传入字符串表示的JSON数据，得到List<News>类型的结果
 */

public class JSONUtils {
    public static List<Music> parseJson(String data){
        List<Music> result=new ArrayList<>();
        if(TextUtils.isEmpty(data)){
            return result;
        }
        try {
            JSONObject object1=new JSONObject(data);
//            JSONObject object2=object1.getJSONObject("music");
            JSONArray array=object1.getJSONArray("music");
            for (int i=0;i<array.length();i++){
                JSONObject object3=array.getJSONObject(i);
//                JSONObject object4=object3.getJSONObject("data");
                Music musices=new Music();
                musices.setMusicName(object3.getString("title"));
                musices.setMusicContent(object3.getString("album"));
//                musices.setImgUrl(object4.getString("cover"));
//                musices.setNewsData(object4.getString("changed"));
                result.add(musices);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
