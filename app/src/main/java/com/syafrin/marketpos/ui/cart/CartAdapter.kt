package com.syafrin.marketpos.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.model.cart.DataCart
import androidx.recyclerview.widget.RecyclerView
import com.lazday.poslaravel.util.GlideHelper
import kotlinx.android.synthetic.main.adapter_cart.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(val context: Context, var cart: ArrayList<DataCart>,
                  val clickListener: (DataCart, Int)-> Unit): RecyclerView.Adapter<CartAdapter.ViewHolderCart>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolderCart (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart, parent, false)
        )

    override fun getItemCount()= cart.size

    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
        holder.bing(cart[position])
        GlideHelper.setImage(context, cart[position].img_produk!!, holder.view.cartDelete);
        holder.view.cartDelete.setOnClickListener {
            clickListener(cart[position], position)
            removeCart(position)
        }
    }

    class ViewHolderCart(view: View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(cart: DataCart){
                view.cartCategori.text = cart.kategori
                view.cartNameProduk.text = cart.nama_produk
                view.cartPrice.text = "${cart.harga} x ${cart.jumlah}"
                val total: Double = cart!!.jumlah!!.toDouble() * cart!!.harga!!.toDouble()
                val totalRupiah: String = NumberFormat.getNumberInstance(Locale.GERMANY).format(total)
                view.cartTotal.text = "Rp. ${totalRupiah}"
        }
    }

    fun setData(newCart: List<DataCart>){
        cart.clear()
        cart.addAll(newCart)
        notifyDataSetChanged()
    }

    fun removeCart(position: Int){
            cart.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cart.size)
        }


    fun removeAll() {
        cart.clear()
        notifyDataSetChanged()
    }

}