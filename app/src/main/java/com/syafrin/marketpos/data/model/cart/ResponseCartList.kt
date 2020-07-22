package com.syafrin.marketpos.data.model.cart

import com.google.gson.annotations.SerializedName

data class ResponseCartList(
    @SerializedName("status")val status: String?,
    @SerializedName("message")val message: String?,
    @SerializedName("data")val dataCart: List<DataCart>
)