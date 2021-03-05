package com.mtj.wanandroid.ui.fragment_switch

import android.view.View
import com.mtj.common.base.BaseFragment
import com.mtj.wanandroid.R
import kotlinx.android.synthetic.main.fragment3.*

class Fragment3 :BaseFragment() {
    lateinit var listenter: OnClickListener

    override fun setView(): Int = R.layout.fragment3

    override fun initView(view: View) {
        btnUp.setOnClickListener {
            listenter.previous()
        }
        btnDown.setOnClickListener {
            listenter.next()
        }
    }

    override fun initData() {
    }

    fun setListener(listenter: OnClickListener) {
        this.listenter = listenter
    }
}