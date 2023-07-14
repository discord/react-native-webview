import { EmitterSubscription } from "react-native";
export interface IWebViewProxy {
    injectJavaScript(script: string): Promise<void>;
    addOnMessageListener(listener: (event: any) => void): EmitterSubscription;
    releaseWebView(): void;
}
//# sourceMappingURL=WebViewProxyTypes.d.ts.map