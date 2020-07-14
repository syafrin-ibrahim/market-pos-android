package com.syafrin.marketpos.ui.login

import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.data.model.DataLogin
import com.syafrin.marketpos.data.model.ResponseLogin

interface LoginContract{

    interface Presenter{
        fun doLogin(username: String, password: String)
        fun setPrefs(prefManager: PrefsManager, dataLogin: DataLogin )
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(ResponseLogin: ResponseLogin)
        fun showMessage(msg: String)
    }
}