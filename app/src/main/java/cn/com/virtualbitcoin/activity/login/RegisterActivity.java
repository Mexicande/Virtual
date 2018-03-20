package cn.com.virtualbitcoin.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

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
    }
}
