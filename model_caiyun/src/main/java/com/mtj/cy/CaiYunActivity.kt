package com.mtj.cy

import com.alibaba.android.arouter.facade.annotation.Route
import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity
import com.mtj.cy.ui.place.PlaceFragment

@Route(path = ActivityHolder.CAIYUN)
class CaiYunActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_caiyun
    }

    override fun afterCreation() {
        super.afterCreation()
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.placeFragment,PlaceFragment())
        beginTransaction.commit()
    }

}
