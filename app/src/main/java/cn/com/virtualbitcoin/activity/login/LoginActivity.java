package cn.com.virtualbitcoin.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.virtualbitcoin.MainActivity;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.EncryptUtils;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.StatusBarUtil;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.view.editext.PowerfulEditText;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

/**
 * 账号密码登录
 */
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
    private static  final  int RESULT_CODE=100;
    private static  final  int REQUESTION_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    private void setListener() {
        etName.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()==11&&etPassWord.getText().toString().length()>=6){
                    //可点击
                    superButton.setEnabled(true);
                    superButton.setUseShape();
                }else {
                    //不可点击
                    superButton.setEnabled(false);
                    superButton.setUseShape();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassWord.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()>=6&&etName.getText().toString().length()==11){
                    //可点击
                    superButton.setEnabled(true);
                    superButton.setUseShape();

                }else {
                    superButton.setEnabled(false);
                    superButton.setUseShape();

                    //不可点击
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

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
                String from = getIntent().getStringExtra("from");
                if("user".equals(from)){
                    ActivityUtils.startActivity(MainActivity.class);
                    finish();
                }else {
                    finish();
                }
                break;
            case R.id.super_Button:
                setLogin();
                break;
            case R.id.tv_forget:
                Intent forgetIntent=new Intent(this,ForgetActivity.class);
                startActivityForResult(forgetIntent,REQUESTION_CODE);
                break;
            case R.id.tv_register:
                Intent registerIntent=new Intent(this,RegisterActivity.class);
                startActivityForResult(registerIntent,REQUESTION_CODE);
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void setLogin() {

        JSONObject jsonObject=new JSONObject();
        String phone = etName.getText().toString();
        String pw = etPassWord.getText().toString();
        String password = EncryptUtils.encryptMD5ToString(pw);

        try {
            jsonObject.put("phone",phone);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api.getReQuest(Api.GET_LOGIN, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                try {
                    String token = data.getString(Contacts.token);
                    String phone = data.getString("phone");
                    SPUtils.getInstance().put(Contacts.token,token);
                    SPUtils.getInstance().put(Contacts.phone,phone);
                    setResult(RESULT_CODE);
                    finish();
                } catch (JSONException e) {

                }

            }


            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);

            }
        });
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_login);
    }
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            String from = getIntent().getStringExtra("from");
            if("user".equals(from)){
                ActivityUtils.startActivity(MainActivity.class);
                finish();
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTION_CODE){
            if(resultCode==RESULT_CODE){
                String stringExtra = data.getStringExtra(Contacts.phone);
                    if(stringExtra!=null){
                        etName.setText(stringExtra);
                    }
            }
        }
    }
}
