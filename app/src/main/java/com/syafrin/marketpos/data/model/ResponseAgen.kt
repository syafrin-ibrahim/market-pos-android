package com.syafrin.marketpos.data.model

import com.google.gson.annotations.SerializedName

data class ResponseAgen(
    @SerializedName("status")val status: Boolean,
     @SerializedName("data")val dataAgen: List<DataAgen>
)