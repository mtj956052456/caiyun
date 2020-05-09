package com.mtj.cy.logic.network

import com.mtj.cy.app.CaiYunConstant
import com.mtj.cy.logic.model.RealTimeResponse
import com.sunnyweather.android.logic.model.DailyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/4/28
 */
interface WeatherService {

    @GET("v2.5/${CaiYunConstant.CAI_YUN_TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealTimeResponse>

    @GET("v2.5/${CaiYunConstant.CAI_YUN_TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}