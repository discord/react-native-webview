import { NativeModules } from 'react-native';

export default async function injectJavaScriptWithWebViewKey(webViewKey: string, script: string): Promise<string | null> {
  return NativeModules.RNCWebView.injectJavaScriptWithWebViewKey(webViewKey, script);
}
