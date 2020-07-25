package com.syafrin.marketpos.ui.cart

import com.syafrin.marketpos.data.model.cart.ResponseCartList
import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate
import com.syafrin.marketpos.data.model.cart.ResponseCheckout

interface CartContract {

    interface Presenter{
        fun getCart(username: String)
        fun deleteCart(username: String)
        fun deteteItemCart(kd_keranjang: Long)

        fun checkout(username: String, kd_agen: Long)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoadingCart(loading: Boolean)
        fun onResultCart(responseCartList: ResponseCartList)
        fun showMessage(message: String)

        fun onResultDeleteCart(responseCartUpdate: ResponseCartUpdate)
        fun showDialog()

        fun onLoadingCheckout(loading: Boolean)
        fun onResultCheckout(responseCheckout: ResponseCheckout)
    }
}