import * as React from 'react';
import { requireNativeComponent, StyleProp, ViewStyle } from 'react-native';

export interface WebViewTargetProps {
  /**
   * The webViewKey of a WebView that already exists — one built by a <WebView> with the same key. This target
   * borrows that native view and displays it here.
   */
  webViewKey: string;
  /**
   * React tag of the view the WebView should be returned to when this target unmounts, so it survives for the
   * next target instead of being torn down. Typically the shared temporary-parent placeholder.
   */
  temporaryParentNodeTag?: number;
  style?: StyleProp<ViewStyle>;
}

const RNCWebViewTarget = requireNativeComponent<WebViewTargetProps>('RNCWebViewTarget');

/**
 * Displays a WebView that already exists and is owned elsewhere. Where <WebView> builds, configures, and owns a
 * native WebView (keyed by webViewKey), <WebViewTarget> only borrows one by that key and reparents it here to
 * show it. It never sets a source, configuration, delegates, or message handlers, so the owning <WebView> keeps
 * the configuration and the message/callback stream while this target merely displays the view. This lets a
 * single pooled WebView be shown in different places over time — moved rather than rebuilt — with no reload.
 */
export default function WebViewTarget(props: WebViewTargetProps) {
  return <RNCWebViewTarget {...props} />;
}
