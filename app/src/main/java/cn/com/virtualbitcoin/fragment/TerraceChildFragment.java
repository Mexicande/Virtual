package cn.com.virtualbitcoin.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.TerraceAdapter;
import cn.com.virtualbitcoin.bean.TerraceBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerraceChildFragment extends Fragment {


    @Bind(R.id.terrace_recycler)
    RecyclerView terraceRecycler;
    private TerraceAdapter mTerraceAdapter;
    private ArrayList<TerraceBean> mList = new ArrayList<>();

    public TerraceChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_terrace_child, container, false);
        ButterKnife.bind(this, view);
        initDate();
        initView();
        return view;
    }


    private void initView() {
        mTerraceAdapter = new TerraceAdapter(null);
        terraceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        terraceRecycler.setAdapter(mTerraceAdapter);
        mTerraceAdapter.addData(mList);
    }

    private void initDate() {
        TerraceBean terraceBean = new TerraceBean();
        terraceBean.setName("BitMEX");
        TerraceBean terraceBean1 = new TerraceBean();
        terraceBean1.setName("OKEX");
        mList.add(terraceBean);
        mList.add(terraceBean1);
        mList.add(terraceBean);
        mList.add(terraceBean1);
        mList.add(terraceBean1);
        mList.add(terraceBean);
        mList.add(terraceBean);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }
}
