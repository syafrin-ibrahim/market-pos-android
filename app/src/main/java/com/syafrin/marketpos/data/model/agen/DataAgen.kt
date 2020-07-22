package com.syafrin.marketpos.data.model.agen

import com.google.gson.annotations.SerializedName

data class DataAgen(
    @SerializedName("kd_agen") val kd_agen: Long?,
    @SerializedName("nama_toko") val nama_toko: String?,
    @SerializedName("nama_pemilik") val nama_pemilik: String?,
    @SerializedName("alamat") val alamat: String?,
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?,
    @SerializedName("img_toko") val img_toko: String?

)