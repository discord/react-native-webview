package com.reactnativecommunity.webview;

import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.ThemedReactContext;

public class RNCWebView extends FrameLayout {
  private static final String TAG = "RNCWebView";
  public static final int INVALID_VIEW_ID = -1;

  private RNCWebViewManager.InternalWebView internalWebView;

  public RNCWebView(ThemedReactContext reactContext) {
    super(reactContext);
  }

  public interface Action {
    void apply(RNCWebViewManager.InternalWebView webView);
  }

  /**
   * Attaches a {@link RNCWebViewManager.InternalWebView} to the RNCWebView parent
   * Throws an exception if the provided internal webView is already attached to a parent
   * @param webView
   */
  public void attachWebView(RNCWebViewManager.InternalWebView webView) {
    this.internalWebView = webView;

    // Only re-attach the WebView if parent is null
    if (webView.getParent() != null) {
      throw new IllegalArgumentException("WebView with key: " + webView.webViewKey + " parent is non null. Cannot re-attach webview.");
    }

    // Fixes broken full-screen modals/galleries due to body height being 0.
    addView(webView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  /**
   * Detaches the internal webview from the RNCWebview parent and returns a reference to it
   * @return internalWebView
   */
  public RNCWebViewManager.InternalWebView detachWebView() {
    if (internalWebView == null) {
      throw new IllegalStateException("Webview is null");
    }

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

  /**
   * Applies an action if the internal webview is non null
   * @param action
   */
  public void ifHasInternalWebView(Action action) {
    if (internalWebView != null) {
      action.apply(internalWebView);
    } else {
      FLog.e(TAG, new Throwable(), "Internal WebView is null");
    }
  }

  /**
   * Provides the associated parent RNCWebView viewId for the provided
   * webView view id.
   * @param webView
   * @return viewId
   */
  public static int getRNCWebViewId(WebView webView) {
    Integer id = RNCWebViewMapManager.INSTANCE.getViewIdMap().get(webView.getId());
    if (id == null) {
      return INVALID_VIEW_ID;
    }
    return id;
  }
}
