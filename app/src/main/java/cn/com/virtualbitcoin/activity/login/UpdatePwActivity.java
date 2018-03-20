package cn.com.virtualbitcoin.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.EncryptUtils;
import cn.com.virtualbitcoin.utils.RegexUtils;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.view.editext.PowerfulEditText;

public class UpdatePwActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_name)
    PowerfulEditText etName;
    @Bind(R.id.et_oldPW)
    PowerfulEditText etOldPW;
    @Bind(R.id.et_PassWord)
    PowerfulEditText etPassWord;
    @Bind(R.id.et_cofimPassWord)
    PowerfulEditText etCofimPassWord;
    private String mToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken= SPUtils.getInstance().getString("token");
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_update_pw;
    }

    @OnClick({R.id.iv_close, R.id.super_Button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
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
        String pd = etPassWord.getText().toString();
        String oldPd = etOldPW.getText().toString();
        String confimPd = etCofimPassWord.getText().toString();

        if(!TextUtils.isEmpty(etPhone)&& RegexUtils.isMobileSimple(etPhone)){
            if(!TextUtils.isEmpty(oldPd)){
                if(!TextUtils.isEmpty(pd)){
                    if(!TextUtils.isEmpty(confimPd)){
                            if(pd.equals(confimPd)){
                                String pho = EncryptUtils.encryptMD5ToString(pd);
                                String conPho = EncryptUtils.encryptMD5ToString(confimPd);
                                String old = EncryptUtils.encryptMD5ToString(oldPd);
                                setRegister(etPhone,pho,conPho,old);

                            }else {
                                ToastUtils.showShort("两次密码不一致");
                            }
                    }else {
                        etCofimPassWord.setFocusable(true);
                        ToastUtils.showShort("请输入密码");
                    }
                }else {
                    etPassWord.setFocusable(true);
                    ToastUtils.showShort("请输入密码");
                }
            }else {
                etOldPW.setFocusable(true);
                ToastUtils.showShort("请输入旧密码");
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
            jsonObject.put(Contacts.token,mToken);
            jsonObject.put("newPassword",password);
            jsonObject.put("repNewPassword",repassword);
            jsonObject.put("password",code);
        } catch (JSONException e) {

        }

        Api.getReQuest(Api.GET_UPDATAPW, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                ToastUtils.showShort("修改成功");
                SPUtils.getInstance().clear();
                ActivityUtils.startActivity(LoginActivity.class);
                finish();
            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });


    }
    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.update_pw);
    }
}
