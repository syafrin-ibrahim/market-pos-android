package com.syafrin.marketpos.network

import com.syafrin.marketpos.data.model.agen.ResponseAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgenDetail
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import com.syafrin.marketpos.data.model.cart.ResponseCartList
import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate
import com.syafrin.marketpos.data.model.cart.ResponseCheckout
import com.syafrin.marketpos.data.model.category.ResponseCategoryList
import com.syafrin.marketpos.data.model.login.ResponseLogin
import com.syafrin.marketpos.data.model.product.ResponseProductList
import com.syafrin.marketpos.data.model.transaction.ResponseTransactionList
import com.syafrin.marketpos.data.model.transaction.detail.ResponseTransactionDetail
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {
    @FormUrlEncoded
    @POST("login_employe")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ):Call<ResponseLogin>

    @GET("agent")
    fun getAgen():Call<ResponseAgen>


    @GET("agent/{kd_agen}")
    fun getAgenDetail(
        @Path("kd_agen")kd_agen: Long
    ):Call<ResponseAgenDetail>

    @DELETE("agent/{kd_agen}")
    fun DeleteAgen(
        @Path("kd_agen")kd_agen: Long
    ):Call<ResponseAgenUpdate>


    @Multipart
    @POST("agent/{kd_agen}")
    fun updateAgen(
        @Path("kd_agen")kd_agen: Long,
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part img_toko: MultipartBody.Part,
        @Query("_method") _method: String
    ):Call<ResponseAgenUpdate>


    @Multipart
    @POST("agent")
    fun storeAgen(
        @Query("nama_toko") nama_toko: String,
        @Query("nama_pemilik") nama_pemilik: String,
        @Query("alamat") alamat: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part img_toko: MultipartBody.Part
    ):Call<ResponseAgenUpdate>

    @POST("get_transaction")
    fun getTransaksi(
        @Query("username") username: String
    ):Call<ResponseTransactionList>


    @POST("get_detail_transaction")
    fun getTransaksiInvoice(
        @Query("no_faktur")no_faktur: String
    ):Call<ResponseTransactionDetail>

    @FormUrlEncoded
    @POST("get_cart")
    fun getCart(
        @Field("username") username: String
    ):Call<ResponseCartList>

    @FormUrlEncoded
    @POST("add_cart")
    fun addCart(
        @Field("username") username: String,
        @Field("kd_produk") kd_produk: Long,
        @Field("jumlah") jumlah: Long
    ):Call<ResponseCartUpdate>

    @GET("get_category")
    fun getCategory(): Call<ResponseCategoryList>

    @FormUrlEncoded
    @POST("get_product")
    fun getProductByCategory(
        @Field("kd_kategori")kd_kategori: Long
    ):Call<ResponseProductList>

    @FormUrlEncoded
    @POST("delete_item_cart")
    fun deleteItemCart(
        @Field("kd_keranjang")kd_keranjang: Long
    ):Call<ResponseCartUpdate>

    @FormUrlEncoded
    @POST("delete_cart")
    fun deleteCart(
        @Field("username")username: String
    ):Call<ResponseCartUpdate>

    @FormUrlEncoded
    @GET("search_agent")
    fun searchAgent(
        @Query("keyword") keyword: String
    ):Call<ResponseAgen>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("username")username: String,
        @Field("kd_agen")kd_agen: Long
    ):Call<ResponseCheckout>
}