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
import android.widget.TextView;

import com.example.music.ContentActivity;
import com.example.music.Music;
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
    TextView title,content;
    View view1;
    ListView list;
    List<String> data=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    View view;
/**/
    private List<Music> musicList=new ArrayList<>();
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
            data.add("伶仃");
            data.add("3333");
            data.add("hhhhhh");
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
                    intent.putExtra("content","伶仃\n" +
                            "词：田辰明\n" +
                            "曲：霍尊\n" +
                            "演唱：霍尊&张力尹\n" +
                            "尊：\n" +
                            "若非午夜梦里掠影\n" +
                            "你正莞尔扑进怀里\n" +
                            "我又怎会想起\n" +
                            "我非生来伶仃\n" +
                            "那年 那夜 多情\n" +
                            "尹：\n" +
                            "最后天各一方散尽\n" +
                            "就当我是无情无心\n" +
                            "光阴匆匆过去\n" +
                            "再没有什么让我伤心\n" +
                            "（男：我们）\n" +
                            "也不再动心\n" +
                            "合：\n" +
                            "怀念那样沉默一个你 微笑 站在 那里\n" +
                            "无论世事如何地更替 总是 如影 随形\n" +
                            "那时我 从未想过\n" +
                            "最后你我各伶仃\n" +
                            "为何真心\n" +
                            "于事无济\n" +
                            "想问最后消失那个你 此刻 又在 哪里\n" +
                            "可知我有平常的悲喜 应当 说给 谁听\n" +
                            "这世界 依然美丽\n" +
                            "人间繁花又开尽\n" +
                            "但没有你\n" +
                            "越美越是空虚\n" );
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





}
