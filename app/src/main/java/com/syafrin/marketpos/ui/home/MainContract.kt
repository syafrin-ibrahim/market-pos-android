package com.syafrin.marketpos.ui.home

interface MainContract {
    interface View{
        fun initListener()
        fun showMessage(msg: String)
    }
}