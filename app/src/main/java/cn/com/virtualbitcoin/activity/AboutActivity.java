package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.utils.AppUtils;

public class AboutActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        String appVersionName = AppUtils.getAppVersionName();
        tvVersion.setText("V"+appVersionName);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_about;
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_about);
    }
}
