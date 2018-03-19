package cn.com.virtualbitcoin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.child.RateChildActivity;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.adapter.RateAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

/**
 * 区块链评级
 */
public class RateActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rate_Recycler)
    RecyclerView rateRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private RateAdapter rateAdapter;
    ArrayList<RateBean.GradeBean> mRateBeans = new ArrayList<>();
    private String mToken;
    private View notDataView;
    private static final int REQUESTION_CODE=2000;
    private static final int RESULT_CODE=100;
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
        mToken = SPUtils.getInstance().getString(Contacts.token);
        initView();
        mRefreshLayout.autoRefresh();
        setListener();
    }

    private void setListener() {
        rateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SuperButton viewByPosition = (SuperButton) rateAdapter.getViewByPosition(rateRecycler, position, R.id.bt_collection);
                RateBean.GradeBean item = rateAdapter.getItem(position);
                if(mToken.isEmpty()){
                    Intent intent=new Intent(RateActivity.this, LoginActivity.class);
                    startActivityForResult(intent,REQUESTION_CODE);
                }else {
                    if ("0".equals(item.getStatus())) {
                        viewByPosition.setText("已收藏");
                    } else {
                        ToastUtils.showShort("已经收藏过了~~~~");
                    }
                }

            }
        });
        rateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(RateActivity.this,RateChildActivity.class);
                intent.putExtra("Grade",rateAdapter.getItem(position));
                startActivity(intent);            }
        });
    }

    private void initDate(String url, final int limit_begin) {

        JSONObject object = new JSONObject();
        try {
            object.put("token", mToken);
            object.put("page", limit_begin);
            object.put("number", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(url, this, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if (limit_begin == 0) {
                    mRateBeans.clear();
                }
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
                if (mRefreshLayout.isLoading()) {
                    mRefreshLayout.finishLoadmore();
                }
                Gson gson = new Gson();
                RateBean rateBean = gson.fromJson(data.toString(), RateBean.class);
                mRateBeans.addAll(rateBean.getGrade());
                rateAdapter.setNewData(mRateBeans);
                if(limit_begin==0&&mRateBeans.size()==0){
                    rateAdapter.setEmptyView(notDataView);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                if(mRateBeans.size()==0){
                    rateAdapter.setEmptyView(notDataView);
                }
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
                if (mRefreshLayout.isLoading()) {
                    mRefreshLayout.finishLoadmore();
                }
            }
        });

    }

    private void initView() {
        rateAdapter = new RateAdapter(mRateBeans);
        rateRecycler.setLayoutManager(new LinearLayoutManager(this));
        rateRecycler.setAdapter(rateAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rateRecycler.getParent(), false);

        rateRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (mToken.isEmpty()) {
                    initDate(Api.GET_GRADELSIT,0);
                } else {
                    initDate(Api.GET_USERGRADELSIT,0);
                }

            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (mToken.isEmpty()) {
                    initDate(Api.GET_GRADELSIT,mRateBeans.size());
                } else {
                    initDate(Api.GET_USERGRADELSIT,mRateBeans.size());
                }

            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTION_CODE){
            switch (resultCode){
                case RESULT_CODE:
                    mToken= SPUtils.getInstance().getString(Contacts.token);
                    initDate(Api.GET_USERGRADELSIT,0);
                    break;
                default:
                    break;
            }
        }
    }

    /*   @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }*/
}
