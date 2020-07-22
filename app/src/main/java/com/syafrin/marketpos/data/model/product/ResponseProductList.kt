package com.syafrin.marketpos.data.model.product

import com.google.gson.annotations.SerializedName
import retrofit2.Call


data class ResponseProductList(
    @SerializedName("status")val status: String?,
    @SerializedName("data")val dataProduct: List<DataProduct>
)