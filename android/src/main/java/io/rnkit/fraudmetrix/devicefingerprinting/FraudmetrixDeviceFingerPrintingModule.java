
package io.rnkit.fraudmetrix.devicefingerprinting;

import android.support.annotation.Nullable;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import cn.tongdun.android.shell.FMAgent;
import cn.tongdun.android.shell.exception.FMException;


public class FraudmetrixDeviceFingerPrintingModule extends ReactContextBaseJavaModule {

    private ReactApplicationContext reactContext;

    public FraudmetrixDeviceFingerPrintingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNKitFraudmetrixDeviceFingerPrinting";
    }

    @ReactMethod
    public void init(ReadableMap args, @Nullable final Promise promise) {
        if (args == null) {
            promise.reject("50000", "args is null!");
        } else {
            try {
                if (args.hasKey("env") && args.getString("env").equals("sandbox")) {
                    FMAgent.init(this.reactContext, FMAgent.ENV_SANDBOX);
                } else {
                    FMAgent.init(this.reactContext, FMAgent.ENV_PRODUCTION);
                }
                promise.resolve("ok");
            } catch (FMException e) {
                e.printStackTrace();
                promise.reject("4001", e.getMessage());
            }
        }
    }

    @ReactMethod
    public void getDeviceInfo(@Nullable final Promise promise) {
        String status = FMAgent.getInitStatus();
        if ("successful".equals(status)) {
            String deviceInfo = FMAgent.onEvent(this.reactContext);
            promise.resolve(deviceInfo);
        } else {
            promise.reject("10001", "请先进行初始化 -init()");
        }
    }

}