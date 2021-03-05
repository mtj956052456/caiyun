package com.mtj.map

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map.*


/**
 * @author  孟腾蛟
 * @Description
 * @date 2021/1/22
 */
@Route(path = ActivityHolder.MAP)
class MapActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_map
    }

    override fun afterCreation() {
        super.afterCreation()
        initCustomToolbar("地图")
        btnPoi.setOnClickListener {
            startActivity(Intent(this, MapPoiActivity::class.java))
        }
        btnLocation.setOnClickListener {
            startActivity(Intent(this, MapLocationActivity::class.java))
        }
    }

}