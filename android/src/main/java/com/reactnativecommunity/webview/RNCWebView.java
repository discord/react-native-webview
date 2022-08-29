package com.reactnativecommunity.webview;

import android.widget.FrameLayout;

import com.facebook.react.uimanager.ThemedReactContext;

public class RNCWebView extends FrameLayout {
  private RNCWebViewManager.InternalWebView internalWebView;

  public RNCWebView(ThemedReactContext reactContext) {
    super(reactContext);
    setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  public void attachWebView(RNCWebViewManager.InternalWebView webView) {
    this.internalWebView = webView;
    addView(webView);
  }

  public RNCWebViewManager.InternalWebView detachWebView() {
    removeWebViewFromParent();
    RNCWebViewManager.InternalWebView webView = internalWebView;
    this.internalWebView = null;
    return webView;
  }

  public void removeWebViewFromParent() {
    if (internalWebView != null) {
      removeView(internalWebView);
    }
  }

  public RNCWebViewManager.InternalWebView getWebView() {
    return internalWebView;
  }
}
