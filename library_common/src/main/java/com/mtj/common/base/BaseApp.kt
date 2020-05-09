package com.mtj.common.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/5/7
 */
open class BaseApp : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Utils.init(this)
//        if (Constant.isModuleDebug) {
            ARouter.openLog()
            ARouter.openDebug()
//        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

    fun getApp(): Context? {
        return context
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}