package com.mtj.wanandroid.logic

import androidx.lifecycle.liveData
import com.mtj.wanandroid.logic.model.OfficialAccountBean
import com.mtj.wanandroid.logic.network.OfficialAccountNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/15
 */
object Repository {

    /**
     * 获取公众号信息
     */
    fun getOfficialAccount() = fire(Dispatchers.IO) {
        val response = OfficialAccountNetwork.getOfficialAccount()
        if (response.errorCode == 0) {
            val officialAccount = response.data
            Result.success(officialAccount)
        } else {
            Result.failure(RuntimeException("response status is ${response.errorCode}"))
        }
    }


    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }
}