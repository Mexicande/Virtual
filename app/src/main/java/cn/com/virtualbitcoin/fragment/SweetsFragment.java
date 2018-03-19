package cn.com.virtualbitcoin.fragment;


import android.content.Intent;
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
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.WebViewActivity;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.adapter.SweetAdapter;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SweetsFragment extends Fragment {


    ArrayList<SweetList.CandyBean> mArrayList = new ArrayList<>();
    @Bind(R.id.Rtext)
    TextView Rtext;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    private SweetAdapter sweetAdapter;
    private String mToken;
    private static final int REQUESTION_CODE=2000;
    private static final int RESULT_CODE=100;
    public SweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sweets, container, false);
        ButterKnife.bind(this, view);
        mToken= SPUtils.getInstance().getString(Contacts.token);
        if(mToken.isEmpty()){
            //未登录
            getDate(Api.GET_SWEETLSIT);
        }else {
            //已登录
            getDate(Api.GET_USUERSWEETLSIT);
        }
        initRecycler();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mToken= SPUtils.getInstance().getString(Contacts.token);
    }

    private void initRecycler() {

        sweetAdapter = new SweetAdapter(R.layout.sweet_item, mArrayList);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });*/
        recycler.setAdapter(sweetAdapter);
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);


        sweetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout viewByPosition = (RelativeLayout) sweetAdapter.getViewByPosition(recycler, position, R.id.layout2);
                RelativeLayout viewByPosition2 = (RelativeLayout) sweetAdapter.getViewByPosition(recycler, position, R.id.layout1);
                SweetList.CandyBean item = sweetAdapter.getItem(position);

                switch (view.getId()) {
                    case R.id.layout1:
                        viewByPosition2.setVisibility(View.GONE);
                        viewByPosition.setVisibility(View.VISIBLE);
                        break;
                    case R.id.layout:
                        viewByPosition2.setVisibility(View.VISIBLE);
                        viewByPosition.setVisibility(View.GONE);
                        break;
                    case R.id.tv_sign:
                        mToken= SPUtils.getInstance().getString(Contacts.token);
                        if(mToken.isEmpty()){
                            Intent intent=new Intent(getActivity(), LoginActivity.class);
                            startActivityForResult(intent,REQUESTION_CODE);
                        }else {
                            if(Integer.parseInt(item.getStatus())==0){
                                toGetSweet(item,position);
                            }else {
                                ToastUtils.showShort("已领取");
                            }
                        }
                        break;
                    case R.id.tv_toGet:
                        Intent intent=new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("url",item.getLink());
                        intent.putExtra("title",item.getName());
                        if(item.getLink()!=null&&!item.getLink().isEmpty()){
                            startActivity(intent);
                        }
                        break;
                    default:
                        break;
                }

            }
        });

    }

    /**
     * 标记 sign
     */
    private void toGetSweet(final  SweetList.CandyBean item, final int position) {


       JSONObject object=new JSONObject();
        try {
            object.put("token",mToken);
            object.put("candy_id",item.getId());
            object.put("status",1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api.getReQuest(Api.GET_SWEET, getActivity(), object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                item.setStatus("1");
                mArrayList.set(position,item);
                sweetAdapter.notifyItemChanged(position);

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });

    }

    private void getDate(String url) {

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
                SweetList list = gson.fromJson(data.toString(), SweetList.class);
                sweetAdapter.setNewData(list.getCandy());
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTION_CODE){
            switch (resultCode){
                case RESULT_CODE:
                    mToken= SPUtils.getInstance().getString(Contacts.token);
                    getDate(Api.GET_USUERSWEETLSIT);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
