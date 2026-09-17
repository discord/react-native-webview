import * as React from 'react';
import { requireNativeComponent } from "react-native";
const RNCWebViewTarget = requireNativeComponent('RNCWebViewTarget');
export default function WebViewTarget(props) {
    return React.createElement(RNCWebViewTarget, props);
}
