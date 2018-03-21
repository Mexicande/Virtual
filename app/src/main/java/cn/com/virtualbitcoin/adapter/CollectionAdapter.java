package cn.com.virtualbitcoin.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.AmountBean;
import cn.com.virtualbitcoin.bean.CollectionBean;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.utils.GlideCircleTransform;

/**
 * Created by apple on 2018/3/18.
 */

public class CollectionAdapter extends BaseQuickAdapter<RateBean.GradeBean,BaseViewHolder> {
    public CollectionAdapter( List<RateBean.GradeBean> data) {
        super(R.layout.collection_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RateBean.GradeBean item) {
        helper.setText(R.id.en_name,item.getName())
                .setText(R.id.tv_star,item.getGade())
        .setRating(R.id.rating, item.getGade()==null? (float) 5.0 :Float.parseFloat(item.getGade()))
        .setText(R.id.date,item.getUpdated_at());

        Glide.with(mContext).load(item.getLogo())
                .error(R.mipmap.logo)
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView)helper.getView(R.id.logo))
        ;
    }
}
