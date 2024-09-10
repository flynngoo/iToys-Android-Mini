package com.itoys.android.core

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @Author Gu Fanfan
 * @Email fanfan.worker@gmail.com
 * @Date 2024/3/23
 */

/**
 * 字典
 */
data class DictModel(
    @JsonProperty("dictKey")
    @JsonAlias("code")
    val dictKey: Int?,
    @JsonProperty("dictValue")
    @JsonAlias("name", "value")
    val dictValue: String?,
) {
    // 选中状态
    var selected = false

    // 扩展字段: code
    var dictCode = 0
}

fun DictModel?.dictKey() = this?.dictKey ?: 0

fun Iterable<DictModel>.toDictValueList(): List<String?> = map(DictModel::dictValue)