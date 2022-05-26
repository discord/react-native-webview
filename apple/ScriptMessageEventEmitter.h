#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

extern NSString * const kScriptMessageNotificationName;

@interface ScriptMessageEventEmitter : RCTEventEmitter <RCTBridgeModule>
@end
