package cn.com.virtualbitcoin.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.EncryptUtils;
import cn.com.virtualbitcoin.utils.RegexUtils;
import cn.com.virtualbitcoin.utils.StatusBarUtil;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.view.CaptchaTimeCount;
import cn.com.virtualbitcoin.view.editext.PowerfulEditText;

public class ForgetActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_name)
    PowerfulEditText etName;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.et_PassWord)
    PowerfulEditText etPassWord;
    @Bind(R.id.et_cofimPassWord)
    PowerfulEditText etCofimPassWord;
    @Bind(R.id.bt_getCode)
    TextView btGetCode;
    private CaptchaTimeCount captchaTimeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        captchaTimeCount = new CaptchaTimeCount(Contacts.MILLIS_IN_TOTAL, Contacts.COUNT_DOWN_INTERVAL, btGetCode, this);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_forget;
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_pw);
    }


    @OnClick({R.id.iv_close, R.id.bt_getCode, R.id.super_Button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.bt_getCode:
                String phone = etName.getText().toString();
                if (etName.getText() != null && phone.length() == 11 && RegexUtils.isMobileSimple(phone)) {
                    getCodeMessage(phone);
                } else {
                    ToastUtils.showShort("请填入正确手机号码");
                }
                break;
            case R.id.super_Button:
                setVerification();

                break;
            default:
                break;
        }
    }
    /**
     * 效验
     *
     */
    private void setVerification() {
        String etPhone = etName.getText().toString();
        String code = etCode.getText().toString();
        String pd = etPassWord.getText().toString();
        String confimPd = etCofimPassWord.getText().toString();


        if(!TextUtils.isEmpty(etPhone)&&RegexUtils.isMobileSimple(etPhone)){
            if(!TextUtils.isEmpty(code)){
                if(!TextUtils.isEmpty(pd)){
                    if(!TextUtils.isEmpty(confimPd)){
                            if(pd.equals(confimPd)){
                                String pho = EncryptUtils.encryptMD5ToString(pd);
                                String conPho = EncryptUtils.encryptMD5ToString(confimPd);
                                setRegister(etPhone,pho,conPho,code);

                            }else {
                                ToastUtils.showShort("两次密码不一致");
                            }
                    }else {
                        etCofimPassWord.setFocusable(true);
                        ToastUtils.showShort("密码不能为空");
                    }
                }else {
                    etPassWord.setFocusable(true);
                    ToastUtils.showShort("密码不能为空");
                }
            }else {
                etCode.setFocusable(true);
                ToastUtils.showShort("密码不能为空");
            }
        }else{
            etName.setFocusable(true);
            ToastUtils.showShort("手机号码格式错误");
        }
    }

    private void setRegister(final String phone,String password,String repassword,String code ) {
        JSONObject jsonObject=new JSONObject();


        try {
            jsonObject.put("phone",phone);
            jsonObject.put("password",password);
            jsonObject.put("repassword",repassword);
            jsonObject.put("code",code);
        } catch (JSONException e) {

        }

        Api.getReQuest(Api.GET_FORGET, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                ToastUtils.showShort("修改成功");
                Intent intent=new Intent();
                intent.putExtra(Contacts.phone,phone);
                setResult(Contacts.RESULT_CODE,intent);
                finish();
            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });


    }

    /**
     * 验证码获取
     */
    private void getCodeMessage(final String phone) {
        captchaTimeCount.start();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Contacts.phone, phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.SEED_FORGET_MESS, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                ToastUtils.showShort("发送成功");

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });
    }
    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));

    }
}
