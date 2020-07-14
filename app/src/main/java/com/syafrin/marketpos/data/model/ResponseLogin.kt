package com.syafrin.marketpos.data.model

import com.google.gson.annotations.SerializedName
import com.syafrin.marketpos.data.model.DataLogin

data class ResponseLogin(
    @SerializedName("status") val status : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("pegawai") val pegawai : DataLogin?
    )