package cn.com.virtualbitcoin.activity;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.base.BaseActivity;
import cn.com.virtualbitcoin.common.Contacts;

public class WebViewActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.web_progress_bar)
    ProgressBar webProgressBar;
    @Bind(R.id.web_container)
    FrameLayout webContainer;
    private WebView mWebView;

    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
        webContainer.addView(mWebView, 0);
        String url = getIntent().getStringExtra(Contacts.WEB_URL);
        String title = getIntent().getStringExtra(Contacts.WEB_TITLE);
        if(url!=null&&!url.isEmpty()){
            getDate(url);
            tvTitle.setText(title);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_web_view;
    }

    private void getDate(String url) {
        if (url != null) {
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setBlockNetworkImage(false);
            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            webSettings.setGeolocationEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setSupportZoom(false);
            webSettings.setNeedInitialFocus(false);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setBuiltInZoomControls(false);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mWebView.getSettings().setMixedContentMode(
                        WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {

                        WebView.HitTestResult hitTestResult = view.getHitTestResult();
                        if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                            view.loadUrl(url);
                            return true;
                    }
                    return false;
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                }
            });

            mWebView.setWebChromeClient(new MyWebChromeClient());

        }
    }
    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                webProgressBar.setVisibility(View.GONE);
            } else {
                if (webProgressBar.getVisibility() != View.VISIBLE) {
                    webProgressBar.setVisibility(View.VISIBLE);
                }
                webProgressBar.setProgress(newProgress);
            }
        }
    }

}
