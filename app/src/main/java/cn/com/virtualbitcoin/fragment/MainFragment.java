package cn.com.virtualbitcoin.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.GetSweetActivity;
import cn.com.virtualbitcoin.activity.PriceActivity;
import cn.com.virtualbitcoin.activity.RateActivity;
import cn.com.virtualbitcoin.activity.WebViewActivity;
import cn.com.virtualbitcoin.adapter.MyViewPagerAdapter;
import cn.com.virtualbitcoin.adapter.NoTouchViewPager;
import cn.com.virtualbitcoin.bean.Banner;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.banner_fresco_demo_content)
    BGABanner bannerFrescoDemoContent;
    @Bind(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @Bind(R.id.view_pager)
    NoTouchViewPager viewPager;
    private List<String> mDataList = new ArrayList<>();


    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
        initBanner();
        initFragment();
        return view;
    }

    private void initBanner() {
        bannerFrescoDemoContent.setAdapter(new BGABanner.Adapter<ImageView, Banner.BannerBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, Banner.BannerBean model, int position) {
                Glide.with(getActivity())
                        .load(model.getLogo())
                        .centerCrop()
                        .into(itemView);
            }
        });
        bannerFrescoDemoContent.setDelegate(new BGABanner.Delegate<ImageView, Banner.BannerBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, Banner.BannerBean model, int position) {

                Intent intent=new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra("title",model.getName());
                intent.putExtra("url",model.getLink());
                startActivity(intent);
            }
        });
        Api.getReQuest(Api.GET_BANNER, getActivity(), new JSONObject(), new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                Gson gson=new Gson();
                Banner banner = gson.fromJson(data.toString(), Banner.class);
                bannerFrescoDemoContent.setData(banner.getBanner(),null);
            }
            @Override
            public void requestFailure(int code, String msg) {

            }
        });

    }


    private void initFragment() {
        mDataList.add(getResources().getString(R.string.sweets));
        mDataList.add(getResources().getString(R.string.rate));
        List<Fragment> list = new ArrayList<>();
        list.add(new SweetsFragment());
        list.add(new RateFragment());
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(myViewPagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(i));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.black_333));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.colorPrimary));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(i);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                indicator.setLineHeight(3);
                indicator.setColors(getResources().getColor(R.color.colorPrimary));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(Utils.getApp(), 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));

        final FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(magicIndicator);
        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
        fragmentContainerHelper.setDuration(300);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                fragmentContainerHelper.handlePageSelected(position);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick({R.id.way_sweet, R.id.way_rate, R.id.way_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.way_sweet:
                ActivityUtils.startActivity(GetSweetActivity.class);
                break;
            case R.id.way_rate:
                ActivityUtils.startActivity(RateActivity.class);
                break;
            case R.id.way_price:
                ActivityUtils.startActivity(PriceActivity.class);
                break;
            default:
                break;
        }
    }
}
