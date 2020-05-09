package com.mtj.android

import com.alibaba.android.arouter.launcher.ARouter
import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_main
    }

    override fun afterBinder() {
        super.afterBinder()
        btn_login.setOnClickListener {
            ARouter.getInstance().build(ActivityHolder.LOGIN).navigation()
        }
        btn_cy.setOnClickListener {
            ARouter.getInstance().build(ActivityHolder.CAIYUN).navigation()
        }
    }

}