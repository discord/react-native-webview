#import <WebKit/WebKit.h>

@interface RNCWKWebViewMapManager : NSObject

+ (instancetype) sharedManager;
- (NSMapTable *)sharedWKWebViewTable;
- (NSMutableDictionary *)sharedWKWebViewDictionary;


@end
