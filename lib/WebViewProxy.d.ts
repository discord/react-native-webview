import { EmitterSubscription } from "react-native";
import { IWebViewProxy } from './WebViewProxyTypes';
export default class WebViewProxy implements IWebViewProxy {
    webViewKey: string;
    constructor(webViewKey: string);
    injectJavaScript(_script: string): Promise<void>;
    addOnMessageListener(listener: (event: any) => void): EmitterSubscription;
    releaseWebView(): void;
}
//# sourceMappingURL=WebViewProxy.d.ts.map