package com.itoys.android.plugin.env

import org.gradle.kotlin.dsl.provideDelegate

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2023/10/23
 */
class DevEnv : IEnv() {

    companion object {
        val INSTANCE: IEnv by lazy { DevEnv() }
    }

    /** 内网 */
    override fun apiUrl() = stringValue(value = "http://api.xxxx/")

    override fun loginSalt() = stringValue(value = "")

    override fun umengKey() = stringValue(value = "")

    override fun wxPayKey() = stringValue(value = "")

    override fun aMapKey() = ""
}