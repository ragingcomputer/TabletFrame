package com.ragingcomputer.apps.tabletframe;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String DASHBOARD_URL = "http://openhab.raging.space:3030/";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REFRESH_TIMER = 120000;
    private static final int IMMERSIVE_TIMER = 3000;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.loadUrl(DASHBOARD_URL);
        webView.setWebViewClient(new WebViewClient());
        webView.setOnTouchListener(this);
        hideSystemUI();
    }

    //runs without a timer by reposting this handler at the end of the runnable
    Handler refreshHandler = new Handler();
    Runnable refreshRunnable = new Runnable() {

        @Override
        public void run() {
            Log.d(TAG, "run-timer");
            reloadPage();
        }
    };

    //runs without a timer by reposting this handler at the end of the runnable
    Handler immersiveHandler = new Handler();
    Runnable immersiveRunnable = new Runnable() {

        @Override
        public void run() {
            Log.d(TAG, "run-immersive");
            hideSystemUI();
        }
    };

    private void reloadPage() {
        refreshHandler.removeCallbacks(refreshRunnable);
        refreshHandler.postDelayed(refreshRunnable, REFRESH_TIMER);

        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.clearCache(true);
        webView.reload();
        hideSystemUI();
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        Log.d(TAG, "hideSystemUI");
        View mDecorView;
        mDecorView = findViewById(R.id.layout_main);
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onWindowFocusChanged");
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.d(TAG, "onWindowFocusChanged-hasFocus");
            immersiveHandler.removeCallbacks(immersiveRunnable);
            immersiveHandler.postDelayed(immersiveRunnable, IMMERSIVE_TIMER);
        }
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        Log.d(TAG, "onTouch");
        refreshHandler.removeCallbacks(refreshRunnable);
        refreshHandler.postDelayed(refreshRunnable, REFRESH_TIMER);
        hideSystemUI();

        return false;//false indicates the event is not consumed
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        reloadPage();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            reloadPage();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        refreshHandler.removeCallbacks(refreshRunnable);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        refreshHandler.removeCallbacks(refreshRunnable);
        super.onDestroy();
    }

}
