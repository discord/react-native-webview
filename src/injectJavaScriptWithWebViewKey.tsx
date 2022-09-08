export default function injectJavaScriptWithWebViewKey(_webViewKey: string, _script: string, ): Promise<string | null> {
  // no-op. This should get overwritten by platform-specific implementations
  return Promise.resolve(null);
}
