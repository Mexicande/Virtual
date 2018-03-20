package cn.com.virtualbitcoin.activity.child;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.bean.RateBean;
import cn.com.virtualbitcoin.utils.GlideCircleTransform;

/**
 * @author admin
 */
public class RateChildActivity extends BaseActivity {


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_ico)
    TextView tvIco;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_industry)
    TextView tvIndustry;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.feature)
    TextView feature;
    @Bind(R.id.tv_technical)
    TextView tvTechnical;
    @Bind(R.id.tv_website)
    TextView tvWebsite;

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
        RateBean.GradeBean rateBean = (RateBean.GradeBean) getIntent().getSerializableExtra("Grade");
        if (rateBean != null) {
            tvName.setText(rateBean.getName());
            setData(rateBean);
        }

    }

    private void setData(RateBean.GradeBean item) {

        Glide.with(this).load(item.getLogo())
                .error(R.mipmap.logo)
                .placeholder(R.mipmap.logo)
                .transform(new GlideCircleTransform(this))
                .into(ivLogo);

        setTextColor(getResources().getString(R.string.rate_ico),tvIco,item.getIco_date());
        setTextColor(getResources().getString(R.string.rate_product),tvType,item.getProduct_type());
        setTextColor(getResources().getString(R.string.rate_industry),tvIndustry,item.getIndustry());
        setTextColor(getResources().getString(R.string.rate_desc),tvDesc,item.getDescription());
        setTextColor(getResources().getString(R.string.rate_feat),feature,item.getFeatures());
        setTextColor(getResources().getString(R.string.rate_tech),tvTechnical,item.getTechnical());
        setTextColor(getResources().getString(R.string.rate_Website),tvWebsite,item.getWebsite());

    }

    private void setTextColor(String stName,TextView tv,String str) {
        if(str!=null&&!str.isEmpty()){
            String s=stName+" "+str;
            int i = s.indexOf(":");

            SpannableString spanString = new SpannableString(s);


            ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.black_color));
            ForegroundColorSpan span1 = new ForegroundColorSpan(getResources().getColor(R.color.black_666));
            AbsoluteSizeSpan spanSize = new AbsoluteSizeSpan(18,true);
            AbsoluteSizeSpan spanSize1 = new AbsoluteSizeSpan(15,true);

            spanString.setSpan(span, 0, i+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanString.setSpan(spanSize, 0, i+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            spanString.setSpan(span1, i+1, s.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanString.setSpan(spanSize1, i+1, s.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            tv.setText(spanString);
        }

    }

    @Override
    protected void setToolbarTitle() {
        tvTitle.setText(R.string.blockchain_rate);
    }

}
