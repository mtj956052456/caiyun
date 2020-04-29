package com.mtj.cy.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/4/28
 */
data class RealTimeResponse(val status: String, val result: Result){

    class Result(val realtime: Realtime)

    class Realtime(val skycon: String, val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)

    class AirQuality(val aqi: AQI)

    class AQI(val chn: Float)

}