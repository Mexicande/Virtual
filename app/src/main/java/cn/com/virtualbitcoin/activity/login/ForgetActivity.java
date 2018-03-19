package cn.com.virtualbitcoin.activity.login;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;

public class ForgetActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
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
