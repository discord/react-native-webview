#import "RNCWebViewTarget.h"
#import "RNCWKWebViewMapManager.h"

#import <React/RCTUIManager.h>
#import <WebKit/WebKit.h>

NSString *const RNCWebViewDidRegisterNotification = @"RNCWebViewDidRegister";

@implementation RNCWebViewTarget

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(onWebViewRegistered:)
                                                 name:RNCWebViewDidRegisterNotification
                                               object:nil];
  }
  return self;
}

- (void)dealloc
{
  [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)onWebViewRegistered:(NSNotification *)notification
{
  id key = notification.object;
  if (_webViewKey != nil && [key isKindOfClass:[NSString class]] && [(NSString *)key isEqualToString:_webViewKey]) {
    [self borrowWebView];
  }
}

- (WKWebView * _Nullable)mappedWebView
{
  if (_webViewKey == nil) {
    return nil;
  }
  NSMutableDictionary *sharedWKWebViewDictionary = [[RNCWKWebViewMapManager sharedManager] sharedWKWebViewDictionary];
  id webView = sharedWKWebViewDictionary[_webViewKey];
  return [webView isKindOfClass:[WKWebView class]] ? (WKWebView *)webView : nil;
}

/**
 * Borrow the shared WKWebView for our key and show it here. This only moves the view into our hierarchy; it never
 * touches the WebView's configuration, its navigation/UI delegates, or its script message handlers. Those stay
 * owned by the RNCWebView that built it, so the RPC socket and load callbacks keep flowing to that builder while
 * we display the view. The autoresizing mask makes the WebView track our bounds, so it reflows to whatever size
 * this target is laid out at — no fixed sizing anywhere. If no WebView exists for this key yet (its builder hasn't
 * registered one), there's nothing to show; we pick it up from the registration notification once it appears.
 */
- (void)borrowWebView
{
  if (self.window == nil) {
    return;
  }
  WKWebView *wkWebView = [self mappedWebView];
  if (wkWebView == nil) {
    return;
  }
  if (wkWebView.superview == self) {
    return;
  }
  // Detach from wherever it currently lives — the builder's RNCWebView, another target, or a temporary parent —
  // by reparenting. addSubview removes it from its old superview for us; we deliberately don't call the builder's
  // teardown, since it isn't going away, it's just no longer the one on screen.
  wkWebView.frame = self.bounds;
  wkWebView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
  [self addSubview:wkWebView];
}

- (void)setWebViewKey:(NSString *)webViewKey
{
  _webViewKey = webViewKey;
  [self borrowWebView];
}

- (void)didMoveToWindow
{
  [super didMoveToWindow];
  [self borrowWebView];
}

- (void)layoutSubviews
{
  [super layoutSubviews];
  WKWebView *wkWebView = [self mappedWebView];
  if (wkWebView != nil && wkWebView.superview == self) {
    wkWebView.frame = self.bounds;
  } else {
    // Not showing it yet — a common case is that our builder hadn't created the view when we first mounted. A
    // layout pass is a cheap, natural point to try again (the registration notification is the primary path).
    [self borrowWebView];
  }
}

/**
 * When this target unmounts, hand the WebView back to its home (its temporary parent) so it stays alive for the
 * next target, rather than being torn down with us. Only do this if we're the one currently showing it — if
 * another target already stole it, its own bookkeeping owns it now.
 */
- (void)removeFromSuperview
{
  WKWebView *wkWebView = [self mappedWebView];
  if (wkWebView != nil && wkWebView.superview == self) {
    UIView *home = _temporaryParentNodeTag != nil
      ? [self.bridge.uiManager viewForReactTag:_temporaryParentNodeTag]
      : nil;
    if (home != nil) {
      wkWebView.frame = home.bounds;
      [home addSubview:wkWebView];
    } else {
      [wkWebView removeFromSuperview];
    }
  }
  [super removeFromSuperview];
}

@end
