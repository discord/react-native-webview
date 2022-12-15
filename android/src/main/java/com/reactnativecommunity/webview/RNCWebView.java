package com.reactnativecommunity.webview;

import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.facebook.common.logging.FLog;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.Map;

public class RNCWebView extends FrameLayout {
  private static final String TAG = "RNCWebView";
  public static final int INVALID_VIEW_ID = -1;
  private String webViewKey;
  private RNCWebViewManager.InternalWebView internalWebView;

  public RNCWebView(ThemedReactContext reactContext) {
    super(reactContext);

    // There is an issue with react-native where if a view is moved
    // the parent and children views are not resized
    // Since we are moving views outside of React, the layout request might be dropped
    // on a normal add/remove view
    // By calling .layout directly on the parent, it'll force the layout change
    // See this issue for more context: https://github.com/facebook/react-native/issues/17968
    setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
      @Override
      public void onChildViewAdded(View parent, View child) {
        if (parent != null) {
          parent.measure(
            MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY)
          );
          parent.layout(0, 0, parent.getMeasuredWidth(), parent.getMeasuredHeight());
        }
      }

      @Override
      public void onChildViewRemoved(View parent, View child) {}
    });
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    if (webViewKey == null) return;

    Map<String, WebView> internalWebViewMap = RNCWebViewMapManager.INSTANCE.getInternalWebViewMap();
    Map<String, RNCWebView> rncWebViewMap = RNCWebViewMapManager.INSTANCE.getRncWebViewMap();
    
    // If there is an existing RNCWebView that has an internal webview, re-attach it to this view
    if (rncWebViewMap.containsKey(webViewKey)) {
      RNCWebView existingView = rncWebViewMap.get(webViewKey);
      RNCWebViewManager.InternalWebView existingInternalWebView = existingView.detachWebView();
      attachWebView(existingInternalWebView);

      // If there is a detached internal webview attach it to this RNCWebView
    } else if (internalWebViewMap.containsKey(webViewKey)) {
      RNCWebViewManager.InternalWebView webView = (RNCWebViewManager.InternalWebView) internalWebViewMap.get(webViewKey);
      attachWebView(webView);
    }

    if (internalWebView != null) {
      internalWebView.setWebViewKey(webViewKey);
      RNCWebViewMapManager.INSTANCE.getViewIdMap().put(internalWebView.getId(), this.getId());
      internalWebViewMap.put(webViewKey, internalWebView);
      rncWebViewMap.put(webViewKey, this);
    }
  }

  public interface Action {
    void apply(RNCWebViewManager.InternalWebView webView);
  }

  public void setWebViewKey(String webViewKey) {
    this.webViewKey = webViewKey;
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
    Integer rncViewId = RNCWebViewMapManager.INSTANCE.getViewIdMap().get(webView.getId());
    if (rncViewId == null) {
      return INVALID_VIEW_ID;
    }
    return rncViewId;
  }
}
