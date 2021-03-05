package com.mtj.android

import com.mtj.common.base.ActivityHolder
import com.mtj.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.contracts.*

class MainActivity : BaseActivity() {

    override fun setView(): Int {
        return R.layout.activity_main
    }

    override fun afterCreation() {
        super.afterCreation()
        btn_login.setOnClickListener {
            intoActivity(ActivityHolder.LOGIN)
        }
        btn_cy.setOnClickListener {
            intoActivity(ActivityHolder.CAIYUN)
        }
    }

    private inline fun getData() {
        contract {
           setData();
        }
    }

    private inline fun setData() {

    }

    @ExperimentalContracts
    class CustomContractBuilder : ContractBuilder {
        override fun <R> callsInPlace(lambda: Function<R>, kind: InvocationKind): CallsInPlace {

        }

        override fun returns(): Returns {
        }

        override fun returns(value: Any?): Returns {
        }

        override fun returnsNotNull(): ReturnsNotNull {
        }

    }


}