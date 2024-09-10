package com.itoys.android.hybrid

import android.content.Context
import com.itoys.android.utils.expansion.actOpen

/**
 * @Author Gu Fanfan
 * @Email fanfan.worker@gmail.com
 * @Date 2024/5/9
 */
object HybridRouter {

    /**
     * 导航到 hybrid
     */
    fun navHybrid(context: Context, url: String) {
        IToysWebViewActivity::class.actOpen(context, HybridArgs.ARGS_KEY_URL to url)
    }

    /**
     * 导航到 hybrid
     */
    fun navHybrid(context: Context, url: String, title: String) {
        IToysWebViewActivity::class.actOpen(
            context,
            HybridArgs.ARGS_KEY_URL to url,
            HybridArgs.ARGS_KEY_TITLE to title
        )
    }
}