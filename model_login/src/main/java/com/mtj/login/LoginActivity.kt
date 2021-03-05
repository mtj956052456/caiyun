package com.mtj.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/5/8
 */
@Route(path = ActivityHolder.LOGIN)
class LoginActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_login
    }

    override fun afterCreation() {
        super.afterCreation()
        setSwipeBackEnable(false)
    }
}