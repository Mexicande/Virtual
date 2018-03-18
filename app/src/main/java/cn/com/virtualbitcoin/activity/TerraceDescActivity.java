package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;

public class TerraceDescActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_terrace_desc;
    }
}
