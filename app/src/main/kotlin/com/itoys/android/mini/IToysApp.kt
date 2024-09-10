package com.itoys.android.mini

import android.app.Application
import android.util.Log
import com.itoys.android.core.GlobalConfig
import com.itoys.android.core.app.AbsApp
import com.itoys.android.logcat.logcat
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2023/10/20
 *
 * app.
 */

@HiltAndroidApp
class IToysApp : AbsApp() {
    override val globalConfig: GlobalConfig by lazy { iToysConfig(application = this@IToysApp) }

    override fun syncInit(application: Application) {
        // 同意隐私政策
        if (IToys.AgreePrivacy) {
            initCompliance(application)
        }
    }

    override suspend fun asyncInit(application: Application) {
        logcat(priority = Log.INFO) { ">>>>>>>>>> 异步初始化 <<<<<<<<<<" }
        logcat(priority = Log.INFO) { ">>>>>>>>>> 异步初始化完成 <<<<<<<<<<" }
    }

    override fun globalInit(application: Application) {
        logcat(priority = Log.INFO) { ">>>>>>>>>> 全局初始化 <<<<<<<<<<" }
    }

    override fun initCompliance(application: Application) {
        logcat(priority = Log.INFO) { ">>>>>>>>>> 用户已经同意隐私政策 <<<<<<<<<<" }
    }
}