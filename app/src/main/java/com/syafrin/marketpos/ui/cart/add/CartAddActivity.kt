package com.syafrin.marketpos.ui.cart.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.data.model.cart.ResponseCartUpdate
import com.syafrin.marketpos.ui.produk.ProdukActivity
import kotlinx.android.synthetic.main.activity_cart_add.*

class CartAddActivity : AppCompatActivity(), CartAddContract.View {

    lateinit var cartAddPresenter: CartAddPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_add)
        prefsManager = PrefsManager(this)
        cartAddPresenter = CartAddPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        if(Constant.IS_CHANGE){
            Constant.IS_CHANGE = false
            edtxProduct.setText(Constant.PRODUCT_NAME)
            nmbrQtity.visibility = View.VISIBLE
            txtQty.visibility = View.VISIBLE

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.PRODUCT_NAME = ""
        Constant.PRODUCT_ID = 0
    }

    override fun initActivity() {
        supportActionBar!!.title = "Tambah Produk"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        txtQty.visibility = View.GONE
        nmbrQtity.visibility = View.GONE

    }

    override fun initListener() {
        edtxProduct.setOnClickListener {
            startActivity(Intent(this, ProdukActivity::class.java))
        }

        nmbrQtity.minValue = 1
        nmbrQtity.maxValue = 25
        nmbrQtity.wrapSelectorWheel = true
        nmbrQtity.setOnValueChangedListener { picker, oldVal, newVal ->
            Constant.PRODUCT_QTY = newVal.toLong()
        }
        btnxSubmit.setOnClickListener {
            if(Constant.PRODUCT_ID > 0 ){
                Constant.PRODUCT_QTY = if (Constant.PRODUCT_QTY > 0 )Constant.PRODUCT_QTY else 1
                cartAddPresenter.addCart(prefsManager.prefUsername, Constant.PRODUCT_ID, Constant.PRODUCT_QTY)
            }else{
                edtxProduct.error = "Produk Tidak Boleh Kosong"
            }
        }
    }

    override fun onLoading(loading: Boolean) {
            when(loading){
                true -> {
                    progress.visibility = View.VISIBLE
                    btnxSubmit.visibility = View.GONE
                }
                false -> {
                    progress.visibility = View.GONE
                    btnxSubmit.visibility = View.VISIBLE
                }
            }
    }

    override fun onResult(responseCartUpdate: ResponseCartUpdate) {
        if(responseCartUpdate.status){
            Constant.IS_CHANGE = true
            finish()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }



}
