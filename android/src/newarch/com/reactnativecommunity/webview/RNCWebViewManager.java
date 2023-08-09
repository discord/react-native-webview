package com.reactnativecommunity.webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNCWebViewManagerDelegate;
import com.facebook.react.viewmanagers.RNCWebViewManagerInterface;
import com.facebook.react.views.scroll.ScrollEventType;
import com.reactnativecommunity.webview.events.TopCustomMenuSelectionEvent;
import com.reactnativecommunity.webview.events.TopHttpErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingProgressEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import com.reactnativecommunity.webview.events.TopRenderProcessGoneEvent;
import com.reactnativecommunity.webview.events.TopShouldStartLoadWithRequestEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

@ReactModule(name = RNCWebViewManagerImpl.NAME)
public class RNCWebViewManager extends SimpleViewManager<RNCWebViewContainer>
        implements RNCWebViewManagerInterface<RNCWebViewContainer> {

    private final ViewManagerDelegate<RNCWebView> mDelegate;
    private final RNCWebViewManagerImpl mRNCWebViewManagerImpl;

    public RNCWebViewManager() {
        mDelegate = new RNCWebViewManagerDelegate<>(this);
        mRNCWebViewManagerImpl = new RNCWebViewManagerImpl();
    }

    @Nullable
    @Override
    protected ViewManagerDelegate<RNCWebViewContainer> getDelegate() {
        return mDelegate;
    }

    @NonNull
    @Override
    public String getName() {
        return RNCWebViewManagerImpl.NAME;
    }

    @NonNull
    @Override
    protected RNCWebView createViewInstance(@NonNull ThemedReactContext context) {
        return mRNCWebViewManagerImpl.createViewInstance(context);
    }

    @Override
    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowFileAccess(view, value);
    }

    @Override
    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowFileAccessFromFileURLs(view, value);

    }

    @Override
    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowUniversalAccessFromFileURLs(view, value);
    }

    @Override
    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowsFullscreenVideo(view, value);
    }

    @Override
    @ReactProp(name = "allowsProtectedMedia")
    public void setAllowsProtectedMedia(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowsProtectedMedia(view, value);
    }

    @Override
    @ReactProp(name = "androidLayerType")
    public void setAndroidLayerType(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setAndroidLayerType(view, value);
    }

    @Override
    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setApplicationNameForUserAgent(view, value);
    }

    @Override
    @ReactProp(name = "basicAuthCredential")
    public void setBasicAuthCredential(RNCWebViewContainer view, @Nullable ReadableMap value) {
        mRNCWebViewManagerImpl.setBasicAuthCredential(view, value);
    }

    @Override
    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setCacheEnabled(view, value);
    }

    @Override
    @ReactProp(name = "cacheMode")
    public void setCacheMode(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setCacheMode(view, value);
    }

    @Override
    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setDomStorageEnabled(view, value);
    }

    @Override
    @ReactProp(name = "downloadingMessage")
    public void setDownloadingMessage(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setDownloadingMessage(value);
    }

    @Override
    @ReactProp(name = "forceDarkOn")
    public void setForceDarkOn(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setForceDarkOn(view, value);
    }

    @Override
    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setGeolocationEnabled(view, value);
    }

    @Override
    @ReactProp(name = "hasOnScroll")
    public void setHasOnScroll(RNCWebViewContainer view, boolean hasScrollEvent) {
        mRNCWebViewManagerImpl.setHasOnScroll(view, hasScrollEvent);
    }

    @Override
    @ReactProp(name = "incognito")
    public void setIncognito(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setIncognito(view, value);
    }

    @Override
    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setInjectedJavaScript(view, value);
    }

    @Override
    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoaded(view, value);
    }

    @Override
    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setInjectedJavaScriptForMainFrameOnly(view, value);

    }

    @Override
    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(view, value);

    }

    @Override
    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setJavaScriptCanOpenWindowsAutomatically(view, value);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(RNCWebViewContainer view, boolean enabled) {
        mRNCWebViewManagerImpl.setJavaScriptEnabled(view, enabled);
    }

    @Override
    @ReactProp(name = "lackPermissionToDownloadMessage")
    public void setLackPermissionToDownloadMessage(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setLackPermissionToDownloadMessage(value);
    }

    @Override
    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setMediaPlaybackRequiresUserAction(view, value);
    }

    @Override
    @ReactProp(name = "menuItems")
    public void setMenuItems(RNCWebViewContainer view, @Nullable ReadableArray items) {
        mRNCWebViewManagerImpl.setMenuCustomItems(view, items);
    }

    @Override
    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setMessagingEnabled(view, value);
    }

    @Override
    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setMessagingModuleName(view, value);
    }

    @Override
    @ReactProp(name = "minimumFontSize")
    public void setMinimumFontSize(RNCWebViewContainer view, int value) {
        mRNCWebViewManagerImpl.setMinimumFontSize(view, value);
    }

    @Override
    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setMixedContentMode(view, value);
    }

    @Override
    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setNestedScrollEnabled(view, value);
    }

    @Override
    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setOverScrollMode(view, value);
    }

    @Override
    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSaveFormDataDisabled(view, value);
    }

    @Override
    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setScalesPageToFit(view, value);
    }

    @Override
    @ReactProp(name = "setBuiltInZoomControls")
    public void setSetBuiltInZoomControls(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSetBuiltInZoomControls(view, value);
    }

    @Override
    @ReactProp(name = "setDisplayZoomControls")
    public void setSetDisplayZoomControls(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSetDisplayZoomControls(view, value);
    }

    @Override
    @ReactProp(name = "setSupportMultipleWindows")
    public void setSetSupportMultipleWindows(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSetSupportMultipleWindows(view, value);
    }

    @Override
    @ReactProp(name = "webViewKey")
    public void setWebViewKey(RNCWebViewContainer view, String webViewKey) {
      mRNCWebViewManagerImpl.setWebViewKey(view, webViewKey);
    }

    @ReactProp(name = "temporaryParentNodeTag")
    public void setTemporaryParentNodeTag(RNCWebViewContainer view, int nodeTag) {
      mRNCWebViewManagerImpl.setTemporaryParentNodeTag(view, nodeTag);
    }

    @Override
    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setShowsHorizontalScrollIndicator(view, value);
    }

    @Override
    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setShowsVerticalScrollIndicator(view, value);
    }

    @Override
    @ReactProp(name = "newSource")
    public void setNewSource(RNCWebViewContainer view, @Nullable ReadableMap value) {
        mRNCWebViewManagerImpl.setSource(view, value, true);
    }

    @Override
    @ReactProp(name = "textZoom")
    public void setTextZoom(RNCWebViewContainer view, int value) {
        mRNCWebViewManagerImpl.setTextZoom(view, value);
    }

    @Override
    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setThirdPartyCookiesEnabled(view, value);
    }

    @Override
    @ReactProp(name = "webviewDebuggingEnabled")
    public void setWebviewDebuggingEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setWebviewDebuggingEnabled(view, value);
    }

    /* iOS PROPS - no implemented here */
    @Override
    public void setAllowingReadAccessToURL(RNCWebViewContainer view, @Nullable String value) {}

    @Override
    public void setAllowsBackForwardNavigationGestures(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setAllowsInlineMediaPlayback(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setAllowsAirPlayForMediaPlayback(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setAllowsLinkPreview(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setAutomaticallyAdjustContentInsets(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setAutoManageStatusBarEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setBounces(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setContentInset(RNCWebViewContainer view, @Nullable ReadableMap value) {}

    @Override
    public void setContentInsetAdjustmentBehavior(RNCWebViewContainer view, @Nullable String value) {}

    @Override
    public void setContentMode(RNCWebViewContainer view, @Nullable String value) {}

    @Override
    public void setDataDetectorTypes(RNCWebViewContainer view, @Nullable ReadableArray value) {}

    @Override
    public void setDecelerationRate(RNCWebViewContainer view, double value) {}

    @Override
    public void setDirectionalLockEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setEnableApplePay(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setHideKeyboardAccessoryView(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setKeyboardDisplayRequiresUserAction(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setPagingEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setPullToRefreshEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setScrollEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setSharedCookiesEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setUseSharedProcessPool(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setLimitsNavigationsToAppBoundDomains(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setTextInteractionEnabled(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setHasOnFileDownload(RNCWebViewContainer view, boolean value) {}

    @Override
    public void setMediaCapturePermissionGrantType(RNCWebViewContainer view, @Nullable String value) {}

    @Override
    public void setFraudulentWebsiteWarningEnabled(RNCWebViewContainer view, boolean value) {}
    /* !iOS PROPS - no implemented here */

    @Override
    @ReactProp(name = "userAgent")
    public void setUserAgent(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setUserAgent(view, value);
    }

    // These will never be called because we use the shared impl for now
  @Override
  public void goBack(RNCWebView view) {
    view.goBack();
  }

  @Override
  public void goForward(RNCWebView view) {
    view.goForward();
  }

  @Override
  public void reload(RNCWebView view) {
    view.reload();
  }

  @Override
  public void stopLoading(RNCWebView view) {
    view.stopLoading();
  }

  @Override
  public void injectJavaScript(RNCWebView view, String javascript) {
      view.evaluateJavascriptWithFallback(javascript);
  }

  @Override
  public void requestFocus(RNCWebView view) {
      view.requestFocus();
  }

  @Override
  public void postMessage(RNCWebView view, String data) {
      try {
        JSONObject eventInitDict = new JSONObject();
        eventInitDict.put("data", data);
        view.evaluateJavascriptWithFallback(
          "(function () {" +
            "var event;" +
            "var data = " + eventInitDict.toString() + ";" +
            "try {" +
            "event = new MessageEvent('message', data);" +
            "} catch (e) {" +
            "event = document.createEvent('MessageEvent');" +
            "event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);" +
            "}" +
            "document.dispatchEvent(event);" +
            "})();"
        );
      } catch (JSONException e) {
        throw  new RuntimeException(e);
      }
  }

  @Override
  public void loadUrl(RNCWebView view, String url) {
      view.loadUrl(url);
  }

  @Override
  public void clearFormData(RNCWebView view) {
      view.clearFormData();
  }

  @Override
  public void clearCache(RNCWebView view, boolean includeDiskFiles) {
      view.clearCache(includeDiskFiles);
  }

  @Override
  public void clearHistory(RNCWebView view) {
      view.clearHistory();
  }
  // !These will never be called

  @Override
    protected void addEventEmitters(@NonNull ThemedReactContext reactContext, RNCWebView view) {
        // Do not register default touch emitter and let WebView implementation handle touches
        view.setWebViewClient(new RNCWebViewClient());
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> export = super.getExportedCustomDirectEventTypeConstants();
        if (export == null) {
            export = MapBuilder.newHashMap();
        }
        // Default events but adding them here explicitly for clarity
        export.put(TopLoadingStartEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingStart"));
        export.put(TopLoadingFinishEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingFinish"));
        export.put(TopLoadingErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingError"));
        export.put(TopMessageEvent.EVENT_NAME, MapBuilder.of("registrationName", "onMessage"));
        // !Default events but adding them here explicitly for clarity

        export.put(TopLoadingProgressEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingProgress"));
        export.put(TopShouldStartLoadWithRequestEvent.EVENT_NAME, MapBuilder.of("registrationName", "onShouldStartLoadWithRequest"));
        export.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll"));
        export.put(TopHttpErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onHttpError"));
        export.put(TopRenderProcessGoneEvent.EVENT_NAME, MapBuilder.of("registrationName", "onRenderProcessGone"));
        export.put(TopCustomMenuSelectionEvent.EVENT_NAME, MapBuilder.of("registrationName", "onCustomMenuSelection"));
        return export;
    }

    @Override
    public @Nullable
    Map<String, Integer> getCommandsMap() {
        return mRNCWebViewManagerImpl.getCommandsMap();
    }

    @Override
    public void receiveCommand(@NonNull RNCWebView reactWebView, String commandId, @Nullable ReadableArray args) {
        mRNCWebViewManagerImpl.receiveCommand(reactWebView, commandId, args);
        super.receiveCommand(reactWebView, commandId, args);
    }

    @Override
    public void onDropViewInstance(@NonNull RNCWebView view) {
        mRNCWebViewManagerImpl.onDropViewInstance(view);
        super.onDropViewInstance(view);
    }
}