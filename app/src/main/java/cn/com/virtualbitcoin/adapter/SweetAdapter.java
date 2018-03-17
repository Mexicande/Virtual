package cn.com.virtualbitcoin.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.SweetList;

/**
 * Created by apple on 2018/3/17.
 */

public class SweetAdapter extends BaseQuickAdapter<SweetList,BaseViewHolder> {

    public SweetAdapter(int layout, List<SweetList> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SweetList item) {
        helper.setText(R.id.EN_name,item.getEn_name())
                .setText(R.id.CN_name,item.getCn_name())
                .setText(R.id.people_num,item.getPeople_num())
                .addOnClickListener(R.id.layout)
                .addOnClickListener(R.id.layout1)
                ;


    }
}
