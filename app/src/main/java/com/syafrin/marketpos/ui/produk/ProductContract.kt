package com.syafrin.marketpos.ui.produk

import com.syafrin.marketpos.data.model.category.ResponseCategoryList
import com.syafrin.marketpos.data.model.product.ResponseProductList

interface ProductContract {
    interface Presenter{
        fun getCategory()
        fun getProduct(kd_kategori:Long)
    }

    interface View{
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultCategory(responseCategoryList: ResponseCategoryList)
        fun onResultProduct(responseProductList: ResponseProductList)
    }
}