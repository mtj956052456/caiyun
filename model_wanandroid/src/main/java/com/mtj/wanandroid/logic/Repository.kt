package com.mtj.wanandroid.logic

import androidx.lifecycle.liveData
import com.mtj.wanandroid.logic.model.OfficialAccountBean
import com.mtj.wanandroid.logic.network.OfficialAccountNetwork
import kotlinx.coroutines.Dispatchers

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/15
 */
object Repository {

    fun getOfficialAccount() = liveData(Dispatchers.IO) {
        val result = try {
            val response = OfficialAccountNetwork.getOfficialAccount()
            if (response.errorCode == 0) {
                val officialAccount = response.data
                Result.success(officialAccount)
            } else {
                Result.failure(RuntimeException("response status is ${response.errorCode}"))
            }
        } catch (e: Exception) {
            Result.failure<List<OfficialAccountBean>>(e)
        }
        emit(result)
    }


//    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
//        val result = try {
//            block()
//        } catch (e: Exception) {
//            Result.failure<T>(e)
//        }
//        emit(result)
//    }
}