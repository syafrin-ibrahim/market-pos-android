package com.syafrin.marketpos.data.model.agen

import com.google.gson.annotations.SerializedName

data class ResponseAgenUpdate(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val msg: String
)