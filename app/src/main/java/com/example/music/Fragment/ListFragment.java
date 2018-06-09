package com.example.music.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.music.ContentActivity;
import com.example.music.R;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    ListView list;
    List<String> data=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    View view;

    ////////////
    private String[] names={"content1.txt","content2.txt","content3.txt"};
    public ListFragment() {
        // Required empty public constructor
    }
//    StringBuffer buffer=new StringBuffer();
//    try{
//        FileInputStream
////    }
//InputStream is = getAssets().open(names[1]);
//    int lenght = is.available();
//    byte[]  buffer = new byte[lenght];
//    is.read(buffer);
//    String result = = new String(buffer, "utf8");
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        list=(ListView)view.findViewById(R.id.);
        for (int i=1;i<20;i++){
            data.add("这是第"+i+"条新闻");
        }
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list2, container, false);
        list=(ListView)view.findViewById(R.id.list_value);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent=new Intent(getActivity(), ContentActivity.class);//跳转到ContentActivity
                    intent.putExtra("title",data.get(position));
                    intent.putExtra("content","其一、日本变成了“局外人”，丧失了发言权，美国却没有关照日本的利益，也拒绝采纳日本的建议。\n" +
                            "\n" +
                            "根据媒体报道，白宫在5月13日大赞韩国北邻废弃核试验场的“壮举”。川普还声称准备了“弃核大礼包”。美国国务卿蓬佩奥在受访时也表示，如果能够就无核化达成协议，美国将提供安全承诺。他还声称“美国的利益是阻止该国向西海岸的洛杉矶发射核武，终极目标是不再受该国的核武威胁。”连极端强硬的美国国家安全事务助理博尔顿都表示“只要实现彻底的、可查证的、不可逆转的弃核，前途将非常光明。”\n" +
                            "\n");
                startActivity(intent);
//                InputStream is = ().open(names[1]);
//                int lenght = is.available();
//                byte[]  buffer = new byte[lenght];
//                is.read(buffer);
//                String result == new String(buffer, "utf8");



            }
        });

        return view;
    }




//    public static void readFileOnLine(String filePath){//输入文件路径
//        FileInputStream fis = openF//打开文件输入流
//        StringBuffer sBuffer = new StringBuffer();
//        DataInputStream dataIO = new DataInputStream(fis);//读取文件数据流
//        String strLine = null;
//        while((strLine =  dataIO.readLine()) != null) {//通过readline按行读取
//            sBuffer.append(strLine + "\n");//strLine就是一行的内容
//        }
//        dataIO.close();
//        fis.close();
//    }


}
