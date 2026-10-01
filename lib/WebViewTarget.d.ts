import * as React from 'react';
import { StyleProp, ViewStyle } from 'react-native';
export interface WebViewTargetProps {
    webViewKey: string;
    temporaryParentNodeTag?: number;
    style?: StyleProp<ViewStyle>;
}
export default function WebViewTarget(props: WebViewTargetProps): React.ReactElement;
