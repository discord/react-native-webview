import { NativeModules } from 'react-native';

export default function injectJavaScript(webViewKey: string) {
  NativeModules.RNCWebView.injectJavaScript(webViewKey);
}
