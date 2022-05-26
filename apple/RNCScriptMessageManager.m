#import <Foundation/Foundation.h>
#import "RNCScriptMessageManager.h"
#import "ScriptMessageEventEmitter.h"

@implementation RNCScriptMessageHandler

- (instancetype)initWithName:(NSString *)name withWebViewKey:(NSString *)webViewKey {
  self = [super init];
  if (self) {
    _name = name;
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
  NSLog(@"@pikachu RNCScriptMessageManager. received message. now posting to notification center");
  [[NSNotificationCenter defaultCenter]
          postNotificationName:kScriptMessageNotificationName
   object:@{ @"webViewKey": _webViewKey, @"message": message}];
  // TODO: send message to event emitter.
  // TODO: include web view key in message.
//  NSLog(@"pikachu handling message in RNCScriptMessageHandler. message: %@", message);
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
  RNCScriptMessageHandler* scriptMessageHandler = [[RNCScriptMessageHandler alloc] initWithName:name withWebViewKey:webViewKey];
  [userContentController addScriptMessageHandler: scriptMessageHandler name:name];
  
  [self sharedMessageHandlerDictionary][webViewKey] = scriptMessageHandler;
}

-(void)removeScriptMessageHandlerWithUserContentController:(WKUserContentController *)userContentController withWebViewKey:(NSString *)webViewKey {
  RNCScriptMessageHandler* scriptMessageHandler = [self sharedMessageHandlerDictionary][webViewKey];
  
  if (scriptMessageHandler != nil) {
    [userContentController removeScriptMessageHandlerForName: [scriptMessageHandler name]];
    [self sharedMessageHandlerDictionary][webViewKey] = nil;
  }
}

@end
