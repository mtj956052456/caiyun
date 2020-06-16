package com.mtj.wanandroid.ui.official_account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mtj.wanandroid.logic.Repository
import com.mtj.wanandroid.logic.model.OfficialAccountBean
import java.util.*

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/15
 */
class OfficialAccountViewModel : ViewModel() {

    private val officialAccountData = MutableLiveData<String>()

    val officialAccountList = ArrayList<OfficialAccountBean>()

    val officialAccountLiveData = Transformations.switchMap(officialAccountData) {
        Repository.getOfficialAccount()
    }

    fun getOfficialAccount() {
        officialAccountData.value = officialAccountData.value
    }

}