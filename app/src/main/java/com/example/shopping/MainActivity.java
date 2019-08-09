package com.example.shopping;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.shopping.app.Constants;
import com.example.shopping.fragments.DassifyFragment;
import com.example.shopping.fragments.FoundFragment;
import com.example.shopping.fragments.HomekFragment;
import com.example.shopping.fragments.MyFragment;
import com.example.shopping.fragments.ShopFragment;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity  implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks{

    private static final int REQUEST_FILE_CODE = 99;

    private RelativeLayout mFrame;
    private BottomNavigationView mBtnMainNav;
    private FragmentTransaction fragmentTransaction;
    private HomekFragment homekFragment;
    private DassifyFragment dassifyFragment;
    private FoundFragment foundFragment;
    private MyFragment myFragment;
    private ShopFragment shopFragment;
    Fragment currFragment;
    private MenuItem lastltem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
        // 判断有没有这些权限
        if (EasyPermissions.hasPermissions(this, permissions)) {
            initView();
        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, REQUEST_FILE_CODE, permissions)
                            .setRationale("请确认相关权限！！")
                            .setPositiveButtonText("ok")
                            .setNegativeButtonText("cancal")
//                            .setTheme(R.style.my_fancy_style)
                            .build());
        }
        initView();
        initFragment();


    }

    private void initFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragmentTransaction!=null){
            homekFragment = new HomekFragment();
            dassifyFragment = new DassifyFragment();
            foundFragment = new FoundFragment();
            shopFragment = new ShopFragment();
            myFragment = new MyFragment();

            fragmentTransaction.add(R.id.frame,homekFragment);
            currFragment=homekFragment;
            fragmentTransaction.show(homekFragment);
            fragmentTransaction.commit();
            lastltem = mBtnMainNav.getMenu().findItem(R.id.navmain);
            mBtnMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.navmain:
                            showType=Constants.TYPE_HOME;
                            //获取要切换的fragment
                            Fragment targetFragment = getTargetFragment(showType);
                            switchFragment(targetFragment);
                            break;
                        case R.id.navfen:
                            showType=Constants.TYPE_DASSIFY;
                            //获取要切换的fragment
                            targetFragment = getTargetFragment(showType);
                            switchFragment(targetFragment);
                            break;

                        case R.id.navfa:
                            showType=Constants.TYPE_FOUND;
                            //获取要切换的fragment
                            targetFragment = getTargetFragment(showType);
                            switchFragment(targetFragment);
                            break;

                        case R.id.navshop:
                            showType=Constants.TYPE_SHOP;
                            //获取要切换的fragment
                            targetFragment = getTargetFragment(showType);
                            switchFragment(targetFragment);
                            break;

                        case R.id.navmy:
                            showType=Constants.TYPE_MY;
                            //获取要切换的fragment
                            targetFragment = getTargetFragment(showType);
                            switchFragment(targetFragment);
                            break;

                    }
                    if (lastltem!=null){
                        menuItem.setChecked(false);
                    }

                    menuItem.setChecked(true);
                    lastltem=menuItem;

                    return false;
                }
            });
        }
    }

    private void initView() {
        mFrame = (RelativeLayout) findViewById(R.id.frame);
        mBtnMainNav = (BottomNavigationView) findViewById(R.id.btn_mainNav);

    }
    int showType= Constants.TYPE_HOME;

    public void switchFragment(Fragment targetFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //目标对象没有加入过
        if (!targetFragment.isAdded()){
            if (currFragment!=null){
                transaction.hide(currFragment);
                transaction.add(R.id.frame,targetFragment,targetFragment.getClass().getName());
            }
        }else{
            //如果目标fragment已经在事务里,显示即可
            transaction.hide(currFragment).show(targetFragment);
        }
        currFragment=targetFragment;
        transaction.commit();
    }
    private Fragment getTargetFragment(int item){
        switch (item){
            case Constants.TYPE_HOME:
                return homekFragment;
            case Constants.TYPE_DASSIFY:
                return dassifyFragment;
            case Constants.TYPE_FOUND:
                return foundFragment;
            case Constants.TYPE_SHOP:
                return shopFragment;
            case Constants.TYPE_MY:
                return myFragment;
        }
        return homekFragment;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        initView();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRationaleAccepted(int requestCode) {

    }

    @Override
    public void onRationaleDenied(int requestCode) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
