package com.syafrin.marketpos.ui.transaksi.detail

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

import android.view.View
import android.view.ViewGroup
import com.lazday.poslaravel.util.GlideHelper

import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.model.transaction.detail.DataDetail


import kotlinx.android.synthetic.main.adapter_agen.view.*
import kotlinx.android.synthetic.main.adapter_transaksi_detail.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class TransaksiDetailAdapter(val context: Context, var detail: ArrayList<DataDetail>): RecyclerView.Adapter<TransaksiDetailAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaksi_detail, parent, false)
        )

    override fun getItemCount()= detail.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(detail[position])
        GlideHelper.setImage(context, detail[position].img_produk!!, holder.view.imgProduct )

    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(detail: DataDetail){
                view.txtCategori.text = detail.kategori
                view.txtNameProduk.text = detail.nama_produk
                view.txtPrice.text = "${detail.harga_rupiah} x ${detail.jumlah}"
                val total: Double = detail!!.jumlah!!.toDouble() * detail!!.harga!!.toDouble()
                val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
                view.txtTotal.text = "Rp. ${totalRupiah}"
        }
    }

    fun setData(newDetail: List<DataDetail>){
         detail.clear()
         detail.addAll(newDetail)
        notifyDataSetChanged()
    }

}