package com.syafrin.marketpos.ui.agen.search

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.syafrin.marketpos.data.model.agen.DataAgen
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.bumptech.glide.Glide
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import kotlinx.android.synthetic.main.adapter_agen.view.*
import kotlinx.android.synthetic.main.adapter_agen_search.view.*

class AgenSearchAdapter(val context: Context, var dataAgen: ArrayList<DataAgen>,
                        val clickListener: (DataAgen, Int)-> Unit): RecyclerView.Adapter<AgenSearchAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agen_search, parent, false)
        )

    override fun getItemCount()= dataAgen.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataAgen[position])
        Glide.with(context)
            .load(dataAgen[position].img_toko)
            .centerCrop()
            .placeholder(R.drawable.img_no_image)
            .error(R.drawable.img_no_image)
            .into(holder.view.img_agen)
            holder.view.crvAgent.setOnClickListener {
                Constant.AGENT_ID = dataAgen[position].kd_agen!!
                clickListener(dataAgen[position], position)
            }

    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(dataAgen: DataAgen){
                view.txvNameStore.text = dataAgen.nama_toko
                view.txvLocation.text = dataAgen.alamat
        }
    }

    fun setData(newDataAgen: List<DataAgen>){
        dataAgen.clear()
        dataAgen.addAll(newDataAgen)
        notifyDataSetChanged()
    }

}