package com.syafrin.marketpos.data.model.category

import com.google.gson.annotations.SerializedName
import com.syafrin.marketpos.data.model.product.DataProduct

data class ResponseCategoryList(
    @SerializedName("status")val status: String?,
    @SerializedName("data")val dataCategory: List<DataCategory>
)