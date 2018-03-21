package cn.com.virtualbitcoin.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.GetSweetActivity;
import cn.com.virtualbitcoin.activity.PriceActivity;
import cn.com.virtualbitcoin.activity.RateActivity;
import cn.com.virtualbitcoin.adapter.CollectionAdapter;
import cn.com.virtualbitcoin.adapter.MyViewPagerAdapter;
import cn.com.virtualbitcoin.adapter.NoTouchViewPager;
import cn.com.virtualbitcoin.adapter.RateAdapter;
import cn.com.virtualbitcoin.adapter.SweetAdapter;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    BGABanner bannerFrescoDemoContent;
    @Bind(R.id.main_Recycyler)
    RecyclerView mainRecycyler;
    CollectionAdapter mCollectionAdapter;
    private List<String> mDataList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private SweetAdapter sweetAdapter;

    ArrayList<SweetList> mSweetList = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mainRecycyler.setLayoutManager(new LinearLayoutManager(getActivity()));

        initBanner();
        initDate();

        return view;

    }

    private void initBanner() {
        //mCollectionAdapter=new CollectionAdapter(null);
        if(rateAdapter!=null){

        }
        sweetAdapter = new SweetAdapter(R.layout.sweet_item, mSweetList);
        mainRecycyler.setAdapter(sweetAdapter);
        sweetAdapter.addHeaderView(setHeader(),0);
        sweetAdapter.addHeaderView(setFooter(),1);
        sweetAdapter.setNewData(mSweetList);

        sweetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout viewByPosition = (RelativeLayout) sweetAdapter.getViewByPosition(mainRecycyler, position, R.id.layout2);
                RelativeLayout viewByPosition2 = (RelativeLayout) sweetAdapter.getViewByPosition(mainRecycyler, position, R.id.layout1);
                switch (view.getId()) {
                    case R.id.layout1:
                        viewByPosition2.setVisibility(View.GONE);
                        viewByPosition.setVisibility(View.VISIBLE);
                        break;
                    case R.id.layout:
                        viewByPosition2.setVisibility(View.VISIBLE);
                        viewByPosition.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }

            }
        });

    }
    private RateAdapter rateAdapter;
    ArrayList<RateBean> rateBeans = new ArrayList<>();
    private void initBanner2() {
        rateAdapter = new RateAdapter(rateBeans);
        rateAdapter.addHeaderView(setHeader(),0);
        rateAdapter.addHeaderView(setFooter(),1);
        mainRecycyler.setAdapter(rateAdapter);
        rateAdapter.setNewData(rateBeans);
        mainRecycyler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));
    }
    private MagicIndicator magicIndicator;
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    private Fragment mFragment;
    // private NoTouchViewPager viewPager;
    private View setFooter() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.footer_item, null);
        magicIndicator=view.findViewById(R.id.magic_indicator);
       // viewPager=view.findViewById(R.id.view_pager);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);

        initFragment();
        return view;
    }
    private void initDate() {
        SweetList list = new SweetList();
        list.setCn_name("比特币");
        list.setEn_name("GEC");
        list.setPeople_num("已领取300人");
        list.setWay_get(1);
        mSweetList.add(list);
        mSweetList.add(list);
        mSweetList.add(list);
        mSweetList.add(list);
        mSweetList.add(list);


        RateBean rateBean = new RateBean();
        rateBean.setCn_name("比特币");
        rateBean.setEn_name("GEC");
        rateBean.setCollection(1);
        rateBean.setRate_num("4.5");
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);


    }
    private View setHeader() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.header_item, null);
        return view;
    }


    private void initFragment() {
        mDataList.add(getResources().getString(R.string.sweets));
        mDataList.add(getResources().getString(R.string.rate));
        List<Fragment> list = new ArrayList<>();
        mFragments.add(new SweetsFragment());
        mFragments.add(new RateFragment());
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(), list);
      //  viewPager.setAdapter(myViewPagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : 2;
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
                        mFragmentContainerHelper.handlePageSelected(i);
                        ToastUtils.showShort(i+"");
                        if(i==1){
                          initBanner2();
                        }else {
                            initBanner();
                        }
                       // switchPages(i);
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
     /*   viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                fragmentContainerHelper.handlePageSelected(position);
            }
        });
*/
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


}
