package com.mtj.wanandroid

import com.alibaba.android.arouter.facade.annotation.Route
import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity
import com.mtj.wanandroid.ui.official_account.OfficialAccountFragment

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/5/8
 */
@Route(path = ActivityHolder.WAN_ANDROID)
class WanAndroidActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_wanandroid
    }

    override fun afterBinder() {
        super.afterBinder()
        setSwipeBackEnable(false)

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fragment_layout, OfficialAccountFragment())
        beginTransaction.commit()

    }
}