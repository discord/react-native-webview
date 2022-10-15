import React, { Component } from 'react';
import { Button, Switch, StyleSheet, Text, View, Alert } from 'react-native';

import WebView from 'react-native-webview';
import releaseWebView from '../../lib/releaseWebView';

const HTML = `
<!DOCTYPE html>\n
<html>
  <head>
    <title>OnOpenWindow</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=320, user-scalable=no">
    <style type="text/css">
      body {
        margin: 0;
        padding: 0;
        font: 62.5% arial, sans-serif;
        background: #ccc;
      }
      a {
        display: block;
        margin-top: 20px;
      }
    </style>
  </head>
  <body>
    <button onclick="openWindowWithoutParam()">Call window.open() from JS</button>
    <button onclick="openWindowBlank()">Call window.open('_blank') from JS</button>
    <button onclick="closePopupWindow()">Call window.close</button>
    <button onclick="sayHello()">Say Hello</button>
  </body>
</html>
`;

type Props = {};
type State = {
  shouldInterceptOpenedWindow: boolean;
  text: string;
  webViewKey: number;
  popupKey: string | undefined;
};

export default class OpenedWindow extends Component<Props, State> {
  state = {
    shouldInterceptOpenedWindow: true,
    text: 'No OpenedWindow event intercepted yet',
    webViewKey: 1,
    popupKey: undefined,
  };

  popupWebView = React.createRef();

  javascriptToInject = `
      function openWindowWithoutParam() {
        popup = window.open('https://example.com')
      }
      function openWindowBlank() {
        popup = window.open('https://example.com', '_blank')
      }
      function sayHello() {
        window.ReactNativeWebView.postMessage('Message from JS');
      }
      function closePopupWindow() {
        if (popup) {
          popup.close();
          popup = undefined;
        }
      }
      function closeWindow() {
        window.close();
      }`;

  javascriptToInjectToPopup = `
    function closeWindow() {
      window.close();
    }`;

  interceptOpenedWindow = (syntheticEvent) => {
    const { nativeEvent } = syntheticEvent;
    const { webViewKey } = nativeEvent;
    this.setState({
      text: `Intercepted OpenedWindow event for ${webViewKey} at ${Date.now()}`,
      popupKey: webViewKey,
    });
  };

  interceptClosedWindow = (syntheticEvent) => {
    const { nativeEvent } = syntheticEvent;
    const { webViewKey } = nativeEvent;
    this.setState({
      text: `Intercepted ClosedWindow event for ${webViewKey} at ${Date.now()}`,
      popupKey: undefined,
    });
  };

  toggleShouldInterceptOpenedWindow = () => {
    this.setState((prevState) => ({
      shouldInterceptOpenedWindow: !prevState.shouldInterceptOpenedWindow,
    }));
  };

  resetWebView = () => {
    if (this.state.popupKey) {
      releaseWebView(this.state.popupKey);
    }
    this.setState((prevState) => ({
      webViewKey: prevState.webViewKey + 1,
      popupKey: undefined,
    }));
  };

  closeWebView = () => {
    if (this.popupWebView) {
      // This is actually expected/intended to fail. The purpose of this test is to make sure that
      // the original WebView's injected javascript isn't bleeding into the popup window.
      this.popupWebView.current.injectJavaScript('closeWindow();');
    }
  };

  render() {
    const onOpenedWindow = this.state.shouldInterceptOpenedWindow
      ? this.interceptOpenedWindow
      : undefined;

    return (
      <View style={styles.container}>
        <View style={styles.interceptSection}>
          <Text style={styles.text}>Intercept OpenedWindow event</Text>
          <Switch
            onValueChange={this.toggleShouldInterceptOpenedWindow}
            value={this.state.shouldInterceptOpenedWindow}
          />
        </View>
        <WebView
          key={this.state.webViewKey}
          source={{ html: HTML }}
          automaticallyAdjustContentInsets={false}
          onOpenedWindow={onOpenedWindow}
          injectedJavaScriptBeforeContentLoaded={this.javascriptToInject}
          onMessage={(e: { nativeEvent: { data?: string } }) => {
            this.setState({ text: e.nativeEvent.data });
          }}
        />
        <Text style={styles.text}>{this.state.text}</Text>
        <Button title="Reset webview" onPress={this.resetWebView} />
        <Button title="Close maybe?" onPress={this.closeWebView} />
        {this.state.popupKey && (
          <WebView
            ref={this.popupWebView}
            webViewKey={this.state.popupKey}
            onClosedWindow={this.interceptClosedWindow}
          />
        )}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    height: 400,
  },
  interceptSection: {
    alignItems: 'center',
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 20,
  },
  text: {
    color: 'black',
  },
});
