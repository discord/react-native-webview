import WebViewProxy from './WebViewProxy';
export default function getWebViewProxy(webViewKey) {
    return new WebViewProxy(webViewKey);
}
