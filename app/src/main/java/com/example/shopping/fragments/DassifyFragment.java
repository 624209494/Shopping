package com.example.shopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.shopping.R;

import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class DassifyFragment extends Fragment {


    private View view;
    private View mStatusBarView;
    /**
     * 主页
     */
    private TextView mToolbarTitle;
    private Toolbar mToolbar;
    private VerticalTabLayout mMyDassifyTab;
    private FrameLayout mMyDassifyFragment;


    public DassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_dassify, null);
        initView(inflate);
        initToolbar();
        return inflate;
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        mToolbarTitle.setText("分类");
    }

    private void initView(View inflate) {


        mStatusBarView = (View) inflate.findViewById(R.id.status_bar_view);
        mToolbarTitle = (TextView) inflate.findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        mMyDassifyTab = (VerticalTabLayout) inflate.findViewById(R.id.my_dassify_Tab);
        mMyDassifyFragment = (FrameLayout) inflate.findViewById(R.id.myDassify_fragment);
    }
}
