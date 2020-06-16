package com.mtj.wanandroid.logic.model

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/15
 */

data class OfficialAccountData(
    val `data`: List<OfficialAccountBean>,
    val errorCode: Int,
    val errorMsg: String
)

data class OfficialAccountBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)