package com.mtj.wanandroid.demo.loadmore

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtj.wanandroid.R
import kotlinx.android.synthetic.main.activity_wanandroid.*

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/20
 */
class Test {


//    <androidx.swiperefreshlayout.widget.SwipeRefreshLayout
//        android:id="@+id/swipe"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent">
//
//            <androidx.recyclerview.widget.RecyclerView
//            android:id="@+id/recyclerView"
//            android:layout_width="match_parent"
//            android:layout_height="match_parent" />
//
//    </androidx.swiperefreshlayout.widget.SwipeRefreshLayout>



    private val mAdapter: LoadMoreAdapter by lazy {
        LoadMoreAdapter(R.layout.layout_animation)
    }

//    override fun afterBinder() {
//        super.afterBinder()
//        setSwipeBackEnable(false)
//
//        recyclerView.apply {
//            adapter = mAdapter
//            initLoadMore()
//            layoutManager = LinearLayoutManager(this@WanAndroidActivity)
//        }
//        swipe.setColorSchemeColors(Color.rgb(47, 223, 189))
//        swipe.setOnRefreshListener {
//            refresh()
//        }
//    }

    /**
     * 初始化加载更多
     */
    private fun initLoadMore() {
        mAdapter.loadMoreModule.setOnLoadMoreListener(OnLoadMoreListener { loadMore() })
        mAdapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
    }

    internal class PageInfo {
        var page = 0
        fun nextPage() {
            page++
        }

        fun reset() {
            page = 0
        }

        fun isFirstPage() = page == 0

    }

    private val pageInfo: PageInfo = PageInfo()

    /**
     * 刷新
     */
    private fun refresh() {
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
        mAdapter.loadMoreModule.isEnableLoadMore = false
        // 下拉刷新，需要重置页数
        pageInfo.reset()
        request()
    }

    /**
     * 加载更多
     */
    private fun loadMore() {
        request()
    }

    private val PAGE_SIZE = 5

    /**
     * 模拟加载数据的类，不用特别关注
     */
    internal class Request(private val mPage: Int, private val mCallBack: RequestCallBack) : Thread() {

        companion object {
            private var mFirstPageNoMore = false
            private var mFirstError = true
        }

        private val mHandler: Handler by lazy {
            Handler(Looper.getMainLooper())
        }

        override fun run() {
            try {
                sleep(800)
            } catch (e: InterruptedException) {
            }
            if (mPage == 2 && mFirstError) {
                mFirstError = false
                mHandler.post { mCallBack.fail(RuntimeException("fail")) }
            } else {
                var size: Int = 5
                if (mPage == 1) {
                    if (mFirstPageNoMore) {
                        size = 1
                    }
                    mFirstPageNoMore = !mFirstPageNoMore
                    if (!mFirstError) {
                        mFirstError = true
                    }
                } else if (mPage == 4) {
                    size = 1
                }
                val dataSize = size
                mHandler.post { mCallBack.success(getSampleData(dataSize)) }
            }
        }

        private fun getSampleData(dataSize: Int): List<LoadMoreBean> {
            val list = mutableListOf<LoadMoreBean>()
            for (i in 0 until dataSize) {
                list.add(LoadMoreBean((0..10).random().toString()))
            }
            return list
        }
    }

    /**
     * 请求数据
     */
    private fun request() {
//        Request(pageInfo.page, object : RequestCallBack {
//            override fun success(data: List<LoadMoreBean>) {
//                //接口请求返回的数据后的逻辑
//                swipe.isRefreshing = false
//                mAdapter.loadMoreModule.isEnableLoadMore = true
//                if (pageInfo.isFirstPage()) {
//                    //如果是加载的第一页数据，用 setData()
//                    mAdapter.setList(data)
//                } else {
//                    //不是第一页，则用add
//                    mAdapter.addData(data)
//                }
//                if (data.size < PAGE_SIZE) {
//                    //如果不够一页,显示没有更多数据布局
//                    mAdapter.loadMoreModule.loadMoreEnd()
//                } else {
//                    mAdapter.loadMoreModule.loadMoreComplete()
//                }
//                // page加一
//                pageInfo.nextPage()
//            }
//
//            override fun fail(e: Exception) {
//                swipe.isRefreshing = false
//                mAdapter.loadMoreModule.isEnableLoadMore = true
//                mAdapter.loadMoreModule.loadMoreFail()
//            }
//        }).start()
    }

    internal interface RequestCallBack {
        /**
         * 模拟加载成功
         *
         * @param data 数据
         */
        fun success(data: List<LoadMoreBean>)

        /**
         * 模拟加载失败
         *
         * @param e 错误信息
         */
        fun fail(e: Exception)
    }

}