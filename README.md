## 快速开始

### 1. 替换AppId
找到`AppConfig`并替换`appId`。
```kotlin
package com.itoys.android.plugin

object AppConfig {

    // app 包名.
    const val appId: String = "com.itoys.xxxx"
}
```

### 2. 替换签名文件
找到`AndroidApplicationPlugin`并替换签名文件信息, Line 63。

### 3. 设置环境信息
```kotlin
package com.itoys.android.plugin.env

abstract class IEnv {

    /**
     * 接口 api 地址
     */
    abstract fun apiUrl(): String

    /**
     * 登录-盐
     */
    abstract fun loginSalt(): String

    /**
     * 友盟 key
     */
    abstract fun umengKey(): String

    /**
     * 微信支付 key
     */
    abstract fun wxPayKey(): String

    /**
     * 高德地图 key
     */
    abstract fun aMapKey(): String

    fun stringValue(value: String) = "\"${value}\""
}
```