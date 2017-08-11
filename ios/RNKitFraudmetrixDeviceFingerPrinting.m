//
//  RNKitFraudmetrixDeviceFingerPrinting.m
//  RNKitFraudmetrixDeviceFingerPrinting
//
//  Created by SimMan on 2017/8/10.
//  Copyright © 2017年 RNKit.io. All rights reserved.
//

#import "RNKitFraudmetrixDeviceFingerPrinting.h"
#import "FMDeviceManager.h"

@implementation RNKitFraudmetrixDeviceFingerPrinting {
    FMDeviceManager_t *manager;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

@synthesize bridge = _bridge;

RCT_EXPORT_MODULE()

#pragma mark 初始化方法
RCT_EXPORT_METHOD(init:(NSDictionary *)args
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {
    
    if (args == nil) {
        reject(@"50000", @"args is nil", nil);
    } else {
        manager = [FMDeviceManager sharedManager];
        manager->initWithOptions(args);
        resolve(@"ok");
    }
}

#pragma mark 获取getDeviceInfo
RCT_EXPORT_METHOD(getDeviceInfo:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject) {
    if (manager) {
        NSString *blackBox = manager->getDeviceInfo();
        NSString *token = [NSString stringWithFormat:@"%@", blackBox];
        resolve(token);
    } else {
        reject(@"10001", @"请先进行初始化 -init()", nil);
    }
}

@end
