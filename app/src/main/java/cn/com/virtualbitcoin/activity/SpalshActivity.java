package cn.com.virtualbitcoin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

import cn.com.virtualbitcoin.MainActivity;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.utils.ActivityUtils;
import cn.com.virtualbitcoin.utils.SharedPreferencesUtil;
import cn.com.virtualbitcoin.utils.StatusBarUtil;

/**
 * Created by apple on 2018/3/21.
 */

public class SpalshActivity extends AppCompatActivity {

    private SwitchHandler mHandler = new SwitchHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));

        if (!this.isTaskRoot()) {
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                    return;
                }
            }
        }
        setWelcome();
    }


    private static class SwitchHandler extends Handler {

        private WeakReference<SpalshActivity> mWeakReference;

        SwitchHandler(SpalshActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            SpalshActivity activity = mWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                       // ActivityUtils.startActivity(MainActivity.class);
                        ActivityUtils.startActivity(WelcomeActivity.class);
                        activity.finish();
                        break;
                    default:
                        break;

                }
            }
        }
    }
    private void setWelcome( ){
        boolean isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
           /* ActivityUtils.startActivity(WelcomeActivity.class);
            finish();*/
            mHandler.sendEmptyMessageDelayed(1, 1000);

        }else {

            mHandler.sendEmptyMessageDelayed(1, 1000);
        }

    }
    /**
     * startActivity屏蔽物理返回按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

    }

}
