package com.chimu.myapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.chimu.myapp.R;
import com.chimu.mylib.base.BaseActivity;

/**
 * Created by Longwj on 2017/11/9.
 */

public class WebActivity extends BaseActivity {
    @Override
    public int contentView() {
        return R.layout.activity_js;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = (WebView) findViewById(R.id.wv_web);
        webView.loadUrl("http://www.baidu.com");

    }
}
