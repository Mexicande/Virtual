package cn.com.virtualbitcoin.activity.child;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.utils.AppUtils;

public class RateChildActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;



    public void goBack(View v) {
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_child);
        ButterKnife.bind(this);
        tvTitle.setText(R.string.app_name);
    }

    @Override
    protected void setToolbarTitle() {
        setTitle(null , R.string.app_name);
    }

}
