package com.mtj.wanandroid.ui.fragment_switch

import android.view.View
import com.mtj.common.base.BaseFragment
import com.mtj.wanandroid.R
import kotlinx.android.synthetic.main.fragment1.*

class Fragment1 : BaseFragment() {

    lateinit var listenter: OnClickListener

    override fun setView(): Int = R.layout.fragment1

    override fun initView(view: View) {

    }

    override fun initData() {
    }

    fun setListener(listenter: OnClickListener) {
        this.listenter = listenter
    }
}