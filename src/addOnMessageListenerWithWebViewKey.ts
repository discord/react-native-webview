import { NativeModules, Platform, EmitterSubscription, NativeEventEmitter } from "react-native";
import { WebViewMessage } from './WebViewTypes';

export interface WebViewMessageWithWebViewKey extends WebViewMessage {
  webViewKey: string;
}

const scriptMessageEmitter = new NativeEventEmitter(
  Platform.select({
    ios: NativeModules.ScriptMessageEventEmitter,
    default: null
  })
);

export default function addOnMessageListenerWithWebViewKey(webViewKey: string, listener: (event: WebViewMessageWithWebViewKey) => void): EmitterSubscription {
  return scriptMessageEmitter.addListener('ReactNativeWebViewOnMessageWithWebViewKey', (eventData: WebViewMessageWithWebViewKey) => {
    if (eventData.webViewKey === webViewKey) {
      listener(eventData);
    }
  })
}
