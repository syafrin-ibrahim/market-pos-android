package com.syafrin.marketpos.ui.user

import com.syafrin.marketpos.data.database.PrefsManager

class UserPresenter(val view: UserContract.View): UserContract.Presenter {

    init{
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefsManager: PrefsManager) {
        if(prefsManager.prefIslogin)view.onResultLogin(prefsManager)
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showMessage("Berhasil Keluar")
        view.onResultLogOut()
    }
}