package com.syafrin.marketpos.ui.transaksi

import com.syafrin.marketpos.data.model.transaction.ResponseTransactionList

interface TransaksiContract {

    interface View{
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading:Boolean)
        fun onResult(responseTransactionList: ResponseTransactionList)
        fun onClickTransaction(invoice: String)
    }

    interface Presenter{
        fun getTransactionByUsername(username: String)

    }


}