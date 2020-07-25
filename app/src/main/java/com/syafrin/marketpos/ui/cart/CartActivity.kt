package com.syafrin.marketpos.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.data.model.cart.DataCart
import com.syafrin.marketpos.data.model.cart.ResponseCartList
import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate
import com.syafrin.marketpos.data.model.cart.ResponseCheckout
import com.syafrin.marketpos.ui.agen.search.AgenSearchActivity
import com.syafrin.marketpos.ui.cart.add.CartAddActivity
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity(), CartContract.View {

    private lateinit var prefsManager: PrefsManager
    private lateinit var cartAdapter: CartAdapter
    private lateinit var presenter: CartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        prefsManager = PrefsManager(this)
        presenter = CartPresenter(this)
        presenter.getCart(prefsManager.prefUsername)
    }


    override fun onResultDeleteCart(responseCartUpdate: ResponseCartUpdate) {
        presenter.getCart(prefsManager.prefUsername)
        cartAdapter.removeAll()
    }

    override fun showDialog() {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Konfirmasi")
            dialog.setMessage("Hapus Semua Item Keranjang ? ")
            dialog.setPositiveButton("hapus"){
                dialog, which ->
                presenter.deleteCart(prefsManager.prefUsername)
                dialog.dismiss()
            }
            dialog.setNegativeButton("Batal"){
                dialog, which ->
                dialog.dismiss()
            }
            dialog.show()

    }

    override fun initActivity() {
        supportActionBar!!.title = "Keranjang"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        cartAdapter = CartAdapter(this, arrayListOf()){
            dataCart: DataCart ,position: Int ->
            presenter.deteteItemCart(dataCart.kd_keranjang!!)
        }
    }

    override fun onStart() {
        super.onStart()
        if(Constant.IS_CHANGE){
            Constant.IS_CHANGE = false
            presenter.getCart(prefsManager.prefUsername)
            edtAgenCart.setText(Constant.AGENT_NAME)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.AGENT_NAME = ""

    }
    override fun initListener() {

        txt_reset.visibility = View.GONE
        edtAgenCart.visibility = View.GONE

        rcvCart.apply { layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }

        swipeCart.setOnRefreshListener {
            presenter.getCart(prefsManager.prefUsername)
        }

        btnAddProduk.setOnClickListener {
            startActivity(Intent(this, CartAddActivity::class.java))
        }

        txt_reset.setOnClickListener {
            showDialog()
        }

        edtAgenCart.setOnClickListener {
            startActivity(Intent(this, AgenSearchActivity::class.java))
        }

        btnCheckOut.setOnClickListener {
            if(cartAdapter.cart.isNullOrEmpty()){
                showMessage("Keranjang Tidak Boleh Kosong")
            }else{
                if(edtAgenCart.text.isNullOrEmpty()){
                    edtAgenCart.error = "tidak boleh kosong"
                }else{
                    presenter.checkout(prefsManager.prefUsername,  Constant.AGENT_ID)
                }
            }
        }


    }

    override fun onLoadingCart(loading: Boolean) {
            when(loading){
                true -> swipeCart.isRefreshing = true
                false -> swipeCart.isRefreshing = false
            }
    }

    override fun onResultCart(responseCartList: ResponseCartList) {
        val dataCart: List<DataCart> = responseCartList.dataCart
        if(dataCart.isNullOrEmpty()){
            txt_reset.visibility = View.GONE
            edtAgenCart.visibility = View.GONE
        }else{
            cartAdapter.setData(dataCart)
            txt_reset.visibility = View.VISIBLE
            edtAgenCart.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun onLoadingCheckout(loading: Boolean) {
        when(loading){
            true ->{
                btnCheckOut.isEnabled = false
                btnCheckOut.setText("Memuat")
            }

            false -> {
                btnCheckOut.isEnabled = true
                btnCheckOut.setText("Checkout")
            }
        }
    }

    override fun onResultCheckout(responseCheckout: ResponseCheckout) {
        presenter.getCart(prefsManager.prefUsername)
        cartAdapter.removeAll()
    }

}
