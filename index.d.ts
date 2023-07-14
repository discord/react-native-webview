import { Component } from 'react';
// eslint-disable-next-line

<<<<<<< HEAD
import releaseWebView from './lib/releaseWebView';
import injectJavaScriptWithWebViewKey from './lib/injectJavaScriptWithWebViewKey';
import addOnMessageListenerWithWebViewKey from './lib/addOnMessageListenerWithWebViewKey';
=======
import getWebViewProxy from './lib/getWebViewProxy';
>>>>>>> 11.18.1-discord-2

import { IOSWebViewProps, AndroidWebViewProps, WindowsWebViewProps } from './lib/WebViewTypes';

export { FileDownload, WebViewMessageEvent, WebViewNavigation } from "./lib/WebViewTypes";

export type WebViewProps = IOSWebViewProps & AndroidWebViewProps & WindowsWebViewProps;

declare class WebView<P = {}> extends Component<WebViewProps & P> {
    /**
     * Go back one page in the webview's history.
     */
    goBack: () => void;

    /**
     * Go forward one page in the webview's history.
     */
    goForward: () => void;

    /**
     * Reloads the current page.
     */
    reload: () => void;

    /**
     * Stop loading the current page.
     */
    stopLoading(): void;

    /**
     * Executes the JavaScript string.
     */
    injectJavaScript: (script: string) => void;

    /**
     * Focuses on WebView redered page.
     */
    requestFocus: () => void;
    
     /**
     * Posts a message to WebView.
     */
    postMessage: (message: string) => void;
    
     /**
     * (Android only)
     * Removes the autocomplete popup from the currently focused form field, if present.
     */
    clearFormData?: () => void;

     /**
     * (Android only)
     * Clears the resource cache. Note that the cache is per-application, so this will clear the cache for all WebViews used.
     */
    clearCache?: (clear: boolean) => void;

     /**
     * (Android only)
     * Tells this WebView to clear its internal back/forward list.
     */
    clearHistory?: () => void;
}

export {WebView, getWebViewProxy};
export default WebView;
