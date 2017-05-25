package com.example.administrator.newstest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.newstest.R;
import com.example.administrator.newstest.data.ConstantData;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Administrator on 2017/5/24.
 */
public class ContentActivity extends AppCompatActivity{
    private WebView mWebView;
    private ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        mWebView= (WebView) findViewById(R.id.webview);
        ivBack= (ImageView) findViewById(R.id.iv_back);
        init();

    }

    private void init() {
        String url=getIntent().getStringExtra(ConstantData.INTENT_URL);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mWebView.canGoBack()){
                    mWebView.goBack();
                } else{
                    finish();
                }

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
