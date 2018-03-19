package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.User_SweetAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.UserSweetBean;

/**
 * @author apple
 *         User_Sweet
 */
public class UserSweetActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.userSweetRecycler)
    RecyclerView userSweetRecycler;
    private User_SweetAdapter mUserSweetAdapter;
    private ArrayList<UserSweetBean>mList=new ArrayList<>();

    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initView();

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_user_sweet;
    }

    private void initDate() {
        UserSweetBean userSweetBean=new UserSweetBean();
        userSweetBean.setEn_name("GEC");
        mList.add(userSweetBean);
        mList.add(userSweetBean);
        mList.add(userSweetBean);
        mList.add(userSweetBean);
    }

    private void initView() {
        mUserSweetAdapter=new User_SweetAdapter(mList);
        userSweetRecycler.setLayoutManager(new LinearLayoutManager(this));
        userSweetRecycler.setAdapter(mUserSweetAdapter);
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_usersweet);
    }
}
