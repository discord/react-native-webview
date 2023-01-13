import * as React from 'react';
import {View, Button, Text} from 'react-native';
import {WebView, releaseWebView} from 'react-native-webview';

const URI = 'https://www.apache.org';
const WEB_VIEW_KEY = 'TemporaryParentingWebViewKey'

const WEBVIEW_WIDTH = 360;
const WEBVIEW_HEIGHT = 150;


export default function TemporaryParenting() {
  const [nodeTag, setNodeTag] = React.useState<number>(0);
  const [showWebView, setShowWebView] = React.useState<boolean>(false);

  const viewRef = React.useCallback(
    (node) => {
      if (node) {
        setNodeTag(node._nativeTag);
      }
    },
    [setNodeTag]
  );

  const mountButtonTitle = showWebView ? 'Temporary Parent to Red Square' : 'Show WebView'

  return (
    <>
     <View ref={viewRef} style={{width: 75, height: 75, marginBottom: 10, backgroundColor: 'red'}}>
     </View>
      <Button title={`${mountButtonTitle}`} onPress={() => setShowWebView(!showWebView)} />

      <Button title="Release WebView" onPress={() => { releaseWebView(WEB_VIEW_KEY); setShowWebView(false) }} />
      <Text>Releasing the webview will destroy it, tapping on Mount again will create a new instance of the webview.</Text>

      {showWebView &&
      <View style={{width: WEBVIEW_WIDTH, height: WEBVIEW_HEIGHT, marginTop: 25}}>
        <WebView
          source={{
            uri: URI,
          }}
          webViewKey={WEB_VIEW_KEY}
          temporaryParentNodeTag={nodeTag}
        />
      </View>
      }
    </>
  )
}
