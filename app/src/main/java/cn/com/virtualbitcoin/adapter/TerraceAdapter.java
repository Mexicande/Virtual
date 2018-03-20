package cn.com.virtualbitcoin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.TerraceBean;

/**
 * Created by apple on 2018/3/18.
 */

public class TerraceAdapter extends BaseQuickAdapter<TerraceBean.MarketBean,BaseViewHolder> {
    public TerraceAdapter( List<TerraceBean.MarketBean> data) {
        super(R.layout.terrace_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TerraceBean.MarketBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.num,"NO."+(helper.getAdapterPosition()+1))
                .setText(R.id.website_url,item.getLink())
                .setRating(R.id.rating,Float.parseFloat(item.getStar()))
                .setText(R.id.trade,item.getTrade())
                .setText(R.id.transaction,item.getTransaction())
                .setText(R.id.country,item.getCountry());
    }
}
