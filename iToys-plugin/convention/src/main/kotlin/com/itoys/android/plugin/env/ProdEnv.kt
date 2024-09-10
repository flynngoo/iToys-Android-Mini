package com.itoys.android.plugin.env

import org.gradle.kotlin.dsl.provideDelegate

/**
 * @Author Gu Fanfan
 * @Email fanfan.work@outlook.com
 * @Date 2023/10/23
 */
class ProdEnv : IEnv() {

    companion object {
        val INSTANCE: IEnv by lazy { ProdEnv() }
    }

    override fun apiUrl() = stringValue(value = "http://api.xxxx/")

    override fun loginSalt() = stringValue(value = "")

    override fun umengKey() = ""

    override fun wxPayKey() = ""

    override fun aMapKey() = ""
}