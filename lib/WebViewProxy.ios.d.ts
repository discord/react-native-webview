import { EmitterSubscription } from "react-native";
import { IWebViewProxy } from './WebViewProxyTypes';
export default class WebViewProxy implements IWebViewProxy {
    webViewKey: string;
    constructor(webViewKey: string);
    injectJavaScript(script: string): Promise<void>;
    addOnMessageListener(listener: (event: any) => void): EmitterSubscription;
    releaseWebView(): void;
}
//# sourceMappingURL=WebViewProxy.ios.d.ts.map