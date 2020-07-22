package com.syafrin.marketpos.ui.agen.create

import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import java.io.File

interface AgenCreateContract {

    interface Presenter {
        fun storeAgen(
            nama_toko: String, nama_pemilik: String, alamat: String,
            latitude: String, longitude: String,
            img_toko: File
        )
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseAgenUpdate: ResponseAgenUpdate)
        fun showMessage(msg: String)
    }
}