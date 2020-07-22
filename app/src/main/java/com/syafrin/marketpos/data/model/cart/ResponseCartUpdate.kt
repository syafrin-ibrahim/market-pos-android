package com.syafrin.marketpos.data.model.cart

import com.google.gson.annotations.SerializedName

data class ResponseCartUpdate(
    @SerializedName("status")val status : Boolean,
    @SerializedName("message")val message : String
)