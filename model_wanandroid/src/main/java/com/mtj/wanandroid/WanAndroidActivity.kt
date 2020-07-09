package com.mtj.wanandroid

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.listener.OnLoadMoreListener
import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity
import com.mtj.wanandroid.demo.loadmore.LoadMoreAdapter
import com.mtj.wanandroid.demo.loadmore.LoadMoreBean
import com.mtj.wanandroid.ui.official_account.OfficialAccountFragment
import kotlinx.android.synthetic.main.activity_wanandroid.*

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/5/8
 */
@Route(path = ActivityHolder.WAN_ANDROID)
class WanAndroidActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_wanandroid
    }


    override fun afterBinder() {
        super.afterBinder()
        setSwipeBackEnable(false)

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fragment_layout, OfficialAccountFragment())
        beginTransaction.commit()
    }

}