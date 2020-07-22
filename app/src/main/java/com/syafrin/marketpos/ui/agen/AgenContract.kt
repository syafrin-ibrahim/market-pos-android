package com.syafrin.marketpos.ui.agen

import com.syafrin.marketpos.data.model.agen.DataAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate

interface AgenContract {

    interface Presenter{
        fun getAgen()
        fun deleteAgen(kd_agen: Long)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoadingAgen(loading: Boolean)
        fun onResultAgen(responseAgen: ResponseAgen)
        fun onResultDeleteAgen(responseAgenUpdate: ResponseAgenUpdate)
        fun showConfirmDelete(dataAgen: DataAgen, position: Int)
        fun showDialogStore(dataAgen: DataAgen, position: Int)
        fun showMessage(msg: String)
    }
}