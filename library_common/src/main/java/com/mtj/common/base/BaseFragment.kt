package com.mtj.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setView(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    override fun onResume() {
        super.onResume()
    }

    abstract fun setView(): Int

    /**
     * 初始化本地数据
     */
    abstract fun initView(view: View)

    /**
     * 在onCreate中请求服务
     */
    abstract fun initData()


}