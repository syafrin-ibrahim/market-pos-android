package com.syafrin.marketpos.data.model.agen

import com.google.gson.annotations.SerializedName
import com.syafrin.marketpos.data.model.agen.DataAgen

data class ResponseAgen(
    @SerializedName("status")val status: Boolean,
     @SerializedName("data")val dataAgen: List<DataAgen>
)