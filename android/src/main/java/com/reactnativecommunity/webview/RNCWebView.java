package com.reactnativecommunity.webview;

import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.ThemedReactContext;

public class RNCWebView extends FrameLayout {
  private RNCWebViewManager.InternalWebView internalWebView;

  public RNCWebView(ThemedReactContext reactContext) {
    super(reactContext);
  }

  public void attachWebView(RNCWebViewManager.InternalWebView webView) {
    this.internalWebView = webView;

    // Fixes broken full-screen modals/galleries due to body height being 0.
    addView(webView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
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

  @Nullable
  public RNCWebViewManager.InternalWebView getWebView() {
    return internalWebView;
  }
}
