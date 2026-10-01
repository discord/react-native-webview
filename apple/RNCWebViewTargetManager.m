#import "RNCWebViewTargetManager.h"
#import "RNCWebViewTarget.h"

// WebViewTarget relies on UIKit view APIs, so like the other webViewKey features it's a no-op on macOS.
#if !TARGET_OS_OSX

@implementation RNCWebViewTargetManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
  RNCWebViewTarget *target = [RNCWebViewTarget new];
  target.bridge = self.bridge;
  return target;
}

RCT_EXPORT_VIEW_PROPERTY(webViewKey, NSString)
RCT_EXPORT_VIEW_PROPERTY(temporaryParentNodeTag, NSNumber)

@end

#endif // !TARGET_OS_OSX
