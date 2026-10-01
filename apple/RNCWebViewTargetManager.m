#import "RNCWebViewTargetManager.h"
#import "RNCWebViewTarget.h"

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
