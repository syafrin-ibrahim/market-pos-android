package com.syafrin.marketpos.ui.agen.search

import com.syafrin.marketpos.data.model.agen.ResponseAgen

interface AgenSearchContract {

    interface Presenter{
            fun getAgent()
            fun searchAgent(keyword: String)
    }

    interface View{
        fun initActivity()
        fun iniListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseAgen: ResponseAgen)
    }
}