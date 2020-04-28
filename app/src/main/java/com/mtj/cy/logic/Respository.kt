package com.mtj.cy.logic

import androidx.lifecycle.liveData
import com.mtj.cy.logic.model.Place
import com.mtj.cy.logic.network.AppNetWork
import kotlinx.coroutines.Dispatchers

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/4/28
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = AppNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }


}
