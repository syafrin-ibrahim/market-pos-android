package com.syafrin.marketpos.data.model.product

import com.google.gson.annotations.SerializedName

data class DataProduct(
    @SerializedName("kd_produk")val kd_produk: Long?,
    @SerializedName("kd_kategori")val kd_kategori: Long?,
    @SerializedName("nama_produk")val nama_produk: String?,
    @SerializedName("harga")val harga: String?,
    @SerializedName("harga_rupiah")val harga_rupiah: String?,
    @SerializedName("img_produk")val img_produk: String?,
    @SerializedName("stok")val stok: String?,
    @SerializedName("kategori")val kategori: String?
)