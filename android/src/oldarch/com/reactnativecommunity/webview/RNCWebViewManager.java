package com.reactnativecommunity.webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReactApplicationContext;
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

import android.graphics.Color;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

public class RNCWebViewManager extends SimpleViewManager<RNCWebViewContainer> {

    private final RNCWebViewManagerImpl mRNCWebViewManagerImpl;

    public RNCWebViewManager() {
        mRNCWebViewManagerImpl = new RNCWebViewManagerImpl();
    }

    @Override
    public String getName() {
        return RNCWebViewManagerImpl.NAME;
    }

    @Override
    public RNCWebViewContainer createViewInstance(ThemedReactContext context) {
        return mRNCWebViewManagerImpl.createViewInstance(context);
    }

    public RNCWebViewContainer createViewInstance(ThemedReactContext context, RNCWebView webView) {
      return mRNCWebViewManagerImpl.createViewInstance(context, webView);
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowFileAccess(view, value);
    }

    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowFileAccessFromFileURLs(view, value);

    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowUniversalAccessFromFileURLs(view, value);
    }

    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowsFullscreenVideo(view, value);
    }

    @ReactProp(name = "allowsProtectedMedia")
    public void setAllowsProtectedMedia(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setAllowsProtectedMedia(view, value);
    }

    @ReactProp(name = "androidLayerType")
    public void setAndroidLayerType(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setAndroidLayerType(view, value);
    }

    @ReactProp(name = "androidAssetLoaderConfig")
    public void setAndroidAssetLoaderConfig(RNCWebViewContainer view, @Nullable ReadableMap config) {
      mRNCWebViewManagerImpl.setAssetLoaderConfig(view, config);
    }

    @ReactProp(name = "temporaryParentNodeTag")
    public void setTemporaryParentNodeTag(RNCWebViewContainer view, int nodeTag) {
      mRNCWebViewManagerImpl.setTemporaryParentNodeTag(view, nodeTag);
    }

    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setApplicationNameForUserAgent(view, value);
    }

    @ReactProp(name = "basicAuthCredential")
    public void setBasicAuthCredential(RNCWebViewContainer view, @Nullable ReadableMap value) {
        mRNCWebViewManagerImpl.setBasicAuthCredential(view, value);
    }

    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setCacheEnabled(view, value);
    }

    @ReactProp(name = "cacheMode")
    public void setCacheMode(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setCacheMode(view, value);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setDomStorageEnabled(view, value);
    }

    @ReactProp(name = "downloadingMessage")
    public void setDownloadingMessage(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setDownloadingMessage(value);
    }

    @ReactProp(name = "forceDarkOn")
    public void setForceDarkOn(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setForceDarkOn(view, value);
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setGeolocationEnabled(view, value);
    }

    @ReactProp(name = "hasOnScroll")
    public void setHasOnScroll(RNCWebViewContainer view, boolean hasScrollEvent) {
        mRNCWebViewManagerImpl.setHasOnScroll(view, hasScrollEvent);
    }

    @ReactProp(name = "incognito")
    public void setIncognito(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setIncognito(view, value);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setInjectedJavaScript(view, value);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoaded(view, value);
    }

    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setInjectedJavaScriptForMainFrameOnly(view, value);

    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(view, value);

    }

    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setJavaScriptCanOpenWindowsAutomatically(view, value);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(RNCWebViewContainer view, boolean enabled) {
        mRNCWebViewManagerImpl.setJavaScriptEnabled(view, enabled);
    }

    @ReactProp(name = "lackPermissionToDownloadMessage")
    public void setLackPermissionToDownloadMessage(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setLackPermissionToDownloadMessage(value);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setMediaPlaybackRequiresUserAction(view, value);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setMessagingEnabled(view, value);
    }

    @ReactProp(name = "menuItems")
    public void setMenuCustomItems(RNCWebViewContainer view, @Nullable ReadableArray items) {
        mRNCWebViewManagerImpl.setMenuCustomItems(view, items);
    }

    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setMessagingModuleName(view, value);
    }

    @ReactProp(name = "minimumFontSize")
    public void setMinimumFontSize(RNCWebViewContainer view, int value) {
        mRNCWebViewManagerImpl.setMinimumFontSize(view, value);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setMixedContentMode(view, value);
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setNestedScrollEnabled(view, value);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setOverScrollMode(view, value);
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSaveFormDataDisabled(view, value);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setScalesPageToFit(view, value);
    }

    @ReactProp(name = "setBuiltInZoomControls")
    public void setSetBuiltInZoomControls(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSetBuiltInZoomControls(view, value);
    }

    @ReactProp(name = "setDisplayZoomControls")
    public void setSetDisplayZoomControls(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSetDisplayZoomControls(view, value);
    }

    @ReactProp(name = "setSupportMultipleWindows")
    public void setSetSupportMultipleWindows(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setSetSupportMultipleWindows(view, value);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setShowsHorizontalScrollIndicator(view, value);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setShowsVerticalScrollIndicator(view, value);
    }

    @ReactProp(name = "source")
    public void setSource(RNCWebViewContainer view, @Nullable ReadableMap value) {
        mRNCWebViewManagerImpl.setSource(view, value, false);
    }

    @ReactProp(name = "textZoom")
    public void setTextZoom(RNCWebViewContainer view, int value) {
        mRNCWebViewManagerImpl.setTextZoom(view, value);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setThirdPartyCookiesEnabled(view, value);
    }

    @ReactProp(name = "webviewDebuggingEnabled")
    public void setWebviewDebuggingEnabled(RNCWebViewContainer view, boolean value) {
        mRNCWebViewManagerImpl.setWebviewDebuggingEnabled(view, value);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(RNCWebViewContainer view, @Nullable String value) {
        mRNCWebViewManagerImpl.setUserAgent(view, value);
    }

    @Override
    protected void addEventEmitters(
      @NonNull ThemedReactContext reactContext,
      RNCWebViewContainer container
    ) {
      container.ifHasRNCWebView(view -> {
        // Do not register default touch emitter and let WebView implementation handle touches
        view.setWebViewClient(new RNCWebViewClient());
      });
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
    public void receiveCommand(@NonNull RNCWebViewContainer reactWebViewContainer, String commandId, @Nullable ReadableArray args) {
      reactWebViewContainer.ifHasRNCWebView(webView -> {
        mRNCWebViewManagerImpl.receiveCommand(webView, commandId, args);
        super.receiveCommand(reactWebViewContainer, commandId, args);
      });
    }

    @Override
    public void onDropViewInstance(@NonNull RNCWebViewContainer view) {
        mRNCWebViewManagerImpl.onDropViewInstance(view);
        super.onDropViewInstance(view);
    }
}