package com.syafrin.marketpos.ui.home

class MainPresenter(val view: MainContract.View) {
    init{
        view.initListener()
    }
}