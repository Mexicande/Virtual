package cn.com.virtualbitcoin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.child.RateChildActivity;
import cn.com.virtualbitcoin.adapter.RateAdapter;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment {


    @Bind(R.id.Rtext)
    TextView Rtext;
    @Bind(R.id.rate_Recycler)
    RecyclerView rateRecycler;
    private RateAdapter rateAdapter;
    ArrayList<RateBean> rateBeans = new ArrayList<>();

    public RateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate, container, false);
        ButterKnife.bind(this, view);
        initDate();
        initView();
        setListener();
        return view;
    }

    private void setListener() {
        rateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("收藏");
                SuperButton viewByPosition = (SuperButton) rateAdapter.getViewByPosition(rateRecycler, position, R.id.bt_collection);
                if (rateBeans.get(position).getCollection() == 1) {
                    ToastUtils.showShort("收藏");
                    viewByPosition.setText("已收藏");
                } else {
                    ToastUtils.showShort("已经收藏过了~~~~");
                }
            }
        });

        rateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(RateChildActivity.class);
            }
        });
    }


    private void initDate() {
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

    private void initView() {
        rateAdapter = new RateAdapter(rateBeans);
        rateRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        rateRecycler.setAdapter(rateAdapter);
        rateRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));
        rateRecycler.setHasFixedSize(true);
        rateRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
