package com.syafrin.marketpos.ui.home

interface MainContract {
    interface View{
        fun initActivity()
        fun showMessage(msg: String)
    }
}