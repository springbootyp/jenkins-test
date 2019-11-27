///**
// * Copyright (C), 2015-2019, XXX有限公司
// * FileName: CaptivePortalLoginActivity
// * Author:   jason.yangpan
// * Date:     2019/9/24 15:16
// * Description: CaptivePortalLoginActivity
// * History:
// * <author>          <time>          <version>          <desc>
// * jason.yangpan           2019/9/24           版本号            CaptivePortalLoginActivity
// */
//package com.jenkins.ci.apple.protal;
//
//import javafx.scene.web.WebView;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈CaptivePortalLoginActivity〉
// *
// * @author jason.yangpan
// * @create 2019/9/24
// * @since 1.0.0
// */
//public class CaptivePortalLoginActivity extends Activity {
//    private static final String TAG = "CaptivePortalLogin";
//    private static final String DEFAULT_SERVER = "clients3.google.com";
//    private static final int SOCKET_TIMEOUT_MS = 10000;
//
//    // Keep this in sync with NetworkMonitor.
//    // Intent broadcast to ConnectivityService indicating sign-in is complete.
//    // Extras:
//    //     EXTRA_TEXT       = netId
//    //     LOGGED_IN_RESULT = "1" if we should use network, "0" if not.
//    private static final String ACTION_CAPTIVE_PORTAL_LOGGED_IN =
//            "android.net.netmon.captive_portal_logged_in";
//    private static final String LOGGED_IN_RESULT = "result";
//
//    private URL mURL;
//    private int mNetId;
//    private NetworkCallback mNetworkCallback;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        String server = Settings.Global.getString(getContentResolver(), "captive_portal_server");
//        if (server == null) server = DEFAULT_SERVER;
//        try {
//            mURL = new URL("http://" + server + "/generate_204");
//        } catch (MalformedURLException e) {
//            done(true);
//        }
//        ....
//        private void testForCaptivePortal() {
//            new Thread(new Runnable() {
//                public void run() {
//                    // Give time for captive portal to open.
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                    }
//                    HttpURLConnection urlConnection = null;
//                    int httpResponseCode = 500;
//                    try {
//                        urlConnection = (HttpURLConnection) mURL.openConnection();
//                        urlConnection.setInstanceFollowRedirects(false);
//                        urlConnection.setConnectTimeout(SOCKET_TIMEOUT_MS);
//                        urlConnection.setReadTimeout(SOCKET_TIMEOUT_MS);
//                        urlConnection.setUseCaches(false);
//                        urlConnection.getInputStream();
//                        httpResponseCode = urlConnection.getResponseCode();
//                    } catch (IOException e) {
//                    } finally {
//                        if (urlConnection != null) urlConnection.disconnect();
//                    }
//                    if (httpResponseCode == 204) {
//                        done(true);
//                    }
//                }
//            }).start();
//        }
//
//        private class MyWebViewClient extends WebViewClient {
//            private boolean firstPageLoad = true;
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                if (firstPageLoad) return;
//                testForCaptivePortal();
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                if (firstPageLoad) {
//                    firstPageLoad = false;
//                    // Now that WebView has loaded at least one page we know it has read in the proxy
//                    // settings.  Now prompt the WebView read the Network-specific proxy settings.
//                    setWebViewProxy();
//                    // Load the real page.
//                    view.loadUrl(mURL.toString());
//                    return;
//                }
//                testForCaptivePortal();
//            }
//        }