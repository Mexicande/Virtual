package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.child.RateChildActivity;
import cn.com.virtualbitcoin.adapter.RateAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

/**
 *  区块链评级
 *
 */
public class RateActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rate_Recycler)
    RecyclerView rateRecycler;
    private RateAdapter rateAdapter;
    ArrayList<RateBean> rateBeans = new ArrayList<>();

    @Override
    public int getLayoutResource() {
        return R.layout.activity_rate;
    }

    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initView();
        setListener();
    }

    private void setListener() {
        rateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SuperButton viewByPosition = (SuperButton) rateAdapter.getViewByPosition(rateRecycler, position, R.id.bt_collection);
                if (rateBeans.get(position).getCollection() == 1) {
                    ToastUtils.showShort("已经收藏过了~~~~");
                } else {
                    ToastUtils.showShort("收藏成功");
                    viewByPosition.setText("已收藏");
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
        rateBean.setEn_name("GEC");
        rateBean.setCollection(1);
        rateBean.setRate_num("4.5");
        RateBean rateBean2 = new RateBean();
        rateBean2.setEn_name("GEC");
        rateBean2.setCollection(0);
        rateBean2.setRate_num("4.5");
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean);
        rateBeans.add(rateBean2);


    }

    private void initView() {
        rateAdapter = new RateAdapter(rateBeans);
        rateRecycler.setLayoutManager(new LinearLayoutManager(this));
        rateRecycler.setAdapter(rateAdapter);
        rateRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));

    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_block_rate);
    }
    /*   @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }*/
}
