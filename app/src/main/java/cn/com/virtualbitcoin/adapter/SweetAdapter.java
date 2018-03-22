package cn.com.virtualbitcoin.adapter;

import android.opengl.Visibility;
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

public class SweetAdapter extends BaseQuickAdapter<SweetList.CandyBean,BaseViewHolder> {

    public SweetAdapter(int layout, List<SweetList.CandyBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SweetList.CandyBean item) {
        helper.setText(R.id.EN_name,item.getName())
                .setText(R.id.EN_name1,item.getName())
                .setText(R.id.tv_introduction,item.getIntroduction())
                .setText(R.id.give_rule,item.getGive_rule())
                .setText(R.id.put_rule,item.getPut_rule())
                .setText(R.id.people_num,"已领取"+item.getNumber()+"人")
                .setText(R.id.people_num1,"已领取"+item.getNumber()+"人")
                .setText(R.id.tv_sign,Integer.parseInt(item.getStatus())==0?
                        mContext.getResources().getString(R.string.sign):mContext.getResources().getString(R.string.signed))
                .setVisible(R.id.layout2, item.getType() != null)
                .setVisible(R.id.layout1,item.getType() == null)
                .setImageResource(R.id.iv_get1,Integer.parseInt(item.getStatus())==0?R.mipmap.iv_get_all:R.mipmap.iv_got_all)
                .setImageResource(R.id.iv_get,Integer.parseInt(item.getStatus())==0?R.mipmap.iv_get:R.mipmap.iv_got)
                .addOnClickListener(R.id.layout)
                .addOnClickListener(R.id.layout1)
                .addOnClickListener(R.id.tv_sign)
                .addOnClickListener(R.id.tv_toGet)
                ;


    }
}
