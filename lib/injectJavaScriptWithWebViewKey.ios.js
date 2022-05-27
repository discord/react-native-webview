import { NativeModules } from 'react-native';
export default function injectJavaScript(webViewKey, script) {
    NativeModules.RNCWebViewManager.injectJavaScriptWithWebViewKey(webViewKey, script);
}
