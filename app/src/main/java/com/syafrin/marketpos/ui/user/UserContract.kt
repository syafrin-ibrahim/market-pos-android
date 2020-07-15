package com.syafrin.marketpos.ui.user

import com.syafrin.marketpos.data.database.PrefsManager

interface UserContract {


    interface Presenter {
        fun doLogin(prefsManager: PrefsManager)
        fun doLogout(prefsManager: PrefsManager)
    }


    interface View {
        fun initActivity()
        fun initListener()
        fun onResultLogin(prefsManager: PrefsManager)
        fun onResultLogOut()
        fun showMessage(msg: String)
    }
}