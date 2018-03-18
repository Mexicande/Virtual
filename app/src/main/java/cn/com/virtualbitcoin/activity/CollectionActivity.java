package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.CollectionAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.CollectionBean;

public class CollectionActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.coll_recycler)
    RecyclerView collRecycler;

    private CollectionAdapter mCollectionAdapter;

    private ArrayList<CollectionBean>mArrays=new ArrayList<>();



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
        initDate();
        initView();
    }



    private void initView() {

        mCollectionAdapter=new CollectionAdapter(mArrays);
        collRecycler.setLayoutManager(new LinearLayoutManager(this));
        collRecycler.setAdapter(mCollectionAdapter);
    }

    private void initDate() {

        CollectionBean collectionBean=new CollectionBean();
            collectionBean.setEn_name("GEC");
            collectionBean.setCn_name("比特币");
            collectionBean.setStar("4.0");
            collectionBean.setDatetime("2018/3/15");
        CollectionBean collectionBean1=new CollectionBean();
            collectionBean1.setEn_name("ETH");
            collectionBean1.setCn_name("以太坊");
            collectionBean1.setStar("3.0");
            collectionBean1.setDatetime("2018/3/18");

            mArrays.add(collectionBean);
            mArrays.add(collectionBean1);
            mArrays.add(collectionBean);
            mArrays.add(collectionBean1);
            mArrays.add(collectionBean1);
    }


    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_collection);
    }
}
