package cn.com.virtualbitcoin.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.MyViewPagerAdapter;
import cn.com.virtualbitcoin.adapter.NoTouchViewPager;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerraceFragment extends Fragment {


    @Bind(R.id.terrace_viewpager)
    NoTouchViewPager terraceViewpager;

    private static final String[] CHANNELS = new String[]{"平台", "钱包"};
    @Bind(R.id.indicator)
    MagicIndicator indicator;
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private FragmentContainerHelper mHelper = new FragmentContainerHelper();
    private MyViewPagerAdapter myViewPagerAdapter;

    public TerraceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_terrace, container, false);
        ButterKnife.bind(this, view);
        mHelper.attachMagicIndicator(indicator);
        initFragment();
        return view;
    }


    private void initFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new TerraceChildFragment());
        list.add(new WalletFragment());
        myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(), list);
        terraceViewpager.setAdapter(myViewPagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                        terraceViewpager.setCurrentItem(i);
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
        indicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        /*titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(Utils.getApp(), 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));*/
        final FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(indicator);
        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
        fragmentContainerHelper.setDuration(300);
        terraceViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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

}
