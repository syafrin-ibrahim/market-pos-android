package com.syafrin.marketpos.ui.cart

import com.syafrin.marketpos.data.model.cart.ResponseCartList
import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate
import com.syafrin.marketpos.data.model.cart.ResponseCheckout
import com.syafrin.marketpos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartPresenter (val view: CartContract.View): CartContract.Presenter {


    override fun deleteCart(username: String) {
            ApiService.endPoint.deleteCart(username).enqueue(object: Callback<ResponseCartUpdate>{
                override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseCartUpdate>,
                    response: Response<ResponseCartUpdate>
                ) {
                    if(response.isSuccessful){
                        val responseCartUpdate: ResponseCartUpdate? = response.body()
                        view.onResultDeleteCart(responseCartUpdate!!)
                        view.showMessage(responseCartUpdate.message)
                    }
                }

            })
    }

    override fun deteteItemCart(kd_keranjang: Long) {
        ApiService.endPoint.deleteItemCart(kd_keranjang).enqueue(object: Callback<ResponseCartUpdate>{
            override fun onFailure(call: Call<ResponseCartUpdate>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseCartUpdate>,
                response: Response<ResponseCartUpdate>
            ) {
                val responseCartUpdate: ResponseCartUpdate? = response.body()
                view.onResultDeleteCart(responseCartUpdate!!)
                view.showMessage(responseCartUpdate.message)
            }

        })
    }

    init{
        view.initActivity()
        view.initListener()
    }

    override fun getCart(username: String) {
        view.onLoadingCart(true)
        ApiService.endPoint.getCart(username).enqueue(object: Callback<ResponseCartList>{
            override fun onFailure(call: Call<ResponseCartList>, t: Throwable) {
                view.onLoadingCart(false)
            }

            override fun onResponse(
                call: Call<ResponseCartList>,
                response: Response<ResponseCartList>
            ) {
                view.onLoadingCart(false)
                if(response.isSuccessful){
                    val responseCartList: ResponseCartList? = response.body()
                    view.onResultCart(responseCartList!!)
                }

            }

        })
    }

    override fun checkout(username: String, kd_agen: Long) {
            view.onLoadingCheckout(true)
            ApiService.endPoint.checkout(username, kd_agen).enqueue(object: Callback<ResponseCheckout>{
                override fun onFailure(call: Call<ResponseCheckout>, t: Throwable) {
                    view.onLoadingCheckout(false)
                }

                override fun onResponse(
                    call: Call<ResponseCheckout>,
                    response: Response<ResponseCheckout>
                ) {
                    view.onLoadingCheckout(false)
                    if(response.isSuccessful){
                        val responseCheckout: ResponseCheckout? = response.body()
                        view.onResultCheckout(responseCheckout!!)
                        view.showMessage(responseCheckout.message)
                    }
                }

            })
    }

}