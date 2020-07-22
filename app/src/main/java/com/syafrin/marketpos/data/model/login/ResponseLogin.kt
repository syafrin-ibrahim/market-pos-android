package com.syafrin.marketpos.data.model.login

import com.google.gson.annotations.SerializedName
import com.syafrin.marketpos.data.model.login.DataLogin

data class ResponseLogin(
    @SerializedName("status") val status : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("pegawai") val pegawai : DataLogin?
    )