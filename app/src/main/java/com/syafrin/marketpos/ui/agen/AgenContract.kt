package com.syafrin.marketpos.ui.agen

import com.syafrin.marketpos.data.model.ResponseAgen

interface AgenContract {

    interface Presenter{
        fun getAgen()
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoadingAgen(loading: Boolean)
        fun onResultAgen(responseAgen: ResponseAgen)
        fun showMessage()
    }
}