package com.syafrin.marketpos.ui.cart

import com.syafrin.marketpos.data.model.cart.ResponseCartList
import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate

interface CartContract {

    interface Presenter{
        fun getCart(username: String)
        fun deleteCart(username: String)
        fun deteteItemCart(kd_keranjang: Long)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoadingCart(loading: Boolean)
        fun onResultCart(responseCartList: ResponseCartList)
        fun showMessage(message: String)

        fun onResultDeleteCart(responseCartUpdate: ResponseCartUpdate)
        fun showDialog()
    }
}