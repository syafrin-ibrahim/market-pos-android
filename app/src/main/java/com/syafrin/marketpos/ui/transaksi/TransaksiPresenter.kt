package com.syafrin.marketpos.ui.transaksi

import com.syafrin.marketpos.data.model.transaction.ResponseTransactionList
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransaksiPresenter(val view: TransaksiContract.View): TransaksiContract.Presenter{

    init{
        view.initFragment()

    }

    override fun getTransactionByUsername(username: String) {
            view.onLoading(true)
        ApiService.endPoint.getTransaksi(username).enqueue(object : Callback<ResponseTransactionList>{
            override fun onFailure(call: Call<ResponseTransactionList>, t: Throwable) {
                 view.onLoading(false)
            }

            override fun onResponse(
                call: Call<ResponseTransactionList>,
                response: Response<ResponseTransactionList>
            ) {
                view.onLoading(false)
                if(response.isSuccessful){
                    val responseTransactionList: ResponseTransactionList? = response.body()
                    view.onResult( responseTransactionList!!)
                }
            }

        })
    }

}