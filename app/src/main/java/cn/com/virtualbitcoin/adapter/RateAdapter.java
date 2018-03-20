package cn.com.virtualbitcoin.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by apple on 2018/3/17.
 */

public class RateAdapter extends BaseQuickAdapter<RateBean.GradeBean,BaseViewHolder> {
    public RateAdapter( List<RateBean.GradeBean> data) {
        super(R.layout.rate_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RateBean.GradeBean item) {
        helper.setText(R.id.EN_name,item.getName())
                .setText(R.id.tv_rate,item.getGade())
                .setRating(R.id.rating,item.getGade()==null? (float) 5.0 :Float.parseFloat(item.getGade()))
                .setText(R.id.bt_collection,("0").equals(item.getStatus())?"收藏":"已收藏")
                .addOnClickListener(R.id.bt_collection);
    }

  /*  private void setSuper(SuperButton view ,int i) {
        if(i==0){
            view.setText("收藏");
        }else {
            view.setText("已收藏");
        }
    }*/
}
