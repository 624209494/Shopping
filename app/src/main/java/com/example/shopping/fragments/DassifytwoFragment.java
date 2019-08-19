package com.example.shopping.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopping.Beans.ShoppingBenas;
import com.example.shopping.R;
import com.example.shopping.app.MyServer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class DassifytwoFragment extends Fragment {
    private View view;
    private RecyclerView mMyRec;
    int id;

    private static final String TAG = "DassifytwoFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dassify_two_frangent, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(MyServer.getUtl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = build.create(MyServer.class);
        myServer.getShopping("api/taohuasuan/getHotItems/?c_src=5&cids=9000&page="+id+"&size=10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingBenas>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " );
                    }

                    @Override
                    public void onNext(ShoppingBenas value) {
                        List<ShoppingBenas.DataBean.ItemsBean> items = value.getData().getItems();
                        Log.e(TAG, "onNext: " +items.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }

    private void initView(View view) {
        mMyRec = (RecyclerView) view.findViewById(R.id.myRec);
        mMyRec.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}
