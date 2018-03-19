package cn.com.virtualbitcoin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.activity.login.LoginActivity;
import cn.com.virtualbitcoin.adapter.SweetAdapter;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.SweetList;
import cn.com.virtualbitcoin.common.Api;
import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;
import cn.com.virtualbitcoin.utils.SPUtils;
import cn.com.virtualbitcoin.utils.ToastUtils;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * @author apple
 */
public class GetSweetActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.Rtext)
    TextView Rtext;
    @Bind(R.id.sweet_recycler)
    RecyclerView mSweetRecycler;
    private SweetAdapter mSweetAdapter;
    private String mToken;
    ArrayList<SweetList.CandyBean> mArrayList = new ArrayList<>();
    private static final int REQUESTION_CODE=2000;
    private static final int RESULT_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken= SPUtils.getInstance().getString("token");
        if(mToken.isEmpty()){
            //未登录
            getDate(Api.GET_SWEETLSIT);
        }else {
            //已登录
            getDate(Api.GET_USUERSWEETLSIT);
        }

        initView();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_get_sweet;
    }

    public void goBack(View v) {
        finish();
    }

    private void getDate(String url) {
        JSONObject object=new JSONObject();
        try {
            object.put("page",1);
            object.put("number",10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api.getReQuest(url, this, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                Gson gson=new Gson();
                SweetList list = gson.fromJson(data.toString(), SweetList.class);
                mSweetAdapter.setNewData(list.getCandy());
            }

            @Override
            public void requestFailure(int code, String msg) {

            }
        });

    }

    private void initView() {
        mSweetAdapter = new SweetAdapter(R.layout.sweet_item, mArrayList);
        mSweetRecycler.setLayoutManager(new LinearLayoutManager(this));
        mSweetRecycler.setAdapter(mSweetAdapter);

        mSweetAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RelativeLayout viewByPosition = (RelativeLayout) mSweetAdapter.getViewByPosition(mSweetRecycler, position, R.id.layout2);
                RelativeLayout viewByPosition2 = (RelativeLayout) mSweetAdapter.getViewByPosition(mSweetRecycler, position, R.id.layout1);
                SweetList.CandyBean item = mSweetAdapter.getItem(position);

                switch (view.getId()) {
                    case R.id.layout1:
                        viewByPosition2.setVisibility(View.GONE);
                        viewByPosition.setVisibility(View.VISIBLE);
                        break;
                    case R.id.layout:
                        viewByPosition2.setVisibility(View.VISIBLE);
                        viewByPosition.setVisibility(View.GONE);
                        break;
                    case R.id.tv_sign:
                        if(mToken.isEmpty()){
                            Intent intent=new Intent(GetSweetActivity.this, LoginActivity.class);
                            startActivityForResult(intent,REQUESTION_CODE);
                        }else {
                            if(Integer.parseInt(item.getStatus())==0){
                                toGetSweet(item,position);
                            }else {
                                ToastUtils.showShort("已领取");
                            }
                        }
                        break;
                    case R.id.tv_toGet:
                        Intent intent=new Intent(GetSweetActivity.this,WebViewActivity.class);
                        intent.putExtra("url",item.getLink());
                        intent.putExtra("title",item.getName());
                        if(item.getLink()!=null&&!item.getLink().isEmpty()){
                            startActivity(intent);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }
    /**
     * 标记 sign
     */
    private void toGetSweet(final  SweetList.CandyBean item, final int position) {


        JSONObject object=new JSONObject();
        try {
            object.put("token",mToken);
            object.put("candy_id",item.getId());
            object.put("status",1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api.getReQuest(Api.GET_SWEET, this, object, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                item.setStatus("1");
                mArrayList.set(position,item);
                mSweetAdapter.notifyItemChanged(position);

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showShort(msg);
            }
        });

    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.sweet_title);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTION_CODE){
            switch (resultCode){
                case RESULT_CODE:
                    mToken= SPUtils.getInstance().getString(Contacts.token);
                    getDate(Api.GET_USUERSWEETLSIT);
                    break;
                default:
                    break;
            }
        }
    }

/*    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }*/
}
