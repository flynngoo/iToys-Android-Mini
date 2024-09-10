package com.itoys.android.mini

import com.itoys.android.utils.mmkv.MMKVOwner
import com.itoys.android.utils.mmkv.mmkvBoolean
import com.itoys.android.utils.mmkv.mmkvString
import com.tencent.mmkv.MMKV

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2024/3/4
 */
object IToys : MMKVOwner {

    private const val MMKV_FILE_ID = "iToys-BusCentralControl"

    override val mmkv: MMKV
        get() = MMKV.mmkvWithID(MMKV_FILE_ID)

    /**
     * 统一隐私政策
     */
    var AgreePrivacy: Boolean by mmkvBoolean(default = true)

    /**
     * 是否登录
     */
    var logged: Boolean by mmkvBoolean()

    /**
     * token
     */
    var token: String? by mmkvString()

    /**
     * 清楚登录状态
     */
    fun clearLoginData() {

    }
}