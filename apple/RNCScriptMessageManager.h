#import <WebKit/WebKit.h>

@interface RNCScriptMessageHandler : NSObject<WKScriptMessageHandler>
@property (strong, nonatomic) NSString *name;
- (instancetype)initWithName:(NSString *)name;
@end


@interface RNCScriptMessageManager: NSObject
+ (instancetype) sharedManager;
- (NSMutableDictionary *)sharedMessageHandlerDictionary;
- (void)addScriptMessageHandlerWithName:(NSString *)name
                      withUserContentController: (WKUserContentController *)userContentController
                      withWebViewKey: (NSString *)webViewKey;
- (void)removeScriptMessageHandlerWithUserContentController: (WKUserContentController *)userContentController
                                             withWebViewKey: (NSString *)webViewKey;
@end
