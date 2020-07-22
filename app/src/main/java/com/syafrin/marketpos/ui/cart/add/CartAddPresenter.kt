package com.syafrin.marketpos.ui.cart.add

import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAddPresenter(val view: CartAddContract.View): CartAddContract.Presenter {

    init{
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }
    override fun addCart(username: String, kd_produk: Long, jumlah: Long) {
        view.onLoading(true)
        ApiService.endPoint.addCart(username, kd_produk, jumlah).enqueue(object: Callback<ResponseCartUpdate>{
            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {
                view.onLoading(false)
            }

            override fun onResponse(
                call: Call<ResponseCartUpdate>,
                response: Response<ResponseCartUpdate>
            ) {
                view.onLoading(false)
                if(response.isSuccessful){
                    val responseCartUpdate: ResponseCartUpdate? = response.body()
                    view.showMessage(responseCartUpdate!!.message)
                    view.onResult(responseCartUpdate!!)
                }
            }

        })
    }



}