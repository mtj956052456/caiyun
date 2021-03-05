package com.mtj.common.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.mtj.common.R
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

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
        initSmartRefresh()
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

    fun getApp(): Context? {
        return context
    }

    private fun initSmartRefresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, R.color.black) //全局设置主题颜色
            ClassicsHeader(context) //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout -> //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context).setDrawableSize(20f)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}