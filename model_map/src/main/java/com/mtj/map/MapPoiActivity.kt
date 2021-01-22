package com.mtj.map

import android.os.Bundle
import com.amap.api.maps.AMap
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.mtj.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_map_poi.*
import java.lang.StringBuilder


/**
 * @author  孟腾蛟
 * @Description
 * @date 2021/1/22
 */
class MapPoiActivity : BaseActivity() {

    private lateinit var aMap: AMap

    override fun setView(): Int {
        return R.layout.activity_map_poi
    }

    override fun afterBinder() {
        super.afterBinder()
        mapview.onCreate(savedInstanceState)
        aMap = mapview.map
        aMap.setOnCameraChangeListener(object : AMap.OnCameraChangeListener {
            override fun onCameraChangeFinish(p0: CameraPosition) {
                search(p0.target)
            }

            override fun onCameraChange(p0: CameraPosition) {
            }
        })
    }

    private fun search(target: LatLng) {
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        val geocoderSearch = GeocodeSearch(this)
        geocoderSearch.setOnGeocodeSearchListener(object : GeocodeSearch.OnGeocodeSearchListener {
            override fun onRegeocodeSearched(p0: RegeocodeResult, p1: Int) {
                val sb = StringBuilder()
                p0.regeocodeAddress.pois.forEach {
                    sb.append(it.title + "\n")
                }
                tvInfo.text = sb.toString()
            }

            override fun onGeocodeSearched(p0: GeocodeResult, p1: Int) {

            }
        })
        val query =
            RegeocodeQuery(
                LatLonPoint(target.latitude, target.longitude),
                1000f,
                GeocodeSearch.AMAP
            )
        geocoderSearch.getFromLocationAsyn(query)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapview.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapview.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapview.onSaveInstanceState(outState)
    }

}