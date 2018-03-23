package cn.com.virtualbitcoin.fragment;


import android.content.Intent;
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
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.child.RateChildActivity;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.adapter.RateAdapter;
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
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment {


    @Bind(R.id.Rtext)
    TextView Rtext;
    @Bind(R.id.rate_Recycler)
    RecyclerView rateRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private RateAdapter rateAdapter;
    ArrayList<RateBean.GradeBean> rateBeansList = new ArrayList<>();
    private String mToken;
    private int page=1;

    public RateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate, container, false);
        ButterKnife.bind(this, view);
        mToken = SPUtils.getInstance().getString(Contacts.token);
        if (mToken.isEmpty()) {
            initDate(1,Api.GET_GRADELSIT);
        } else {
            initDate(1,Api.GET_USERGRADELSIT);
        }
        initView();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mToken = SPUtils.getInstance().getString(Contacts.token);
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if(mToken.isEmpty()){
                    initDate(1,Api.GET_GRADELSIT);
                }else {
                    initDate(1,Api.GET_USERGRADELSIT);
                }
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(mToken.isEmpty()){
                    initDate(1,Api.GET_GRADELSIT);
                }else {
                    initDate(1,Api.GET_USERGRADELSIT);
                }
                page++;
            }
        });

        rateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SuperButton viewByPosition = (SuperButton) rateAdapter.getViewByPosition(rateRecycler, position, R.id.bt_collection);
                RateBean.GradeBean item = rateAdapter.getItem(position);
                if (mToken.isEmpty()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, Contacts.REQUESTION_CODE);
                } else {
                    addCollection(position,item,viewByPosition);
                }
            }
        });

        rateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), RateChildActivity.class);
                intent.putExtra("Grade", rateAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    /**
     * 添加、取消收藏
     *
     * @param item  状态
     */
    private void addCollection(final int position, final RateBean.GradeBean item, final SuperButton viewByPosition) {



        JSONObject object = new JSONObject();
        try {
            object.put("token", mToken);
            object.put("grade_id", item.getId());
            if ("0".equals(item.getStatus())) {
                object.put("status", 1);
            } else {
                object.put("status", 2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_ADDCOLLECTION, getActivity(), object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if("0".equals(item.getStatus())){
                    viewByPosition.setText("已收藏");
                    ToastUtils.showShort("收藏成功");
                    item.setStatus("1");
                }else {
                    viewByPosition.setText("收藏");
                    ToastUtils.showShort("取消收藏");
                    item.setStatus("0");

                }
                rateBeansList.set(position, item);
                rateAdapter.notifyItemChanged(position);

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
                if(code==Contacts.ERROR_CODE){
                    SPUtils.getInstance().clear();
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,Contacts.REQUESTION_CODE);
                    /*ActivityUtils.startActivity(LoginActivity.class);
                    finish();*/
                }
            }
        });

    }


    private void initDate(final int page, String url) {

        JSONObject object = new JSONObject();
        try {
            object.put("token", mToken);
            object.put("page", page);
            object.put("number", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(url, getActivity(), object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if (page == 1) {
                    rateBeansList.clear();
                }
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore();
                }
                Gson gson = new Gson();
                RateBean rateBean = gson.fromJson(data.toString(), RateBean.class);
                if(!rateBean.getGrade().isEmpty()){
                    rateBeansList.addAll(rateBean.getGrade());
                    rateAdapter.setNewData(rateBeansList);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore();
                }
                if(code==Contacts.ERROR_CODE){
                    initDate(1,Api.GET_SWEETLSIT);


/*
                    SPUtils.getInstance().clear();
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,Contacts.REQUESTION_CODE);*/
                }
            }
        });

    }

    private void initView() {
        rateAdapter = new RateAdapter(rateBeansList);
        rateRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        rateRecycler.setAdapter(rateAdapter);
        rateRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Contacts.REQUESTION_CODE) {
            switch (resultCode) {
                case Contacts.RESULT_CODE:
                    mToken = SPUtils.getInstance().getString(Contacts.token);
                    initDate(1,Api.GET_USERGRADELSIT);
                    break;
                default:
                    break;
            }
        }
    }
}
