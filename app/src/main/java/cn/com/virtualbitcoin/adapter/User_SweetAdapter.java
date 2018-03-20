package cn.com.virtualbitcoin.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.CollectionBean;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.bean.UserSweetBean;
import cn.com.virtualbitcoin.utils.GlideCircleTransform;

/**
 * Created by apple on 2018/3/18.
 */

public class User_SweetAdapter extends BaseQuickAdapter<SweetList.CandyBean,BaseViewHolder> {
    public User_SweetAdapter(List<SweetList.CandyBean> data) {
        super(R.layout.item_my_sweet, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SweetList.CandyBean item) {
        helper.setText(R.id.en_name,item.getName())
                .setImageResource(R.id.get,R.mipmap.iv_got_all);
     /*   Glide.with(mContext).load(item.ge)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(mContext))
                .into((ImageView)helper.getView(R.id.logo));*/
    }
}
