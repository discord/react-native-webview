import { NativeEventEmitter } from "react-native";
var scriptMessageEmitter = new NativeEventEmitter(undefined);
var WebViewProxy = /** @class */ (function () {
    function WebViewProxy(webViewKey) {
        this.webViewKey = webViewKey;
    }
    // eslint-disable-next-line class-methods-use-this, @typescript-eslint/no-unused-vars
    WebViewProxy.prototype.injectJavaScript = function (_script) {
        // no-op
        return Promise.resolve();
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
    // eslint-disable-next-line class-methods-use-this
    WebViewProxy.prototype.releaseWebView = function () {
        // no-op
    };
    return WebViewProxy;
}());
export default WebViewProxy;
