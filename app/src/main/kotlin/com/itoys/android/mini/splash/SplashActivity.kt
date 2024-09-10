package com.itoys.android.mini.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.itoys.android.core.activity.AbsMviActivity
import com.itoys.android.databinding.SplashActivityLayoutBinding
import com.itoys.android.utils.expansion.collect
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author Gu Fanfan
 * @Email fanfan.worker@gmail.com
 * @Date 2024/3/24
 */
@AndroidEntryPoint
class SplashActivity : AbsMviActivity<SplashActivityLayoutBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel? by viewModels()

    override fun createViewBinding() = SplashActivityLayoutBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initialize(savedInstanceState: Bundle?) {
    }

    override fun onResume() {
        super.onResume()
        viewModel?.sendIntent(SplashIntent.Next(this@SplashActivity))
    }

    override fun addObserver() {
        super.addObserver()

        viewModel?.apply { collect(uiState, ::collect) }
    }

    /**
     * ui state
     */
    private fun collect(ui: SplashState?) {
        when (ui) {
            is SplashState.Login -> {}

            is SplashState.Main -> {}

            else -> {}
        }
    }
}