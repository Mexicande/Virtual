package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.SweetAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * @author apple
 */
public class GetSweetActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.Rtext)
    TextView Rtext;
    @Bind(R.id.sweet_recycler)
    RecyclerView mSweetRecycler;
    private SweetAdapter mSweetAdapter;
    ArrayList<SweetList> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initView();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_get_sweet;
    }

    public void goBack(View v) {
        finish();
    }

    private void initDate() {
        tvTitle.setText(R.string.sweet_title);
        SweetList list = new SweetList();
        list.setCn_name("比特币");
        list.setEn_name("GEC");
        list.setPeople_num("已领取300人");
        list.setWay_get(1);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);
        arrayList.add(list);

    }

    private void initView() {
        mSweetAdapter = new SweetAdapter(R.layout.get_sweet_item, arrayList);
        mSweetRecycler.setLayoutManager(new LinearLayoutManager(this));
        mSweetRecycler.setAdapter(mSweetAdapter);
        mSweetRecycler.addItemDecoration(new DividerItemDecoration(Utils.getApp(), DividerItemDecoration.VERTICAL));

        mSweetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout viewByPosition = (RelativeLayout) mSweetAdapter.getViewByPosition(mSweetRecycler, position, R.id.layout2);
                RelativeLayout viewByPosition2 = (RelativeLayout) mSweetAdapter.getViewByPosition(mSweetRecycler, position, R.id.layout1);
                switch (view.getId()) {
                    case R.id.layout1:
                        viewByPosition2.setVisibility(View.GONE);
                        viewByPosition.setVisibility(View.VISIBLE);
                        break;
                    case R.id.layout:
                        viewByPosition2.setVisibility(View.VISIBLE);
                        viewByPosition.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }

            }
        });
    }
    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.sweet_title);
    }

/*    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }*/
}
