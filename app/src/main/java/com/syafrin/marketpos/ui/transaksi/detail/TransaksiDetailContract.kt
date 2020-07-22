package com.syafrin.marketpos.ui.transaksi.detail

import com.syafrin.marketpos.data.model.transaction.detail.ResponseTransactionDetail

interface TransaksiDetailContract {
    interface Presenter{
        fun getTransactionByInvoice(invoice:String)
    }

    interface View{
        fun initFragment()
        fun initListener(view:android.view.View)
        fun onLoading(loading:Boolean)
        fun onResult(responseTransactionDetail: ResponseTransactionDetail)

    }
}