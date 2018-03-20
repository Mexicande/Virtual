package cn.com.virtualbitcoin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.adapter.User_SweetAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.AmountBean;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.bean.UserSweetBean;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;

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
    private ArrayList<SweetList.CandyBean>mList=new ArrayList<>();
    private String mToken;
    private View notDataView;

    public void goBack(View v) {
        finish();
    }
    @Override
    public int getLayoutResource() {
        return R.layout.activity_user_sweet;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString(Contacts.token);
        initView();
        initDate();

    }

    private void initDate() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Contacts.token, mToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(Api.GET_MYSWEET, this, jsonObject, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                Gson gson = new Gson();
                SweetList terraceBean = gson.fromJson(data.toString(), SweetList.class);

                if(terraceBean.getCandy()==null){
                    mUserSweetAdapter.setEmptyView(notDataView);
                }else {
                    mList.addAll(terraceBean.getCandy());
                    mUserSweetAdapter.setNewData(mList);
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
            }
        });

    }

    private void initView() {
        mUserSweetAdapter=new User_SweetAdapter(mList);
        userSweetRecycler.setLayoutManager(new LinearLayoutManager(this));
        userSweetRecycler.setAdapter(mUserSweetAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) userSweetRecycler.getParent(), false);

    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_usersweet);
    }
}
