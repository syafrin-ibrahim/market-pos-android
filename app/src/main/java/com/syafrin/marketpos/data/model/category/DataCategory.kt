package com.syafrin.marketpos.data.model.category

import com.google.gson.annotations.SerializedName

data class DataCategory(
    @SerializedName("kd_kategori")val kd_kategori: Long?,
    @SerializedName("kategori")val kategori: String?,
    @SerializedName("image")val img_kategori: String?

)