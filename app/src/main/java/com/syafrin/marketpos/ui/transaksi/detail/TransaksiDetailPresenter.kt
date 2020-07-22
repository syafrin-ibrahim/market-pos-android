package com.syafrin.marketpos.ui.transaksi.detail

import android.telecom.Call
import com.syafrin.marketpos.data.model.transaction.detail.ResponseTransactionDetail
import com.syafrin.marketpos.network.ApiService
import retrofit2.Callback
import retrofit2.Response

class TransaksiDetailPresenter(val view:TransaksiDetailContract.View): TransaksiDetailContract.Presenter {


    init{
        view.initFragment()
    }

    override fun getTransactionByInvoice(invoice: String) {
        view.onLoading(true)
        ApiService.endPoint.getTransaksiInvoice(invoice).enqueue(object : Callback<ResponseTransactionDetail> {

            override fun onFailure(call: retrofit2.Call<ResponseTransactionDetail>, t: Throwable) {
                 view.onLoading(false)
            }

            override fun onResponse(
                call: retrofit2.Call<ResponseTransactionDetail>,
                response: Response<ResponseTransactionDetail>
            ) {
                view.onLoading(false)
                if(response.isSuccessful){
                    val responseTransactionDetail: ResponseTransactionDetail? = response.body()
                    view.onResult(responseTransactionDetail!!)
                }
            }

        })
    }

}