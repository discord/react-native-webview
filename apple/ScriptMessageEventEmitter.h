#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

@interface ScriptMessageEventEmitter : RCTEventEmitter <RCTBridgeModule>
- onMessage: (nonnull NSString *)webViewKey :(nonnull NSString *)message
@end
