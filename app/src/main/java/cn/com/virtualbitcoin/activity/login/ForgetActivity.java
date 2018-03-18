package cn.com.virtualbitcoin.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;

public class ForgetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_forget;
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_pw);
    }
}
