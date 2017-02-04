package mycartoon.yangqian.com.mycartoon.tv.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.base.BaseActivity;
import mycartoon.yangqian.com.mycartoon.tv.entity.Act_s;
import mycartoon.yangqian.com.mycartoon.tv.entity.Video_rec;


public class WebActivity extends BaseActivity implements View.OnClickListener {
    private static final String KEY_ACT = "KEY_Act";
    private static final String KEY_VID = "KEY_Vid";
    private static final String KEY_NET = "KEY_Net";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_web)
    LinearLayout mActivityWeb;

    //对外公开一个跳转进来的方法,周边剧集
    public static void openWeb(Context context, Video_rec video_rec) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_VID, video_rec);
        context.startActivity(intent);
    }

    //演员简介
    public static void openWeb(Context context, Act_s act_s) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_ACT, act_s);
        context.startActivity(intent);
    }

    //演员简介
    public static void openWeb(Context context, String network) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_NET, network);
        context.startActivity(intent);
    }


    @BindView(R.id.pb_show)
    ProgressBar mPbShow;
    @BindView(R.id.wv_show)
    WebView mWvShow;
    private Act_s act_s;
    private Video_rec video_rec;
    private String netWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        String title = "欢迎主人回来";
        //设置toolbar
        setSupportActionBar(mToolbar);
        //给左上角加返回图标(返回按钮)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        getSupportActionBar().setTitle(title);

        mWvShow.getSettings().setJavaScriptEnabled(true);  //设置支持JavaScript，否则页面的js将不起作用
        mWvShow.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWvShow.setWebViewClient(webViewClient);
        mWvShow.setWebChromeClient(webChromeClient);

        video_rec = (Video_rec) getIntent().getSerializableExtra(KEY_VID);
        act_s = (Act_s) getIntent().getSerializableExtra(KEY_ACT);
        netWork = getIntent().getStringExtra(KEY_NET);
        if (video_rec != null) {
            mWvShow.loadUrl(video_rec.getDetail_url());
            video_rec = null;
        } else if (act_s != null) {
            mWvShow.loadUrl(act_s.getUrl());
            act_s = null;
        } else if (netWork != null) {
            mWvShow.loadUrl(netWork);
            netWork = "";
        }

    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };
    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mPbShow.setProgress(newProgress);
            if (newProgress >= 100) {
                mPbShow.setVisibility(View.GONE);
            }
        }
    };

    //用WebView点链接看了很多页以后为了让WebView支持回退功能，需要覆盖Activity类的onKeyDown()方法，如果不做任何处理，
    //点击系统回退键，整个浏览器会调用finish()而结束自身，而不是回退到上一页面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWvShow.canGoBack()) {
            mWvShow.goBack(); //返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                startActivity(ShowActivity.class);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //左上角返回按钮
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
