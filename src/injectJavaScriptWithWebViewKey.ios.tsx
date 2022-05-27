import { NativeModules } from 'react-native';

export default function injectJavaScript(webViewKey: string, script: string) {
  NativeModules.RNCWebViewManager.injectJavaScriptWithWebViewKey(webViewKey, script);
}
