#import <WebKit/WebKit.h>

//#if !TARGET_OS_OSX
//#import <UIKit/UIKit.h>
//#else
//#import <React/RCTUIKit.h>
//#endif // !TARGET_OS_OSX

@interface RNCScriptMessageManager: NSObject
+ (instancetype) sharedManager;
- (NSMutableDictionary *)sharedMessageHandlerDictionary;
- (void)addScriptMessageHandlerWithName:(NSString *)name
                      withUserContentController: (WKUserContentController *)userContentController
                      withWebViewKey: (NSString *)webViewKey;
@end

@interface RNCScriptMessageHandler : NSObject<WKScriptMessageHandler>
@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *webViewKey;
- (instancetype)initWithWebViewKey:(NSString *)webViewKey;
@end
