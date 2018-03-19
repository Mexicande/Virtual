package cn.com.virtualbitcoin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.CollectionActivity;
import cn.com.virtualbitcoin.activity.UserSweetActivity;
import cn.com.virtualbitcoin.activity.login.ForgetActivity;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.activity.login.RegisterActivity;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.AppUtils;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.view.supertextview.SuperButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends Fragment {


    @Bind(R.id.layout_tourist)
    RelativeLayout layoutTourist;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.layout_logined)
    LinearLayout layoutLogined;
    @Bind(R.id.super_quit)
    SuperButton superQuit;
    private String token;

    public CenterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        token = SPUtils.getInstance().getString(Contacts.token);
        String phone = SPUtils.getInstance().getString(Contacts.phone);
        if (!token.isEmpty()) {
            layoutTourist.setVisibility(View.GONE);
            layoutLogined.setVisibility(View.VISIBLE);
            userName.setText(phone);
            superQuit.setVisibility(View.VISIBLE);
        } else {
            if (layoutLogined.getVisibility() == View.VISIBLE) {
                layoutLogined.setVisibility(View.GONE);
            }
            if (layoutTourist.getVisibility() == View.GONE) {
                layoutTourist.setVisibility(View.VISIBLE);
            }
            superQuit.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.my_collection, R.id.my_sweet, R.id.chang_pw, R.id.join_qq, R.id.join_wechat,
            R.id.about, R.id.update, R.id.login, R.id.register, R.id.super_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_collection:
                if (token.isEmpty()) {
                    ActivityUtils.startActivity(LoginActivity.class);
                } else {
                    ActivityUtils.startActivity(CollectionActivity.class);
                }
                break;
            case R.id.my_sweet:
                if (token.isEmpty()) {
                    ActivityUtils.startActivity(LoginActivity.class);
                } else {
                    ActivityUtils.startActivity(UserSweetActivity.class);
                }
                break;
            case R.id.chang_pw:
                if (token.isEmpty()) {
                    ActivityUtils.startActivity(LoginActivity.class);
                    ToastUtils.showShort("未登录");
                } else {
                    Intent intentPw = new Intent(getActivity(), ForgetActivity.class);
                    intentPw.putExtra("from", "user");
                    startActivity(intentPw);
                }
                break;
            case R.id.join_qq:
                break;
            case R.id.join_wechat:
                break;
            case R.id.about:
                break;
            case R.id.update:
                checkVersion();
                break;
            case R.id.login:
                ActivityUtils.startActivity(LoginActivity.class);
                break;
            case R.id.register:
                Intent intentRi = new Intent(getActivity(), RegisterActivity.class);
                intentRi.putExtra("from", "user");
                startActivity(intentRi);
                break;
            case R.id.super_quit:
                SPUtils.getInstance().clear();
                ActivityUtils.startActivity(LoginActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 版本检测
     *
     */

    private void checkVersion() {
        JSONObject jsonObject=new JSONObject();
        int appVersionCode = AppUtils.getAppVersionCode();
        try {
            jsonObject.put("versioncode",appVersionCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_UPDATE, getActivity(), jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {


            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });
    }
}
