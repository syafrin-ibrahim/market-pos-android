package com.syafrin.marketpos.data.model.agen

import com.google.gson.annotations.SerializedName
import com.syafrin.marketpos.data.model.agen.DataAgen

data class ResponseAgenDetail(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val dataAgen: DataAgen
)