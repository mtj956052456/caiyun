package com.mtj.wanandroid.demo.loadmore

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mtj.wanandroid.R

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/20
 */
class LoadMoreAdapter(layoutResId: Int) : BaseQuickAdapter<LoadMoreBean, BaseViewHolder>(layoutResId), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: LoadMoreBean) {
        holder.setText(R.id.tweetText,item.text)
    }

}