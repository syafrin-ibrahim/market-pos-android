package com.syafrin.marketpos.ui.produk

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.lazday.poslaravel.util.GlideHelper

import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.model.category.DataCategory
import com.syafrin.marketpos.data.model.product.DataProduct
import com.syafrin.marketpos.data.model.transaction.detail.DataDetail


import kotlinx.android.synthetic.main.adapter_agen.view.*
import kotlinx.android.synthetic.main.adapter_category.view.*
import kotlinx.android.synthetic.main.adapter_category.view.imvImage
import kotlinx.android.synthetic.main.adapter_produk.view.*
import kotlinx.android.synthetic.main.adapter_transaksi_detail.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ProdukAdapter(val context: Context, var product: ArrayList<DataProduct>): RecyclerView.Adapter<ProdukAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_produk, parent, false)
        )

    override fun getItemCount()= product.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(product[position])
        GlideHelper.setImage(context, product[position].img_produk!!, holder.view.imvImage )
        holder.view.linProduct.setOnClickListener {
            Constant.PRODUCT_ID = product[position].kd_produk!!
            Constant.PRODUCT_NAME = product[position].nama_produk!!
            Constant.IS_CHANGE = true
            (context as Activity).finish()
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(product: DataProduct){
                view.txvName.text= product.nama_produk
                view.txvPrice.text= product.harga_rupiah
                view.txvStock.text= "stok tersedia (${product.stok})"

        }
    }

    fun setData(newProduct: List<DataProduct>){
         product.clear()
         product.addAll(newProduct)
        notifyDataSetChanged()
    }

}