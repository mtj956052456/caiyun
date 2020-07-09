package com.mtj.wanandroid.ui.official_account

import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mtj.wanandroid.R
import com.mtj.wanandroid.logic.model.OfficialAccountBean

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/15
 */
class OfficialAccountAdapter(layoutResId: Int, data: MutableList<OfficialAccountBean>?) : BaseQuickAdapter<OfficialAccountBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: OfficialAccountBean) {
        holder.setText(R.id.tvName, item.name)
    }

}