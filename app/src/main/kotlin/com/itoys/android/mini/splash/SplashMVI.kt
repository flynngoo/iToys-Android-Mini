package com.itoys.android.mini.splash

import android.content.Context
import com.itoys.android.core.mvi.IUIIntent
import com.itoys.android.core.mvi.IUIState

/**
 * @Author Gu Fanfan
 * @Email fanfan.worker@gmail.com
 * @Date 2024/3/24
 */

sealed class SplashIntent : IUIIntent {

    /**
     * 跳转到下一个页面
     */
    data class Next(val context: Context) : SplashIntent()
}

sealed class SplashState : IUIState {

    /** Initial state */
    data object Initial : SplashState()

    /**
     * 登录
     */
    data object Login : SplashState()

    /**
     * 主页
     */
    data object Main : SplashState()
}