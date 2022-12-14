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
      <View style={{ height: 600 }}>
        <View style={{ width: 300, height: 400, borderWidth: 2, borderColor: 'red' }}>
        <WebView
          ref={this.googleRef}
          webViewKey="TEST"
          source={{ uri: "https://yelp.ca" }}
          style={{flex: 1}}

        />
        </View>
        <View style={{ width: 150, height: 100, borderColor: 'blue', borderWidth: 2 }}>
          <WebView
            ref={this.yelpRef}
            webViewKey="TEST"
            source={{ uri: "https://yelp.ca" }}
            style={{flex: 1}}
          />
        </View>
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
