package com.syafrin.marketpos.ui.agen

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

class AgenAdapter(val context: Context, var dataAgen: ArrayList<DataAgen>,
                  val clickListener: (DataAgen, Int, String)-> Unit): RecyclerView.Adapter<AgenAdapter.ViewHolder>(){


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
            holder.view.img_agen.setOnClickListener {
                Constant.AGENT_ID = dataAgen[position].kd_agen!!
                clickListener(dataAgen[position], position, "detail")
            }
            holder.view.txt_option.setOnClickListener {
            val popupmenu = PopupMenu(context, holder.view.txt_option)
            popupmenu.inflate(R.menu.menu_options)
            popupmenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_update -> {
                        Constant.AGENT_ID = dataAgen[position].kd_agen!!
                        clickListener(dataAgen[position], position, "update")
                    }
                    R.id.action_delete -> {
                        Constant.AGENT_ID = dataAgen[position].kd_agen!!
                        clickListener(dataAgen[position], position, "delete")
                    }
                }
                true
            }
            popupmenu.show()
        }
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
    fun removeAgent(position: Int){
            dataAgen.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataAgen.size)
    }
}