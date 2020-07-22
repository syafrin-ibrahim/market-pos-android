package com.syafrin.marketpos.ui.login

import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.data.model.login.DataLogin
import com.syafrin.marketpos.data.model.login.ResponseLogin
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter{
    init{
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }
    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endPoint.loginUser(username,password)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    view.onLoading(false)

                }

                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    view.onLoading(false)
                    if(response.isSuccessful){
                        val responseLogin: ResponseLogin? = response.body()
                        view.showMessage(responseLogin!!.message)

                        if(responseLogin!!.status){
                            view.onResult(responseLogin)
                        }

                    }
                }

            })
    }

    override fun setPrefs(prefManager: PrefsManager, dataLogin: DataLogin) {
        prefManager.prefIslogin = true
        prefManager.prefUsername = dataLogin.username!!
        prefManager.prefsAlamat = dataLogin.alamat!!
        prefManager.prefsIsAktif = dataLogin.is_active!!
        prefManager.prefsJk = dataLogin.jk!!
        prefManager.prefsPassword = dataLogin.password!!
        prefManager.prefsNamaPegawai = dataLogin.nama_pegawai!!
    }

}