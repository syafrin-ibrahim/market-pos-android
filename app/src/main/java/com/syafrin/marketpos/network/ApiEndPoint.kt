package com.syafrin.marketpos.network

import com.syafrin.marketpos.data.model.ResponseAgen
import com.syafrin.marketpos.data.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {
    @FormUrlEncoded
    @POST("login_employe")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ):Call<ResponseLogin>

    @GET("agent")
    fun getAgen():Call<ResponseAgen>

}