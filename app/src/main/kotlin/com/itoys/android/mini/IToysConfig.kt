package com.itoys.android.mini

import android.app.Application
import com.itoys.android.BuildConfig
import com.itoys.android.core.GlobalConfig
import com.itoys.android.core.app.AppBridge
import com.itoys.android.core.network.ApiResultCode
import com.itoys.android.core.network.GlobalHttpHandler
import com.itoys.android.core.network.INetworkDependency
import com.itoys.android.utils.ActivityUtils
import com.itoys.android.utils.SysUtils
import com.itoys.android.utils.expansion.invalid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @Author Gu Fanfan
 * @Email fanfan.worker@gmail.com
 * @Date 2024/3/24
 */

/**
 * API成功code
 */
private const val API_SUCCESSFUL_CODE = 1

/** 页码key */
private const val KEY_PAGE = "page"

/** 分页数量key */
private const val KEY_PAGE_SIZE = "limit"

/** 搜索关键字key */
private const val KEY_SEARCH = "search"

/** image folder */
private const val IMAGE_FOLDER = "JJST"

/** 升级api */
private const val UPGRADE_URL = "/appGrade/getGrade"

/**
 * 全局http配置
 */
private val httpHandler = object : GlobalHttpHandler {
    override fun onHttpRequestBefore(
        chain: Interceptor.Chain,
        request: Request
    ): Request = request

    override fun onHttpResultResponse(
        chain: Interceptor.Chain,
        response: Response
    ): Response {
        if (response.code == ApiResultCode.UNAUTHORIZED || response.code == ApiResultCode.FORBIDDEN) {
            if (!response.request.url.encodedPath.equals(UPGRADE_URL, ignoreCase = true)) {
                IToys.clearLoginData()

                CoroutineScope(Dispatchers.Main).launch {
                    AppBridge.getTopActivity()?.apply {
                        ActivityUtils.openLaunchActivity(this)
                        AppBridge.killAllActivities()
                    }
                }
            }
        }

        return response
    }
}

fun iToysConfig(application: Application): GlobalConfig {
    return GlobalConfig.build {
        debugMode(BuildConfig.DEBUG)
        apiHostUrl(BuildConfig.API_URL)
        apiSuccessfulCode(API_SUCCESSFUL_CODE)
        globalHttpHandler(httpHandler)
        pagingConfig(KEY_PAGE, KEY_PAGE_SIZE, KEY_SEARCH)
        imageFolder(IMAGE_FOLDER)
        networkDependency(object : INetworkDependency {

            override fun appVersion(): Long = SysUtils.appVersionCode(application)

            override fun appVersionName(): String = SysUtils.appVersionName(application)

            override fun headers() = mapOf<String, String>()

            override fun platform() = "Android"

            override fun specialHeaders() = mapOf<String, String>()

            override fun specialUrls() = listOf<String>()

            override fun token() = IToys.token.invalid()

            override fun tokenKey() = "ClientAuthorization"
        })
    }
}