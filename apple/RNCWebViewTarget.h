/**
 * A lightweight display target for a WebView that already exists in the shared map. Where RNCWebView builds,
 * configures, and owns a WKWebView, RNCWebViewTarget only borrows one by its webViewKey and shows it here. It
 * never sets a source, a user agent, a delegate, or any configuration, so the RNCWebView that built the view
 * stays its owner — navigation callbacks and the message stream keep flowing there while this target merely
 * displays it. On unmount it hands the view back to its temporary parent so it survives for the next target.
 */
#import <React/RCTView.h>
#import <React/RCTBridge.h>

// Posted (object = webViewKey) by RNCWebView when it registers a WebView, so a target waiting on that key borrows
// it as soon as it exists rather than racing the builder.
extern NSString *const RNCWebViewDidRegisterNotification;

@interface RNCWebViewTarget : RCTView
@property (nonatomic, copy) NSString * _Nullable webViewKey;
@property (nonatomic, copy) NSNumber * _Nullable temporaryParentNodeTag;
@property (nonatomic, weak) RCTBridge * _Nullable bridge;
@end
