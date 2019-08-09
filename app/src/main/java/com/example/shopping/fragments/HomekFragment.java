package com.example.shopping.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.shopping.CaptureActivity;
import com.example.shopping.MainActivity;
import com.example.shopping.R;
<<<<<<< HEAD
import com.example.shopping.adapters.BannerAdapter;
=======
>>>>>>> origin/master
import com.example.shopping.adapters.HomeGoodsAdapter;
import com.example.shopping.simp.SimpleGoods;
import com.youth.banner.Banner;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomekFragment extends Fragment {


    private View view;
    private ImageView mEwm;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private SearchView searchView;
    private ListView listView;
    //定义自动完成的列表
    private static final int[] PROMOTE_COLORS = {
            R.color.purple,
            R.color.orange,
            R.color.pink,
            R.color.colorPrimary
    };

    private static final int[] PROMOTE_PLACE_HOLDER = {
            R.drawable.mask_round_purple,
            R.drawable.mask_round_orange,
            R.drawable.mask_round_pink,
            R.drawable.mask_round_yellow
    };

    private SimpleGoods simpleGoods;

    public static HomekFragment newInstance() {
        return new HomekFragment();
    }
    private HomeGoodsAdapter mGoodsAdapter; // 首页商品列表适配器.
    private BannerAdapter<Banner> mBannerAdapter; //轮播图适配器.


    public HomekFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_homek, null);
        initView(inflate);
        initSearchView();
        return inflate;
    }

    private void initSearchView() {
//        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mStrings);
//        listView.setAdapter(adapter);
//        //为ListView启动过滤
//        listView.setTextFilterEnabled(true);

        //设置SearchView自动缩小为图标
        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置默认提示文字
        searchView.setQueryHint("搜索");
        //配置监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                //此处添加查询开始后的具体时间和方法
                Toast.makeText(getActivity(),"搜索内容为:" + query,Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //如果newText长度不为0
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }else{
                    listView.setFilterText("");
//          adapter.getFilter().filter(newText.toString());
                }
                return true;
            }
        });
        //点击下方选择搜索
//        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Object string = adapter.getItem(position);
//                searchView.setQuery(string.toString(),true);
//            }
//        });
    }

    private void initView(View inflate) {
        mEwm = (ImageView) inflate.findViewById(R.id.ewm);
        mEwm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态权限申请
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
            }
        });
        searchView = (SearchView) inflate.findViewById(R.id.searchView);




        listView = (ListView) inflate.findViewById(R.id.lv);
    }

    private void goScan() {
        Toast.makeText(getActivity(), "扫描二维码", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(getActivity(), "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) { //1
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                Toast.makeText(getActivity(), "你扫描到的内容是：" + content, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
