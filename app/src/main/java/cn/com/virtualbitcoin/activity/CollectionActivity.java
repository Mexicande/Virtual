package cn.com.virtualbitcoin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.child.RateChildActivity;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.adapter.CollectionAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.CollectionBean;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;

public class CollectionActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.coll_recycler)
    RecyclerView collRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private View notDataView;
    private String mToken;

    private CollectionAdapter mCollectionAdapter;

    private ArrayList<RateBean.GradeBean> mArrays = new ArrayList<>();

    public void goBack(View v) {
        finish();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_collection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString(Contacts.token);
        initDate();
        initView();
    }


    private void initView() {

        mCollectionAdapter = new CollectionAdapter(mArrays);
        collRecycler.setLayoutManager(new LinearLayoutManager(this));
        collRecycler.setAdapter(mCollectionAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) collRecycler.getParent(), false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDate();
            }
        });
        mCollectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(CollectionActivity.this,RateChildActivity.class);
                intent.putExtra("Grade",mCollectionAdapter.getItem(position));
                startActivity(intent);
            }
        });

    }

    private void initDate() {
        JSONObject object = new JSONObject();
        try {
            object.put("token", mToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_MY_COLLECTION, this, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }

                Gson gson = new Gson();
                RateBean rateBean = gson.fromJson(data.toString(), RateBean.class);
                if(rateBean.getGrade()==null){
                    mCollectionAdapter.setEmptyView(notDataView);
                }else {
                    mArrays.clear();
                    mArrays.addAll(rateBean.getGrade());
                    mCollectionAdapter.setNewData(rateBean.getGrade());
                }

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }

                if(code==Contacts.ERROR_CODE){
                    SPUtils.getInstance().clear();
                   /* Intent intent=new Intent(CollectionActivity.this,LoginActivity.class);
                    startActivityForResult(intent,Contacts.REQUESTION_CODE);*/
                    ActivityUtils.startActivity(LoginActivity.class);
                    finish();
                }else {
                    mCollectionAdapter.setEmptyView(notDataView);

                }

            }
        });

    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_collection);
    }
}
