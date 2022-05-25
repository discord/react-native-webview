#import "ScriptMessageEventEmitter.h"
#import <Foundation/Foundation.h>

#import <React/RCTDefines.h>
#import <React/RCTBridgeModule.h>

@implementation ScriptMessageEventEmitter
{
  BOOL _hasListeners;
}

RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"onMessage"];
}

// Will be called when this module's first listener is added.
- (void)startObserving
{
  _hasListeners = YES;
}

// Will be called when this module's last listener is removed, or on dealloc.
- (void)stopObserving
{
  _hasListeners = NO;
}

- (void)onMessage: (nonnull NSString *)webViewKey :(nonnull NSString *)message
{
  if (_hasListeners) {
    [self sendEventWithName:@"onMessage" body:@{ @"webViewKey": webViewKey, @"message": message}];
  }
}

@end
