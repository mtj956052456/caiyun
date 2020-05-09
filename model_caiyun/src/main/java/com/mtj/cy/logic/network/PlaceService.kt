package com.mtj.cy.logic.network

import com.mtj.cy.app.CaiYunConstant
import com.mtj.cy.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/4/28
 */
interface PlaceService {

    @GET("v2/place?token=${CaiYunConstant.CAI_YUN_TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}