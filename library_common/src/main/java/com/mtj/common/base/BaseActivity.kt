package com.mtj.common.base

import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.mtj.common.R
import com.mtj.common.interf.PermissionCallBack
import com.mtj.common.pop.LoadingPop
import com.mtj.common.swipeback.ViewDragHelper
import com.mtj.common.swipeback.app.SwipeBackActivity
import com.yanzhenjie.permission.AndPermission
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : SwipeBackActivity() {

    protected var savedInstanceState: Bundle? = null

    protected var mBaseActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
        super.onCreate(savedInstanceState)
        setContentView(setView())
        mBaseActivity = this
        ARouter.getInstance().inject(this) //注册路由
        ActivityHolder.addActivity(this)
        setSwipeBackEnable(true)
        swipeBackLayout.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT)
        if (isNeedToChangeStatusBarColor()) {
            changeTheColorOfTheStatusBar(setStatusBarColor())
        } else {
            ImmersionBar.with(this).init()
        }
        afterCreation() //界面控件初始化
    }

    private var loadingPop: LoadingPop? = null

    open fun showDialog(show: Boolean) {
        if (show) {
            if (loadingPop == null) loadingPop = LoadingPop(mBaseActivity)
            loadingPop?.showPopupWindow()
        } else {
            if (loadingPop != null) loadingPop?.dismiss()
        }
    }

    /**
     * 初始化ToolBar
     *
     * @param title
     */
    open fun initCustomToolbar(title: String) {
        customtoolbar.setMainTitleLeftDrawable(R.mipmap.icon_back)
        customtoolbar.setMainTitle("" + title)
        lt_main_title_left.setOnClickListener { finish() }
    }

    fun intoActivity(path: String?) {
        ARouter.getInstance().build(path).navigation()
    }

    fun intoActivityWithBundle(path: String?, bundle: Bundle?, isFinish: Boolean) {
        ARouter.getInstance().build(path).with(bundle).navigation(this, object : NavigationCallback {
            override fun onFound(postcard: Postcard) {}
            override fun onLost(postcard: Postcard) {
                if (isFinish) finish()
            }

            override fun onArrival(postcard: Postcard) {}
            override fun onInterrupt(postcard: Postcard) {}
        })
    }

    protected abstract fun setView(): Int

    //onCreate之后
    protected open fun afterCreation() {
    }

    fun checkPermission(callBack: PermissionCallBack, vararg permissions: String?) {
        AndPermission.with(this)
            .runtime()
            .permission(*permissions)
            .onGranted { callBack.success() }.onDenied { ToastUtils.showShort("禁用权限部分功能将无法使用") }.start()
    }

    // 改变状态栏颜色
    open fun changeTheColorOfTheStatusBar(color: Int) {
        ImmersionBar.with(this)
            .statusBarColor(color)
            .fitsSystemWindows(true)
            .statusBarDarkFont(isLightColor(resources.getColor(color)))
            .init()
    }

    open fun isLightColor(@ColorInt color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) >= 0.5
    }

    /**
     * 是否更改状态栏颜色，默认改变
     */
    open fun isNeedToChangeStatusBarColor(): Boolean = true

    /**
     * 设置状态栏颜色
     */
    open fun setStatusBarColor(): Int = R.color.colorPrimary

    /**
     * 设置最大系统字体为标准字体
     *
     * @return
     */
    override fun getResources(): Resources {
        val resources = super.getResources()
        val configuration = resources.configuration
        configuration.fontScale = 1.0f
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return resources
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this)
        ActivityHolder.removeActivity(this)
        super.onDestroy()
    }
}