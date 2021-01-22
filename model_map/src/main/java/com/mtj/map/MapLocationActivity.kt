package com.mtj.map

import android.util.Log
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode
import com.mtj.common.base.BaseActivity
import com.mtj.common.interf.PermissionCallBack
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.activity_map_location.*
import java.lang.StringBuilder


/**
 * @author  孟腾蛟
 * @Description
 * @date 2021/1/22
 */
class MapLocationActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_map_location
    }

    override fun afterBinder() {
        super.afterBinder()
        checkPermission(PermissionCallBack {
            btnLocation.setOnClickListener {
                if (btnLocation.text == "开始定位") {
                    btnLocation.text = "结束定位"
                    initLocation()
                    mlocationClient.startLocation()
                } else {
                    btnLocation.text = "开始定位"
                    mlocationClient.stopLocation()
                }
            }
        }, Permission.Group.LOCATION)
    }

    private lateinit var mlocationClient: AMapLocationClient
    private lateinit var mLocationOption: AMapLocationClientOption
    private val sb = StringBuilder()
    private fun initLocation() {
        mlocationClient = AMapLocationClient(this)
        mLocationOption = AMapLocationClientOption()
        mlocationClient.setLocationListener { aMapLocation ->
            if (aMapLocation != null) {
                if (aMapLocation.errorCode == 0) {
                    sb.append("lat: ${aMapLocation.latitude}").append(" lng: ${aMapLocation.longitude}").append("\n")

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    sb.append(("location Error, ErrCode:" + aMapLocation.errorCode) + ", errInfo:" + aMapLocation.errorInfo).append("\n")
                }
            }
            tvInfo.text = sb.toString()
        }
        mLocationOption.locationMode = AMapLocationMode.Hight_Accuracy
        mLocationOption.locationMode = AMapLocationMode.Battery_Saving;
        mLocationOption.isNeedAddress = true
        mLocationOption.interval = 1000
        mlocationClient.setLocationOption(mLocationOption)
    }

    override fun onDestroy() {
        super.onDestroy()
        mlocationClient.onDestroy()
    }

}