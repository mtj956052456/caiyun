package com.mtj.cy

import android.app.Application
import android.content.Context

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/4/28
 */
class App : Application() {

    companion object {

        const val TOKEN = "OR0VPYnlI7oEb7d0" // 填入你申请到的令牌值

        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}