package cn.com.virtualbitcoin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.RateAdapter;
import cn.com.virtualbitcoin.adapter.SweetAdapter;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SweetsFragment extends Fragment {


    ArrayList<SweetList> arrayList = new ArrayList<>();
    @Bind(R.id.Rtext)
    TextView Rtext;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private SweetAdapter sweetAdapter;

    public SweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sweets, container, false);
        ButterKnife.bind(this, view);
        initDate();
        initRecycler();
        return view;
    }

    private void initRecycler() {
        sweetAdapter = new SweetAdapter(R.layout.sweet_item, arrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);

        /*recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });*/
        recycler.setAdapter(sweetAdapter);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //initDate();
                ToastUtils.showShort("加载更多");
                sweetAdapter.addData(arrayList);
            }
        });

        sweetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout viewByPosition = (RelativeLayout) sweetAdapter.getViewByPosition(recycler, position, R.id.layout2);
                RelativeLayout viewByPosition2 = (RelativeLayout) sweetAdapter.getViewByPosition(recycler, position, R.id.layout1);
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
    private void initDate() {
        SweetList list = new SweetList();
        list.setCn_name("比特币");
        list.setEn_name("GEC");
        list.setPeople_num("已领取300人");
        list.setWay_get(1);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
