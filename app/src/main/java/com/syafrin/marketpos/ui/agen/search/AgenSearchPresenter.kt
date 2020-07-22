package com.syafrin.marketpos.ui.agen.search

import com.syafrin.marketpos.data.model.agen.ResponseAgen
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgenSearchPresenter(val view: AgenSearchContract.View): AgenSearchContract.Presenter {

    init{
            view.initActivity()
            view.iniListener()
    }

    override fun getAgent() {
            view.onLoadingAgent(true)
            ApiService.endPoint.getAgen().enqueue(object: Callback<ResponseAgen>{
                override fun onFailure(call: Call<ResponseAgen>, t: Throwable) {
                    view.onLoadingAgent(false)
                }

                override fun onResponse(
                    call: Call<ResponseAgen>,
                    response: Response<ResponseAgen>
                ) {
                    view.onLoadingAgent(false)
                    if(response.isSuccessful){
                        val responseAgen: ResponseAgen? = response.body()
                        view.onResultAgent(responseAgen!!)
                    }
                }

            })
    }

    override fun searchAgent(keyword: String) {
        view.onLoadingAgent(true)
        ApiService.endPoint.searchAgent(keyword).enqueue(object: Callback<ResponseAgen>{
            override fun onFailure(call: Call<ResponseAgen>, t: Throwable) {
                view.onLoadingAgent(false)
            }

            override fun onResponse(call: Call<ResponseAgen>, response: Response<ResponseAgen>) {
                view.onLoadingAgent(false)
                if(response.isSuccessful){
                    val responseAgen: ResponseAgen? = response.body()
                    view.onResultAgent(responseAgen!!)
                }
            }

        })
    }






}