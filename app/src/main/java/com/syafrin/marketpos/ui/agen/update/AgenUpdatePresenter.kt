package com.syafrin.marketpos.ui.agen.update

import com.syafrin.marketpos.data.model.agen.ResponseAgenDetail
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import com.syafrin.marketpos.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgenUpdatePresenter(val view: AgenUpdateContract.View): AgenUpdateContract.Presenter {

    init{
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }
    override fun getDetail(kd_agen: Long) {
        view.onLoading(true)
        ApiService.endPoint.getAgenDetail(kd_agen).enqueue(object: Callback<ResponseAgenDetail>{
            override fun onFailure(call: Call<ResponseAgenDetail>, t: Throwable) {
                view.onLoading(false)
            }

            override fun onResponse(
                call: Call<ResponseAgenDetail>,
                response: Response<ResponseAgenDetail>
            ) {
                view.onLoading(false)
                if(response.isSuccessful){
                     val responseAgenDetail: ResponseAgenDetail? = response.body()
                    view.onDetailResult(responseAgenDetail!!)
                }
            }

        })
    }

    override fun updateAgen(
        kd_agen: Long,
        nama_toko: String,
        nama_pemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        img_toko: File?
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part
        if(img_toko != null ){
            requestBody = RequestBody.create(MediaType.parse("image/*"), img_toko)
            multipartBody = MultipartBody.Part.createFormData("img_toko", img_toko.name, requestBody)
        }else{
            requestBody = RequestBody.create(MediaType.parse("image/*"), "")
            multipartBody = MultipartBody.Part.createFormData("img_toko", "", requestBody)
        }

        view.onLoading(true)
        ApiService.endPoint.updateAgen( kd_agen, nama_toko, nama_pemilik,
            alamat, latitude, longitude, multipartBody, "PUT").enqueue(object : Callback<ResponseAgenUpdate>{
            override fun onFailure(call: Call<ResponseAgenUpdate>, t: Throwable) {
                    view.onLoading(false)
            }

            override fun onResponse(
                call: Call<ResponseAgenUpdate>,
                response: Response<ResponseAgenUpdate>
            ) {
                view.onLoading(false)
                if(response.isSuccessful){
                    val responseAgenUpdate: ResponseAgenUpdate? = response.body()
                    view.onUpdateResult(responseAgenUpdate!!)
                }
            }

        })
    }
}