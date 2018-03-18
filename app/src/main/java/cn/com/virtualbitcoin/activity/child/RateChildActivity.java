package cn.com.virtualbitcoin.activity.child;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.IntroBean;

/**
 * @author admin
 */
public class RateChildActivity extends BaseActivity {


    @Bind(R.id.tv_title)
    TextView tvTitle;

    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_rate_child;
    }

    private void initDate() {
        IntroBean introBean = new IntroBean();
        introBean.setCo(" 09.02.2018-16.03.2018");
        introBean.setDescription("Socialmedia Socialmedia Socialmedia Soc ialmedia Socialmedia Socialmedia Socialmedia Socialmedia Socialmedia Socialmedia Socialmedia Socialmedia Socialm edia Socialmedia");
        introBean.setFeatures("FeaturesFeaturesFeaturesFeaturesFeaturesF eaturesFeatures");
        introBean.setTechnical("Technical detailsTechnical detailsTe chnical detailsTechnical details echnical detailsTechnical detailsTechnical detailsTechnical details");
    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.title_collection);
    }

}
