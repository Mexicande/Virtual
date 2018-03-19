package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.Bind;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.AmountAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.AmountBean;
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
    private ArrayList<AmountBean> mList = new ArrayList<>();

    public void goBack(View v) {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initView();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_price;
    }

    private void initDate() {
        AmountBean amountBean = new AmountBean();
        amountBean.setName("WTC");
        amountBean.setWalton("walton");
        amountBean.setPrice("￥98.43");
        amountBean.setRange("-43.12%");
        mList.add(amountBean);
        mList.add(amountBean);
        mList.add(amountBean);
        mList.add(amountBean);
        mList.add(amountBean);
        mList.add(amountBean);
    }

    private void initView() {
        mAmountAdapter = new AmountAdapter(mList);
        amountRecycler.setLayoutManager(new LinearLayoutManager(this));
        amountRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));
        amountRecycler.setAdapter(mAmountAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) amountRecycler.getParent(), false);

    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_price);
    }
}
