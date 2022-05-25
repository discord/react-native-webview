#import <Foundation/Foundation.h>
#import "RNCScriptMessageManager.h"

@implementation RNCScriptMessageHandler

- (instancetype)initWithWebViewKey:(NSString *)webViewKey {
  self = [super init];
  if (self) {
    _webViewKey = webViewKey;
  }
  return self;
}

//- (instancetype)initWithWebViewKey:(NSString *)webViewKey:(id<WKScriptMessageHandler>)scriptDelegate {
//    self = [super init];
//    if (self) {
//        _scriptDelegate = scriptDelegate;
//    }
//    return self;
//}

- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message {
  // TODO: send message to event emitter.
  // TODO: include web view key in message.
  NSLog(@"pikachu handling message in RNCScriptMessageHandler. message: %@", message);
//    [self.scriptDelegate userContentController:userContentController didReceiveScriptMessage:message];
}

@end

@interface RNCScriptMessageManager() {
  NSMutableDictionary *_sharedMessageHandlerDictionary;
}
@end

@implementation RNCScriptMessageManager

+ (id) sharedManager {
    static RNCScriptMessageManager *_sharedManager = nil;
    @synchronized(self) {
      if(_sharedManager== nil) {
        _sharedManager = [[super alloc] init];
      }
      return _sharedManager;
    }
}

- (NSMutableDictionary *)sharedMessageHandlerDictionary {
  if (!_sharedMessageHandlerDictionary) {
    _sharedMessageHandlerDictionary = [[NSMutableDictionary alloc] init];
    }
  return _sharedMessageHandlerDictionary;
}

- (void)addScriptMessageHandlerWithName:(NSString *)name withUserContentController:(WKUserContentController *)userContentController withWebViewKey:(NSString *)webViewKey {
  RNCScriptMessageHandler* scriptMessageHandler = [[RNCScriptMessageHandler alloc] initWithWebViewKey:webViewKey];
  [userContentController addScriptMessageHandler: scriptMessageHandler name:name];
}

@end
