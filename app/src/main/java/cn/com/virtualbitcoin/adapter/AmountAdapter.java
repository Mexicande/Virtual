package cn.com.virtualbitcoin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.AmountBean;
import cn.com.virtualbitcoin.bean.RateBean;

/**
 * Created by apple on 2018/3/18.
 */

public class AmountAdapter extends BaseQuickAdapter<AmountBean,BaseViewHolder> {
    public AmountAdapter( List<AmountBean> data) {
        super(R.layout.amount_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AmountBean item) {
        helper.setText(R.id.EN_name,item.getName())
                .setText(R.id.CN_name,item.getWalton())
                .setText(R.id.tv_price,item.getPrice())
                .setText(R.id.bt_range,item.getRange())
                .setText(R.id.num,String.valueOf(helper.getAdapterPosition()+1))
                ;

    }
}
