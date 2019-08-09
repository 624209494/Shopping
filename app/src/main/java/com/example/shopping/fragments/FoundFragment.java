package com.example.shopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopping.Beans.FoundBeans;
import com.example.shopping.R;
import com.example.shopping.adapters.FoundAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoundFragment extends Fragment {


    private View view;
    private Toolbar mFoundToolbar;
    private RecyclerView mFoundRec;
    private FoundAdapter foundAdapter;
    private ArrayList<FoundBeans> list;
    private View mStatusBarView;
    /**
     * 主页
     */
    private TextView mToolbarTitle;
    private Toolbar mToolbar;

    public FoundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_found, null);

        initView(view);
        initToolbar();
        initData();
        return view;
    }

    private void initToolbar() {
        mFoundToolbar.setTitle("");
        mToolbarTitle.setText("发现");
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new FoundBeans("用户一", R.mipmap.c));
        list.add(new FoundBeans("用户2", R.mipmap.c));
        foundAdapter.initData(list);
    }

    private void initView(View view) {
        mFoundRec = (RecyclerView) view.findViewById(R.id.Found_rec);
        mFoundRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        foundAdapter = new FoundAdapter(getActivity());
        mFoundRec.setAdapter(foundAdapter);

        mStatusBarView = (View) view.findViewById(R.id.status_bar_view);
        mToolbarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

    }
}
