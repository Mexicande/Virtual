package cn.com.virtualbitcoin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.bean.TerraceBean;

/**
 * Created by apple on 2018/3/18.
 */

public class TerraceAdapter extends BaseQuickAdapter<TerraceBean,BaseViewHolder> {
    public TerraceAdapter( List<TerraceBean> data) {
        super(R.layout.terrace_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TerraceBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.num,"NO."+(helper.getAdapterPosition()+1));
    }
}
