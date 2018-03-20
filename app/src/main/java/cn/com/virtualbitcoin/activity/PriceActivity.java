package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.AmountAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.AmountBean;
import cn.com.virtualbitcoin.bean.TerraceBean;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;

public class PriceActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.amountRecycler)
    RecyclerView amountRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private AmountAdapter mAmountAdapter;
    private View notDataView;
    private ArrayList<AmountBean.CoinMarketBean> mList = new ArrayList<>();
    private int page=1;
    public void goBack(View v) {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        refreshLayout.autoRefresh();

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_price;
    }


    private void initDate(final int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", page);
            jsonObject.put("number", 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_PRICE, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if (page == 1) {
                    mList.clear();
                }
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore();
                }
                Gson gson = new Gson();
                AmountBean terraceBean = gson.fromJson(data.toString(), AmountBean.class);
                if(!terraceBean.getCoinMarket().isEmpty()){
                    mList.addAll(terraceBean.getCoinMarket());
                    mAmountAdapter.setNewData(mList);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
                if(mList.size()==0){
                    mAmountAdapter.setEmptyView(notDataView);
                }
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore();
                }
            }
        });

    }

    private void initView() {
        mAmountAdapter = new AmountAdapter(mList);
        amountRecycler.setLayoutManager(new LinearLayoutManager(this));
        amountRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));
        amountRecycler.setAdapter(mAmountAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) amountRecycler.getParent(), false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDate(1);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                initDate(page);
                page++;
            }
        });
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_price);
    }
}
