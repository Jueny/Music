package com.example.music.task;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by john on 2018/6/10.
 */

public class JsonTask extends AsyncTask <String,Void,String>{
    CallBack callBack;
    public JsonTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... params) {
        String result=null;

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(params[0]).build();
        try {
            Response response=client.newCall(request).execute();
            result=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(callBack!=null){
            callBack.getData(result);
        }
    }

    public interface CallBack{
        void getData(String result);
    }
}
