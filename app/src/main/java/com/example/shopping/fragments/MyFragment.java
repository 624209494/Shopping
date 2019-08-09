package com.example.shopping.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopping.R;

import java.io.File;
import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView mMyimg;
    /**
     * 我的订单
     */
    private TextView mMyorder;
    /**
     * 全部订单
     */
    private TextView mAllorder;
    private CardView mCardmy;
    private final int LOCAL = 1;
    private final int CAMERA = 2;
    private final int CUT = 3;
    public static final String IMAGE_FILE_NAME = "clip_temp.jpg";
    public static final String RESULT_PATH = "result_path";
    public static final String PASS_PATH = "pass_path";
    private PopupWindow popupWindow;
    private TranslateAnimation animation;
    private View popupView;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        initView(inflate);
        // 头像点击换头像
        initimg();
        return inflate;
    }

    public void showUpPop() {

        if (popupWindow == null) {

            popupView = View.inflate(getActivity(), R.layout.popwindow_avatar_layout, null);
            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override

                public void onDismiss() {


                }

            });


            // 设置背景图片， 必须设置，不然动画没作用

            popupWindow.setBackgroundDrawable(new BitmapDrawable());

            popupWindow.setFocusable(true);

            // 设置点击popupwindow外屏幕其它地方消失

            popupWindow.setOutsideTouchable(true);

            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0

            animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,

                    Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);


            animation.setInterpolator(new AccelerateInterpolator());

            animation.setDuration(200);

        }


        //设置按钮点击监听

        popupView.findViewById(R.id.close_popup).setOnClickListener(this);

        popupView.findViewById(R.id.open_photos).setOnClickListener(this);

        popupView.findViewById(R.id.open_camera).setOnClickListener(this);


        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow.showAtLocation(popupView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView.startAnimation(animation);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_popup:
                //关闭popup
                popupWindow.dismiss();
                break;
            case R.id.open_camera: //打开相册操作
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                popupWindow.dismiss();
                break;
            case R.id.open_photos://打开相机操作
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);
                // 采用ForResult打开                dialog.dismiss();
                break;
        }
    }


    private void initimg() {
        mMyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpPop();
            }
        });
    }









    private void initView(View inflate) {
        mMyimg = (ImageView) inflate.findViewById(R.id.myimg);
        mMyorder = (TextView) inflate.findViewById(R.id.myorder);
        mAllorder = (TextView) inflate.findViewById(R.id.allorder);
        mCardmy = (CardView) inflate.findViewById(R.id.cardmy);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(this).load(R.mipmap.avatar).apply(requestOptions).into(mMyimg);
    }


}
