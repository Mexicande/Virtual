package cn.com.virtualbitcoin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.CollectionActivity;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.activity.UserSweetActivity;
import cn.com.virtualbitcoin.utils.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends Fragment {


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

    @OnClick({R.id.my_collection, R.id.my_sweet, R.id.chang_pw, R.id.join_qq, R.id.join_wechat, R.id.about, R.id.update,R.id.login,R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_collection:
                ActivityUtils.startActivity(CollectionActivity.class);
                break;
            case R.id.my_sweet:
                ActivityUtils.startActivity(UserSweetActivity.class);
                break;
            case R.id.chang_pw:
                break;
            case R.id.join_qq:
                break;
            case R.id.join_wechat:
                break;
            case R.id.about:
                break;
            case R.id.update:
                break;
            case R.id.login:
                ActivityUtils.startActivity(LoginActivity.class);
                break;
            case R.id.register:
                break;
            default:
                break;
        }
    }
}
