package com.syafrin.marketpos.ui.agen

import com.syafrin.marketpos.data.model.agen.ResponseAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgenPresenter(var view: AgenContract.View): AgenContract.Presenter {

    init{
        view.initActivity()
        view.initListener()
    }

    override fun deleteAgen(kd_agen: Long) {
            view.onLoadingAgen(true)
            ApiService.endPoint.DeleteAgen(kd_agen).enqueue(object : Callback<ResponseAgenUpdate>{
                override fun onFailure(call: Call<ResponseAgenUpdate>, t: Throwable) {
                    view.onLoadingAgen(false)
                }

                override fun onResponse(
                    call: Call<ResponseAgenUpdate>,
                    response: Response<ResponseAgenUpdate>
                ) {
                    view.onLoadingAgen(false)
                    if(response.isSuccessful){
                        val responseAgenUpdate: ResponseAgenUpdate? = response.body()
                        view.onResultDeleteAgen(responseAgenUpdate!!)
                    }
                }

            })
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