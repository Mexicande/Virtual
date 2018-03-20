package cn.com.virtualbitcoin.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.AmountBean;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

/**
 * Created by apple on 2018/3/18.
 */

public class AmountAdapter extends BaseQuickAdapter<AmountBean.CoinMarketBean,BaseViewHolder> {
    public AmountAdapter( List<AmountBean.CoinMarketBean> data) {
        super(R.layout.amount_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AmountBean.CoinMarketBean item) {
        helper.setText(R.id.EN_name,item.getSymbol())
                .setText(R.id.CN_name,item.getName())
                .setText(R.id.tv_price,item.getPrice())
                .setText(R.id.num,String.valueOf(helper.getAdapterPosition()+1))
                ;
        setSuperButton((SuperButton) helper.getView(R.id.bt_range),item.getPercent_change_24h());
       /*  SuperButton button=(SuperButton) helper.getView(R.id.bt_range);
         button.setShapeSolidColor(Float.parseFloat(item.getPercent_change_24h()) >= 0 ? R.color.green_amount
                : R.color.red_range);
         button.setUseShape();
         button.setText(item.getPercent_change_24h());*/

    }

    private void setSuperButton(SuperButton button,String range) {
        button.setText(range+"%");
        button.setShapeSolidColor(Float.parseFloat(range) >= 0 ? mContext.getResources().getColor(R.color.green_amount)
                : mContext.getResources().getColor(R.color.red_range));
        button.setUseShape();

    }

}
