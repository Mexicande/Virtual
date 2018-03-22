package cn.com.virtualbitcoin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import cn.com.virtualbitcoin.activity.WebViewActivity;
import cn.com.virtualbitcoin.adapter.WalletAdapter;
import cn.com.virtualbitcoin.bean.TerraceBean;
import cn.com.virtualbitcoin.bean.Wallet;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author apple
 *         钱包List
 */
public class WalletFragment extends Fragment {


    @Bind(R.id.wallet_recycler)
    RecyclerView walletRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private WalletAdapter mWalletAdapter;
    private ArrayList<Wallet.WalletBean> mList = new ArrayList<>();
    private View notDataView;
    private int page=1;


    public WalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        ButterKnife.bind(this, view);
        initView();
        initDate(1);
        setListener();
        return view;
    }

    private void setListener() {

        mWalletAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Wallet.WalletBean item = mWalletAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(Contacts.WEB_TITLE, item.getName());
                intent.putExtra(Contacts.WEB_URL, item.getLink());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mWalletAdapter = new WalletAdapter(mList);
        walletRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        walletRecycler.setAdapter(mWalletAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) walletRecycler.getParent(), false);
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

    private void initDate(final int page ) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", page);
            jsonObject.put("number", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_WALLETLSIT, getActivity(), jsonObject, new OnRequestDataListener() {
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
                Wallet wallet = gson.fromJson(data.toString(), Wallet.class);
                if(!wallet.getWallet().isEmpty()){
                    mList.addAll(wallet.getWallet());
                    mWalletAdapter.setNewData(mList);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
                if(mList.size()==0){
                    mWalletAdapter.setEmptyView(notDataView);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
