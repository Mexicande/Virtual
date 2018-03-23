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
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.adapter.User_SweetAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.SPUtils;

/**
 * @author apple
 *         User_Sweet
 */
public class UserSweetActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.userSweetRecycler)
    RecyclerView userSweetRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private User_SweetAdapter mUserSweetAdapter;
    private ArrayList<SweetList.CandyBean> mList = new ArrayList<>();
    private String mToken;
    private View notDataView;

    public void goBack(View v) {
        finish();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_user_sweet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString(Contacts.token);
        initView();
        initDate();
        setListener();
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDate();
            }
        });
        mUserSweetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SweetList.CandyBean item = (SweetList.CandyBean) adapter.getItem(position);
                Intent intent = new Intent(UserSweetActivity.this, WebViewActivity.class);
                intent.putExtra("url", item.getLink());
                intent.putExtra("title", item.getName());
                if (item.getLink() != null && !item.getLink().isEmpty()) {
                    startActivity(intent);
                }
            }
        });


    }

    private void initDate() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Contacts.token, mToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_MYSWEET, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }

                Gson gson = new Gson();
                SweetList terraceBean = gson.fromJson(data.toString(), SweetList.class);

                if (terraceBean.getCandy() == null) {
                    mUserSweetAdapter.setEmptyView(notDataView);
                } else {
                    mList.addAll(terraceBean.getCandy());
                    mUserSweetAdapter.setNewData(mList);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);

                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }

                if(code==Contacts.ERROR_CODE){
                    SPUtils.getInstance().clear();
                   /* Intent intent=new Intent(CollectionActivity.this,LoginActivity.class);
                    startActivityForResult(intent,Contacts.REQUESTION_CODE);*/
                    ActivityUtils.startActivity(LoginActivity.class);
                    finish();
                }
            }
        });

    }

    private void initView() {
        mUserSweetAdapter = new User_SweetAdapter(mList);
        userSweetRecycler.setLayoutManager(new LinearLayoutManager(this));
        userSweetRecycler.setAdapter(mUserSweetAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) userSweetRecycler.getParent(), false);

    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_usersweet);
    }
}
