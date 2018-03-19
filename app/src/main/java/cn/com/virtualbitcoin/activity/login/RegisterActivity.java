package cn.com.virtualbitcoin.activity.login;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_login);
    }
}
