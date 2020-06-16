package com.mtj.wanandroid.ui.official_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.mtj.wanandroid.R
import kotlinx.android.synthetic.main.fragment_offical_account.*

/**
 * @author  孟腾蛟
 * @Description
 * @date 2020/6/14
 */
class OfficialAccountFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(OfficialAccountViewModel::class.java)
    }

    private lateinit var adapter: OfficialAccountAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offical_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = OfficialAccountAdapter(R.layout.item_official_account, viewModel.officialAccountList)
        recyclerView.adapter = adapter
        btnGetData.setOnClickListener {
            viewModel.getOfficialAccount()
        }

        viewModel.officialAccountLiveData.observe(this, Observer { result ->
            val officialAccount = result.getOrNull()
            if (officialAccount != null) {
                viewModel.officialAccountList.clear()
                viewModel.officialAccountList.addAll(officialAccount)
                adapter.notifyDataSetChanged()
            } else {
                ToastUtils.showShort("暂无数据")
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}