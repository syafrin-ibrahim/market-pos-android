package com.syafrin.marketpos.data.model

import com.google.gson.annotations.SerializedName

data class DataLogin (

    @SerializedName("username") val username : String?,
    @SerializedName("password") val password : String?,
    @SerializedName("nama_pegawai") val nama_pegawai : String?,
    @SerializedName("jk") val jk : String?,
    @SerializedName("alamat") val alamat : String?,
    @SerializedName("is_active") val is_active : String?

    )
