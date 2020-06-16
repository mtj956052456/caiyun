package com.mtj.wanandroid.logic.network

import com.mtj.wanandroid.logic.model.OfficialAccountData
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/15
 */
interface OfficialAccountService{

    @GET("wxarticle/chapters/json")
    fun getOfficialAccount(): Call<OfficialAccountData>

}