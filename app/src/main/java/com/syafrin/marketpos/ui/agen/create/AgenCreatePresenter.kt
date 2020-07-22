package com.syafrin.marketpos.ui.agen.create

import android.content.ContentValues.TAG
import android.util.Log
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import com.syafrin.marketpos.network.ApiService

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File
import retrofit2.Callback
import retrofit2.Response

class AgenCreatePresenter(val view: AgenCreateContract.View): AgenCreateContract.Presenter{

   init{
        view.initActivity()
        view.initListener()
       view.onLoading(false)
   }

    override fun storeAgen(
        nama_toko: String,
        nama_pemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        img_toko: File
    ) {
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), img_toko)
        val multipartBody: MultipartBody.Part? = MultipartBody.Part.createFormData("img_toko", img_toko.name, requestBody)
        Log.d(TAG, "filename " + img_toko.name);
        view.onLoading(true)

        ApiService.endPoint.storeAgen(nama_toko, nama_pemilik, alamat, latitude,longitude, multipartBody!!)
            .enqueue(object : Callback<ResponseAgenUpdate>{
                override fun onFailure(call: Call<ResponseAgenUpdate>, t: Throwable) {
                    view.onLoading(false)
                    Log.d(TAG, "Error " + t.message)
                }

                override fun onResponse(
                    call: Call<ResponseAgenUpdate>,
                    response: Response<ResponseAgenUpdate>
                ) {
                    view.onLoading(false)
                    if(response.isSuccessful){
                        val responseAgenUpdate: ResponseAgenUpdate? = response.body()
                        view.onResult(responseAgenUpdate!!)
                    }

                }

            })
    }

}