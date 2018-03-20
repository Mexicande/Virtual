package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.TerraceBean;

public class TerraceDescActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       TerraceBean.MarketBean marketBean= (TerraceBean.MarketBean) getIntent().getSerializableExtra("terrace");

       tvTitle.setText(marketBean.getName());

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_terrace_desc;
    }
}
