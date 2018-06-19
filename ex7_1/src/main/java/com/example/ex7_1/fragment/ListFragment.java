package com.example.ex7_1.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ex7_1.ContentActivity;
import com.example.ex7_1.Music;
import com.example.ex7_1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    TextView title,content;
    View view1;
    ListView list;
    List<String> data=new ArrayList<>();
    ArrayAdapter<String> adapter;
    View view;
    /**/
    private List<Music> musicList=new ArrayList<>();
    ////////////
    private String[] names={"content1","content2","content3"};
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
        initMusic();
        for (int i=1;i<6;i++){
            data.add("伶仃");
            data.add("同类");
            data.add("勿念无忧");
            data.add("少年");
            data.add("十年");
            data.add("匆匆");
        }
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
    }

    private void initMusic(){
        for (int i=1;i<6;i++){
            Music mic1=new Music("伶仃","词：田辰明\n" +"曲：霍尊\n" +"演唱：霍尊&张力尹",R.drawable.img1);
            Music mic2=new Music("同类","演唱：胡夏",R.drawable.img2);
            Music mic3=new Music("勿念无忧","演唱：品冠&烦尹",R.drawable.img3);
            Music mic4=new Music("少年","演唱：光良",R.drawable.img4);
            Music mic5=new Music("十年","演唱：陈奕迅",R.drawable.img5);
            Music mic6=new Music("匆匆","演唱：李宗盛",R.drawable.img6);
            musicList.add(mic1);
            musicList.add(mic2);
            musicList.add(mic3);
            musicList.add(mic4);
            musicList.add(mic5);
            musicList.add(mic6);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list, container, false);
        list=(ListView)view.findViewById(R.id.list_value);
        list.setAdapter(adapter);
        Context context;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            /*parent           发生点击动作的AdapterView。
            view              在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
            position　    视图在adapter中的位置。
            id                   被点击元素的行id。*/
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getActivity(), ContentActivity.class);//跳转到ContentActivity
                intent.putExtra("title",data.get(position));
                intent.putExtra("content",contents[position]);
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
//    /**
//     * 读取assets下的txt文件，返回utf-8 String
//     * @param context
//     * @param fileName 不包括后缀
//     * @return
//     */
//    public static String readAssetsTxt(Context context, String fileName){
//        try {
//            //Return an AssetManager instance for your application's package
//            InputStream is = context.getAssets().open(fileName+".txt");
//            int size = is.available();
//            // Read the entire asset into a local byte buffer.
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            // Convert the buffer into a string.
//            String text = new String(buffer, "utf-8");
//            // Finally stick the string into the text view.
//            return text;
//        } catch (IOException e) {
//            // Should never happen!
////            throw new RuntimeException(e);
//            e.printStackTrace();
//        }
//        return "读取错误，请检查文件名";
//    }


    //
//    InputStream is = getAssets().open(fileName);
//    int lenght = is.available();
//    byte[]  buffer = new byte[lenght];
//    is.read(buffer);
//    String result = = new String(buffer, "utf8");
//    public static String readTxt(String fileName){
    String [] contents={"若非午夜梦里掠影\n" +
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
            "想问最后消失那个你 此刻 又在 哪里",
            "雨后的城市 寂寞又狼狈\n" +
                    "路边的座位 它空着在等谁\n" +
                    "我拉住时间 它却不理会\n" +
                    "有没有别人 跟我一样很想被安慰\n" +
                    "风 停了又吹 我忽然想起谁\n" +
                    "天 亮了又黑 我过了好几岁\n" +
                    "心 暖了又灰 世界\n" +
                    "有时候孤单的很需要另一个同类\n" +
                    "爱 收了又给 我们都不太完美\n" +
                    "梦 作了又碎 我们有几次机会\n" +
                    "去追\n" +
                    "我拉住时间 它却不理会",
            "你的疯狂\n" +
                    "你的梦想\n" +
                    "你一说到流浪\n" +
                    "就会慌张\n" +
                    "你的孤单\n" +
                    "你的倔强\n" +
                    "你酒后的荒唐\n" +
                    "掩藏忧伤\n" +
                    "看星光 拼凑成远方\n" +
                    "不去想 前路多漫长\n" +
                    "别让过往\n" +
                    "沾湿眼眶\n" +
                    "风雨都是晴朗\n" +
                    "好梦一场","怀念那样沉默一个你 微笑 站在 那里\n" +
            "只有思念能用来耗尽 漫漫 长长 光阴\n" +
            "那时我 如果懂得\n" +
            "一切最后会失去\n" +
            "我会等你\n" +
            "不再离去\n" +
            "想问最后消失那个你 此刻 又在 哪里\n" +
            "可知记忆越让人沉溺 越是 让人 惋惜\n" +
            "如果我 再遇见你\n" +
            "还能从头再说起\n" +
            "说给你听\n" +
            "我曾那么想你",
            "",""};
//    }

}
