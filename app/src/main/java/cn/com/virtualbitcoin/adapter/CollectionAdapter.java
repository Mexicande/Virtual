package cn.com.virtualbitcoin.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.AmountBean;
import cn.com.virtualbitcoin.bean.CollectionBean;
import cn.com.virtualbitcoin.utils.GlideCircleTransform;

/**
 * Created by apple on 2018/3/18.
 */

public class CollectionAdapter extends BaseQuickAdapter<CollectionBean,BaseViewHolder> {
    public CollectionAdapter( List<CollectionBean> data) {
        super(R.layout.collection_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBean item) {
        helper.setText(R.id.en_name,item.getEn_name())
                .setText(R.id.cn_name,item.getCn_name())
                .setText(R.id.tv_star,item.getStar())
        .setRating(R.id.rating, item.getStar()==null? (float) 5.0 :Float.parseFloat(item.getStar()))
        .setText(R.id.date,item.getDatetime());

        Glide.with(mContext).load(item.getLogo())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView)helper.getView(R.id.logo))
        ;
    }
}
