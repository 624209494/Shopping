package com.example.shopping.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.shopping.Beans.ShoppingBenas;
import com.example.shopping.R;
import com.example.shopping.adapters.MyPagerAdapter;
import com.example.shopping.app.MyServer;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private ViewPager mMyDassifyFragment;
    private ArrayList<Fragment> frameLayouts = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;
    private static final String TAG = "DassifyFragment";
    public DassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_dassify, null);
        initView(inflate);
        initToolbar();
        initData();
        initFragment();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyServer.getUtl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        myServer.getShopping("api/taohuasuan/getHotItems/?c_src=5&cids=9000&page=1&size=10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingBenas>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ShoppingBenas value) {
                        Log.e(TAG, "onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initFragment() {
        for (int i = 0; i < 11; i++) {
            frameLayouts.add(new DassifytwoFragment());

        }
        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), frameLayouts);
        mMyDassifyFragment.setAdapter(myPagerAdapter);
        mMyDassifyTab.setupWithViewPager(mMyDassifyFragment);

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
        mMyDassifyFragment = (ViewPager) inflate.findViewById(R.id.myDassify_fragment);
    }
}
