package com.chimu.myapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.chimu.myapp.R;
import com.chimu.mylib.base.BaseActivity;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

/**
 * Created by Longwj on 2017/11/9.
 */

public class WebActivity extends BaseActivity implements View.OnClickListener {
    private Button button;
    private BridgeWebView webView;

    @Override
    public int contentView() {
        return R.layout.activity_js;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = (BridgeWebView) findViewById(R.id.wv_web);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
        WebSettings settings = webView.getSettings();
//支持js
        settings.setJavaScriptEnabled(true);
//设置字符编码
        settings.setDefaultTextEncodingName("utf-8");
// 支持缩放
        settings.setSupportZoom(true);
// //启用内置缩放装置
        settings.setBuiltInZoomControls(true);
// 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
// 支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setDefaultHandler(new DefaultHandler());

        webView.setWebChromeClient(new WebChromeClient());
        //加载本地网页
        webView.loadUrl("file:///android_asset/mhtml.html");
        //自定义webViewClient设置到webview上
        webView.setWebViewClient(new BridgeWebViewClient(webView));
        //加载服务器网页


        //必须和js同名函数，注册具体执行函数，类似java实现类。
        webView.registerHandler("submitFromWeb", new BridgeHandler() {

            @Override
            public void handler(String data, CallBackFunction function) {

                String str ="这是html给java的数据:" + data + "\n";
                Toast.makeText(WebActivity.this, str, Toast.LENGTH_LONG).show();
                //回调js方法
                function.onCallBack( str + "返回给alert：");

            }
        });
    }


    @Override
    public void onClick(View v) {
        if (button.equals(v)) {
            webView.callHandler("functionInJs", "java数据", new CallBackFunction() {

                @Override
                public void onCallBack(String data) {
                    // TODO Auto-generated method stub
                    Toast.makeText(WebActivity.this, "js回调数据："+data, Toast.LENGTH_LONG).show();
                }

            });
        }
    }

}
