package com.wll.myproject.aggregatedata;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWebViewActivity extends BaseActivity {

    @BindView(R.id.m_webtoolbar)
    Toolbar mWebtoolbar;
    @BindView(R.id.m_webview)
    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_web_view );
        ButterKnife.bind( this );
        initToolbar();
        initWebView();
    }

    private void initWebView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra( "weburl" );
        mWebview.loadUrl( url );
        //声明WebSettings子类，
        WebSettings webSettings = mWebview .getSettings();
        //如果访问的页面中要与javaScript交互,则webView必须设置支持JavaScript。
        webSettings.setJavaScriptEnabled( true );
        //支持插件。
        //设置屏幕自适应
        webSettings.setUseWideViewPort( true );
        //缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode( true );
        //缩放操作
        webSettings.setSupportZoom( true );//支持缩放，默认为true，是下面那个的前提。
        webSettings.setBuiltInZoomControls( true );//设置内置的缩放控件，若为false,则该webView不可缩放。
        webSettings.setDisplayZoomControls( false );//隐藏原生的缩放控件。

        //其他细节操作
        webSettings.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );//关闭webView中缓存。
        webSettings.setAllowFileAccess( true );//设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置支持通过js打开新窗口
        webSettings.setLoadsImagesAutomatically( true );//设置自动加载图片。
        webSettings.setDefaultTextEncodingName( "utf-8" );//设置编码格式。

        //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器，而是在本webView中显示。
        mWebview.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url );
                return true;
            }
        } );

        //webView默认是不处理https请求的，页面显示空白，需要进行如下设置。
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        });

        //作用：辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等。
        //onProgressChanged() 作用：获得网页的加载进度并显示
        mWebview.setWebChromeClient( new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    //设置加载进度条。
//                    progress.setText(progress);
                } else {
                }
            }
        } );


    }

    private void initToolbar() {
        mWebtoolbar.setNavigationIcon( R.mipmap.backs );
        mWebtoolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();
            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
}
