package cn.com.virtualbitcoin.activity.login;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.virtualbitcoin.MainActivity;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.StatusBarUtil;
import cn.com.virtualbitcoin.view.editext.PowerfulEditText;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.logo)
    ImageView logo;
    @Bind(R.id.et_name)
    PowerfulEditText etName;
    @Bind(R.id.et_PassWord)
    PowerfulEditText etPassWord;
    @Bind(R.id.super_Button)
    SuperButton superButton;
    @Bind(R.id.tv_forget)
    TextView tvForget;
    @Bind(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    private void setListener() {
        etName.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    if(s.length()==11&&etPassWord.getText().toString().length()>=6){
                        //可点击
                    }else {
                        //不可点击
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassWord.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()>=6&&etName.getText().toString().length()==11){
                    //可点击
                }else {
                    //不可点击
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.iv_close, R.id.super_Button, R.id.tv_forget, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.super_Button:
                break;
            case R.id.tv_forget:
                ActivityUtils.startActivity(ForgetActivity.class);
                break;
            case R.id.tv_register:
                ActivityUtils.startActivity(RegisterActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_login);
    }
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));

    }
}
