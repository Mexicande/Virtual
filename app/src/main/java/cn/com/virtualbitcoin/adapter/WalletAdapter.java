package cn.com.virtualbitcoin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.TerraceBean;
import cn.com.virtualbitcoin.bean.Wallet;

/**
 * Created by apple on 2018/3/18.
 */

public class WalletAdapter extends BaseQuickAdapter<Wallet.WalletBean,BaseViewHolder> {

    public WalletAdapter(List<Wallet.WalletBean> data) {
        super(R.layout.wallet_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wallet.WalletBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.num,"NO."+(helper.getAdapterPosition()+1))
                .setText(R.id.website_url,item.getLink())
                .setText(R.id.currency,item.getCurrency())
                ;
    }
}
