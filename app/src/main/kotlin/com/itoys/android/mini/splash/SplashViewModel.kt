package com.itoys.android.mini.splash

import com.itoys.android.core.mvi.AbsViewModel
import com.itoys.android.mini.IToys

/**
 * @Author Gu Fanfan
 * @Email fanfan.worker@gmail.com
 * @Date 2024/3/24
 */
class SplashViewModel : AbsViewModel<SplashIntent, SplashState>() {

    override fun createUIState() = SplashState.Initial

    override fun handlerIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.Next -> {
                // 启动检查更新worker
                // UpgradeWorker.startWorker(intent.context, UpgradeWorker.APP_TYPE_SITE)
                sendUIState(navNext())
            }
        }
    }

    /**
     * 导航到下一个页面
     */
    private fun navNext(): SplashState {
        // 1. 没有登录直接跳转登录
        if (!IToys.logged) return SplashState.Login
        return SplashState.Main
    }
}