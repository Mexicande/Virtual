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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.RateActivity;
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
    private RateAdapter rateAdapter;
    ArrayList<RateBean.GradeBean> rateBeans = new ArrayList<>();
    private String mToken;
    private static final int REQUESTION_CODE=2000;
    private static final int RESULT_CODE=100;
    public RateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate, container, false);
        ButterKnife.bind(this, view);
        mToken= SPUtils.getInstance().getString(Contacts.token);
        if(mToken.isEmpty()){
            initDate(Api.GET_GRADELSIT);
        }else {
            initDate(Api.GET_USERGRADELSIT);
        }
        initView();
        setListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mToken= SPUtils.getInstance().getString(Contacts.token);
    }

    private void setListener() {
        rateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SuperButton viewByPosition = (SuperButton) rateAdapter.getViewByPosition(rateRecycler, position, R.id.bt_collection);
                RateBean.GradeBean item = rateAdapter.getItem(position);
                if(mToken.isEmpty()){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
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
                Intent intent=new Intent(getActivity(),RateChildActivity.class);
                intent.putExtra("Grade",rateAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }


    private void initDate(String url) {

        JSONObject object=new JSONObject();
        try {
            object.put("token",mToken);
            object.put("page",1);
            object.put("number",10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(url, getActivity(), object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                Gson gson=new Gson();
                RateBean rateBean = gson.fromJson(data.toString(), RateBean.class);
                rateAdapter.setNewData(rateBean.getGrade());
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTION_CODE){
            switch (resultCode){
                case RESULT_CODE:
                    mToken= SPUtils.getInstance().getString(Contacts.token);
                    initDate(Api.GET_USERGRADELSIT);
                    break;
                default:
                    break;
            }
        }
    }
}
