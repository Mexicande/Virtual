package cn.com.virtualbitcoin;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import cn.com.virtualbitcoin.common.Contacts;
import cn.com.virtualbitcoin.utils.Utils;

/**
 * Created by apple on 2018/3/15.
 */

public class MyApplication extends Application {
    String channel="test";

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        //bugly
        CrashReport.initCrashReport(getApplicationContext(), "d7fddf6a40", false);

        //Walle
        channel = WalleChannelReader.getChannel(this.getApplicationContext());
        //友盟
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this,"5aaa14e3f43e48540d0000cd"
                ,channel));
        initOkGo();


    }

    /**
     * 公共参数
     */
    private void initOkGo() {
        HttpParams params=new HttpParams();

        params.put("app_version", Contacts.app_version);
        params.put("app_name", Contacts.app_Name);
        params.put("app_terminal", Contacts.app_Terminal);
        params.put("channel",channel);

        OkGo.getInstance().init(this)
                .addCommonParams(params);
    }
}
