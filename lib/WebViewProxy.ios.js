import { NativeModules, NativeEventEmitter } from "react-native";
var scriptMessageEmitter = new NativeEventEmitter(NativeModules.ScriptMessageEventEmitter);
var WebViewProxy = /** @class */ (function () {
    function WebViewProxy(webViewKey) {
        this.webViewKey = webViewKey;
    }
    WebViewProxy.prototype.injectJavaScript = function (script) {
        return NativeModules.RNCWebViewManager.injectJavaScriptWithWebViewKey(this.webViewKey, script);
    };
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    WebViewProxy.prototype.addOnMessageListener = function (listener) {
        var _this = this;
        return scriptMessageEmitter.addListener('ReactNativeWebViewOnMessageWithWebViewKey', function (eventData) {
            if (eventData.webViewKey === _this.webViewKey) {
                listener(eventData);
            }
        });
    };
    WebViewProxy.prototype.releaseWebView = function () {
        NativeModules.RNCWebViewManager.releaseWebView(this.webViewKey);
    };
    return WebViewProxy;
}());
export default WebViewProxy;
