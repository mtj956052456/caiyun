package com.mtj.wanandroid.ui.fragment_switch

import android.view.View
import androidx.fragment.app.Fragment
import com.mtj.common.base.BaseFragment
import com.mtj.wanandroid.R

class SwitchFragment : BaseFragment(), OnClickListener {

    var curFragment: Fragment? = null
    var fragment1: Fragment1? = null
    var fragment2: Fragment2? = null
    var fragment3: Fragment3? = null
    var curPosition = 0
    override fun setView(): Int = R.layout.fragment_switch

    override fun initView(view: View) {
        fragment1 = Fragment1()
        fragment1!!.listenter = this
        childFragmentManager.beginTransaction().add(R.id.rootFrame, fragment1!!).commit()
        curFragment = fragment1 as Fragment1
    }

    override fun initData() {
    }

    private fun setState(positon: Int) {
        curPosition = positon
        when (positon) {
            0 -> {
                if (fragment1 == null) {
                    fragment1 = Fragment1()
                    fragment1!!.listenter = this
                    childFragmentManager.beginTransaction().hide(curFragment!!).add(R.id.rootFrame, fragment1!!).commit()
                } else {
                    childFragmentManager.beginTransaction().hide(curFragment!!).show(fragment1!!).commit()
                }
                curFragment = fragment1 as Fragment1
            }
            1 -> {
                if (fragment2 == null) {
                    fragment2 = Fragment2()
                    fragment2!!.listenter = this
                    childFragmentManager.beginTransaction().hide(curFragment!!).add(R.id.rootFrame, fragment2!!).commit()
                } else {
                    childFragmentManager.beginTransaction().hide(curFragment!!).show(fragment2!!).commit()
                }
                curFragment = fragment2 as Fragment2

            }
            2 -> {
                if (fragment3 == null) {
                    fragment3 = Fragment3()
                    fragment3!!.listenter = this
                    childFragmentManager.beginTransaction().hide(curFragment!!).add(R.id.rootFrame, fragment3!!).commit()
                } else {
                    childFragmentManager.beginTransaction().hide(curFragment!!).show(fragment3!!).commit()
                }
                curFragment = fragment3 as Fragment3
            }
        }
    }

    override fun next() {
        if (curPosition < 2) curPosition++
        setState(curPosition)
    }

    override fun previous() {
        if (curPosition > 0) curPosition--
        setState(curPosition)
    }


}