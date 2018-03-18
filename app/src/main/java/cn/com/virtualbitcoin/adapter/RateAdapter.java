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

public class RateAdapter extends BaseQuickAdapter<RateBean,BaseViewHolder> {
    public RateAdapter( List<RateBean> data) {
        super(R.layout.rate_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RateBean item) {
        helper.setText(R.id.EN_name,item.getEn_name())
                .setText(R.id.CN_name,item.getCn_name())
                .setText(R.id.tv_rate,item.getRate_num())
                .setRating(R.id.rating,item.getRate_num()!=null? (float) 5.0 :Float.parseFloat(item.getRate_num()))
        .addOnClickListener(R.id.bt_collection);
        setSuper((SuperButton) helper.getView(R.id.bt_collection),item.getCollection());
    }

    private void setSuper(SuperButton view ,int i) {
        if(i==0){
            view.setText("收藏");
        }else {
            view.setText("已收藏");
        }
    }
}
