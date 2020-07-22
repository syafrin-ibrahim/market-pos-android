package com.syafrin.marketpos.ui.agen.update

import com.syafrin.marketpos.data.model.agen.ResponseAgenDetail
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import java.io.File

interface AgenUpdateContract {
    interface Presenter{
        fun getDetail(kd_agen: Long)
        fun updateAgen(kd_agen: Long, nama_toko: String, nama_pemilik: String, alamat: String,
                       latitude: String, longitude: String,
                       img_toko: File? )
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onDetailResult(responseAgenDetail: ResponseAgenDetail)
        fun onUpdateResult(responseAgenUpdate: ResponseAgenUpdate)
        fun showMessage(msg: String )
    }
}