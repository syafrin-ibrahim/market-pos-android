package com.syafrin.marketpos.ui.transaksi

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

import android.view.View
import android.view.ViewGroup

import com.syafrin.marketpos.R

import com.syafrin.marketpos.data.model.transaction.DataTransaction
import kotlinx.android.synthetic.main.adapter_agen.view.*
import kotlinx.android.synthetic.main.adapter_transksi.view.*

class TransaksiAdapter(val context: Context, var transaction: ArrayList<DataTransaction>,
                       val clickListener: (DataTransaction, Int)-> Unit): RecyclerView.Adapter<TransaksiAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transksi, parent, false)
        )

    override fun getItemCount()= transaction.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(transaction[position])
        holder.view.txtSee.setOnClickListener{
            clickListener(transaction[position], position)
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(transaction: DataTransaction){
                view.txtInvoice.text = transaction.no_faktur
                view.txtDate.text = transaction.tgl_penjualan
                view.txtTotal.text = transaction.total_rupiah
        }
    }

    fun setData(newTransaction: List<DataTransaction>){
        transaction.clear()
        transaction.addAll(newTransaction)
        notifyDataSetChanged()
    }

}