
[![npm](https://img.shields.io/npm/v/tnrn-amap-location.svg)](https://www.npmjs.com/package/tnrn-fraudmetrix-device)
[![npm](https://img.shields.io/npm/dm/tnrn-amap-location.svg)](https://www.npmjs.com/package/tnrn-fraudmetrix-device)
[![npm](https://img.shields.io/npm/dt/tnrn-amap-location.svg)](https://www.npmjs.com/package/tnrn-fraudmetrix-device)
[![npm](https://img.shields.io/npm/l/tnrn-amap-location.svg)](https://github.com/tnrn/tnrn-fraudmetrix-device/master/LICENSE)

FraudmetrixDeviceFingerPrinting for [React Native][rn].


## Getting Started

First, `cd` to your RN project directory, and install RNMK through [rnpm](https://github.com/rnpm/rnpm) . If you don't have rnpm, you can install RNMK from npm with the command `npm i -S tnrn-fraudmetrix-device` and link it manually (see below).

### Android

* #### React Native < 0.29 (Using rnpm)

  `rnpm install tnrn-fraudmetrix-device`

* #### React Native >= 0.29
  `$npm install -S tnrn-fraudmetrix-device`

  `$react-native link tnrn-fraudmetrix-device`

#### Manually
1. JDK 7+ is required
1. Add the following snippet to your `android/settings.gradle`:


  ```gradle
include ':tnrn-fraudmetrix-device'
project(':tnrn-fraudmetrix-device').projectDir = new File(rootProject.projectDir, '../node_modules/tnrn-fraudmetrix-device/android/app')
  ```
  
1. Declare the dependency in your `android/app/build.gradle`
  
  ```gradle
  dependencies {
      ...
      compile project(':tnrn-fraudmetrix-device')
  }
  ```
  
1. Import `import io.rnkit.fraudmetrix.devicefingerprinting;` and register it in your `MainActivity` (or equivalent, RN >= 0.32 MainApplication.java):

  ```java
  @Override
  protected List<ReactPackage> getPackages() {
      return Arrays.asList(
              new MainReactPackage(),
              new FraudmetrixDeviceFingerPrinting()
      );
  }
  ```

Finally, you're good to go, feel free to require `tnrn-fraudmetrix-device` in your JS files.

Have fun! :metal:

## Basic Usage

Import library

```
import FraudmetrixDeviceFingerPrinting from 'tnrn-fraudmetrix-device'
```

# Authentication

## iOS

```
Privacy - Location When In Use Usage Description: 我们需要通过您的地理位置信息获取您周边的相关数据
Privacy - Contacts Usage Description: 是否允许此App访问你的通讯录？

在 iOS 工程中 build phases 添加 libresolv.9.tbd
```

## Android

```
<uses-sdk android:minSdkVersion="8"/>
<!-- 必选权限 -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.WRITE_SETTINGS"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<!-- 可选权限，不声明此部分权限将放弃部分设备信息的采集，对数据分析及设备指纹的精准度有一定影响 -->
<uses-permission android:name="android.permission.GET_TASKS" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<application>
    <!-- 您的合作方标识 -->
    <meta-data android:name="PARTNER_CODE" android:value="partner" />
    <!-- !!!注意!!! 如果您从同盾SDK 1.x 版本升级到 3.x，请将 1.x 中的FMUDID_service声明删除 !!!注意!!! -->
    <!-- !!!注意!!! 否则会在某些特殊情况下导致应用崩溃，如果没有该service声明，请忽略此注释 !!!注意!!! -->
</application>
```

# Api

```
/**
 * 初始化方法
 * @param {Object} options 参数
 * ------- options ---------
 * @param {String} [required] partner 合作方编码
 * @param {String} env sandbox: 测试环境, 生产环境则直接去掉此参数
 * @param {String} allowd 值为: allowd, 生产环境则直接去掉此参数
 */
try {
  await FraudmetrixDeviceFingerPrinting.init({
    partner: 'partner',
    env: 'sandbox',
    allowd: 'allowd',
  });
  console.log(`初始化成功`);
} catch (err) {
  console.log(err);
}

/**
 * 获取 deviceInfo
 * @return {String} deviceInfo
 */
try {
  const deviceInfo = await FraudmetrixDeviceFingerPrinting.getDeviceInfo();
  alert('deviceInfo: ' + deviceInfo + ' -- 获取 deviceInfo成功!!');
} catch (err) {
  alert(`获取 deviceInfo 失败 -- err: ${err.message}`)
}

```

## Questions

[create an issue](https://github.com/tnrn/tnrn-fraudmetrix-device/issues/new)

> made with ♥
