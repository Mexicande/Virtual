package cn.com.virtualbitcoin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.adapter.MyViewPagerAdapter;
import cn.com.virtualbitcoin.adapter.NoTouchViewPager;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.fragment.CenterFragment;
import cn.com.virtualbitcoin.fragment.MainFragment;
import cn.com.virtualbitcoin.fragment.TerraceFragment;
import cn.com.virtualbitcoin.utils.AppUtils;
import cn.com.virtualbitcoin.utils.StatusBarUtil;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.view.NormalItemView;
import cn.com.virtualbitcoin.view.update.AppUpdateUtils;
import cn.com.virtualbitcoin.view.update.CProgressDialogUtils;
import cn.com.virtualbitcoin.view.update.OkGoUpdateHttpUtil;
import cn.com.virtualbitcoin.view.update.UpdateAppBean;
import cn.com.virtualbitcoin.view.update.UpdateAppManager;
import cn.com.virtualbitcoin.view.update.UpdateCallback;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    NoTouchViewPager viewPager;
    @Bind(R.id.tab)
    PageNavigationView tab;
    private int  versionCode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateDiy();
        initTab();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void initTab() {

        NavigationController navigationController = tab.custom()
                .addItem(newItem(R.mipmap.iv_default_terrace, R.mipmap.iv_seclect_terrace, "平台/钱包"))
                .addItem(newItem(R.mipmap.iv_default_home, R.mipmap.iv_select_home, "主页"))
                .addItem(newItem(R.mipmap.iv_default_my, R.mipmap.iv_select_my, "我的"))
                .build();
        List<Fragment> list = new ArrayList<>();
        list.add(new TerraceFragment());
        list.add(new MainFragment());
        list.add(new CenterFragment());

        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), list));
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);
       viewPager.setCurrentItem(1);


    }
    public void updateDiy() {

        versionCode = AppUpdateUtils.getVersionCode(this);
        AppUtils.updateDiy(versionCode,this);

    }
    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView onlyIconItemView = new NormalItemView(this);
        onlyIconItemView.initialize(drawable, checkedDrawable, text);
        onlyIconItemView.setTextDefaultColor(Color.parseColor("#666666"));
        onlyIconItemView.setTextCheckedColor(Color.parseColor("#009EFD"));
        return onlyIconItemView;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
    }
    private long mLastBackTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次退出");
        }
    }
}
