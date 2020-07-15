package com.syafrin.marketpos.ui.agen

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.syafrin.marketpos.data.model.DataAgen
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.syafrin.marketpos.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_agen.view.*

class AgenAdapter(val context: Context, var dataAgen: ArrayList<DataAgen>): RecyclerView.Adapter<AgenAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agen, parent, false)
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
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(dataAgen: DataAgen){
                view.txt_namatoko.text = dataAgen.nama_toko
                view.txt_alamat.text = dataAgen.alamat
        }
    }

    fun setData(newDataAgen: List<DataAgen>){
        dataAgen.clear()
        dataAgen.addAll(newDataAgen)
        notifyDataSetChanged()
    }
}