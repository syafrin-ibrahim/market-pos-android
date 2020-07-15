package com.syafrin.marketpos.ui.agen

import com.syafrin.marketpos.data.model.ResponseAgen
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgenPresenter(var view: AgenContract.View): AgenContract.Presenter {

    init{
        view.initActivity()
        view.initListener()
    }

    override fun getAgen() {
        view.onLoadingAgen(true)
        ApiService.endPoint.getAgen().enqueue(object : Callback<ResponseAgen>{
            override fun onFailure(call: Call<ResponseAgen>, t: Throwable) {
                view.onLoadingAgen(false)

            }

            override fun onResponse(call: Call<ResponseAgen>, response: Response<ResponseAgen>) {
                view.onLoadingAgen(false)
                if(response.isSuccessful){
                    val ResponseAgen: ResponseAgen? = response.body()
                    view.onResultAgen(ResponseAgen!!)
                }
            }

        })
    }

}