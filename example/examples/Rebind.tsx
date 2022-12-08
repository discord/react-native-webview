import React, { Component } from 'react';
import { View, Button } from 'react-native';

import WebView from 'react-native-webview';

export default class Rebind extends Component {
  constructor(props) {
    super(props);

    this.googleRef = React.createRef(null);
    this.yelpRef = React.createRef(null);
  }

  render() {
    return (
      <View style={{ height: 400 }}>
        <WebView
          ref={this.googleRef}
          webViewKey="TEST"
          source={{ uri: `https://zombo.com` }}
          style={{ width: 200, height: 200 }}
        />
        <WebView
          ref={this.yelpRef}
          webViewKey="TEST"
          source={{ uri: `https://yelp.com` }}
          style={{ width: 200, height: 200 }}
        />
        <Button
          title="Bind to google"
          onPress={() => {
            console.log('google ref', this.googleRef);
            this.googleRef.current.rebind('TEST');
          }}
        />
        <Button
          title="Bind to yelp"
          onPress={() => {
            this.yelpRef.current.rebind('TEST');
          }}
        />
      </View>
    );
  }
}
