#import "ScriptMessageEventEmitter.h"
#import <Foundation/Foundation.h>

#import <React/RCTDefines.h>
#import <React/RCTBridgeModule.h>

NSString * const kScriptMessageNotificationName = @"ScriptMessageNotificationName";

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
  NSLog(@"pikachu ScriptMessageEventEmitter startObserving");
  _hasListeners = YES;
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(onMessageNotification:)  name:kScriptMessageNotificationName object:nil];

}

// Will be called when this module's last listener is removed, or on dealloc.
- (void)stopObserving
{
  _hasListeners = NO;
}

- (void) onMessageNotification:(NSNotification *) notification
{
    // [notification name] should always be @"TestNotification"
    // unless you use this method for observation of other notifications
    // as well.

    if ([[notification name] isEqualToString:kScriptMessageNotificationName])
        NSLog (@"Successfully received the test notification!");
}

//- (void)onMessage: (nonnull NSString *)webViewKey :(nonnull NSString *)message
//{
//  if (_hasListeners) {
//    [self sendEventWithName:@"onMessage" body:@{ @"webViewKey": webViewKey, @"message": message}];
//  }
//}

@end
