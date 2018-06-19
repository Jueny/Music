package com.example.ex7_1.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ex7_1.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    TextView title,content;
    View view;//碎片布局对应的视图

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //把碎片的布局加载，得到相应的视图
        view=inflater.inflate(R.layout.fragment_content, container, false);//inflater布局填充器（打气筒）
        return view;
    }
    public void refresh(String titleString ,String contentString){
        View layout=view.findViewById(R.id.visible_layout);
        layout.setVisibility(View.VISIBLE);//将控件设为可见
        title=(TextView)view.findViewById(R.id.title);
        content=(TextView)view.findViewById(R.id.content);
        title.setText(titleString);
        content.setText(contentString);
    }

}
