package com.syafrin.marketpos.ui.produk

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
import com.syafrin.marketpos.data.model.transaction.detail.DataDetail


import kotlinx.android.synthetic.main.adapter_agen.view.*
import kotlinx.android.synthetic.main.adapter_category.view.*
import kotlinx.android.synthetic.main.adapter_transaksi_detail.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class CategoriAdapter(val context: Context, var categori: ArrayList<DataCategory>,
                      val clickListener:(DataCategory, Int)-> Unit): RecyclerView.Adapter<CategoriAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
        )

    override fun getItemCount()= categori.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(categori[position])
        GlideHelper.setImage(context, categori[position].img_kategori!!, holder.view.imvImage )
        holder.view.relCategory.setOnClickListener {
            Constant.CATEGORY_ID = categori[position].kd_kategori!!
            clickListener(categori[position], position)
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(category: DataCategory){
                view.txvCategory.text= category.kategori
        }
    }

    fun setData(newCategory: List<DataCategory>){
         categori.clear()
         categori.addAll(newCategory)
        notifyDataSetChanged()
    }

}