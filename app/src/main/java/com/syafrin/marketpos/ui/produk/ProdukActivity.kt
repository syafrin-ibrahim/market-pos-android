package com.syafrin.marketpos.ui.produk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.syafrin.marketpos.R
import androidx.recyclerview.widget.RecyclerView
import com.syafrin.marketpos.data.model.category.DataCategory
import com.syafrin.marketpos.data.model.category.ResponseCategoryList
import com.syafrin.marketpos.data.model.product.DataProduct
import com.syafrin.marketpos.data.model.product.ResponseProductList
import kotlinx.android.synthetic.main.activity_produk.*
import kotlinx.android.synthetic.main.activity_produk.txvCategory
import kotlinx.android.synthetic.main.adapter_category.*
import kotlinx.android.synthetic.main.fragment_transaksi_detail.*

class ProdukActivity : AppCompatActivity(), ProductContract.View {
    private lateinit var productadapter : ProdukAdapter
    private lateinit var categoryadapter : CategoriAdapter
    private lateinit var presenter: ProductPresenter
    private var kdKategori: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk)
        productadapter = ProdukAdapter(this, arrayListOf())
        presenter = ProductPresenter(this)
        presenter.getCategory()
    }



    override fun initActivity() {
        supportActionBar!!.hide()
        categoryadapter = CategoriAdapter(this, arrayListOf()){
            category: DataCategory, position: Int ->
            kdKategori = category.kd_kategori!!
            presenter.getProduct(kdKategori)
        }
    }

    override fun initListener() {
        rcvCategory.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = categoryadapter
        }

        rcvProduct.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productadapter
        }

        swipex.setOnRefreshListener {
                when(View.VISIBLE){
                    rcvCategory.visibility -> presenter.getCategory()
                    rcvProduct.visibility -> presenter.getProduct(kdKategori)
                }
        }

        imxClose.setOnClickListener {
            when(View.VISIBLE){
                rcvCategory.visibility -> finish()
                rcvCategory.visibility -> {
                    rcvProduct.visibility = View.GONE
                    rcvCategory.visibility = View.VISIBLE
                    txvCategory.text = "Pilih Categori"
                }
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true-> {
                swipex.isRefreshing = true
                rcvCategory.visibility = View.GONE
                rcvProduct.visibility = View.GONE
            }
            false -> {
                swipex.isRefreshing = false
            }
        }

    }

    override fun onResultCategory(responseCategoryList: ResponseCategoryList) {
            rcvCategory.visibility = View.VISIBLE
            val dataCategoriAdapter: List<DataCategory> = responseCategoryList.dataCategory
            categoryadapter.setData(dataCategoriAdapter)
            txvCategory.text = "Pilih Kategori"

    }



    override fun onResultProduct(responseProductList: ResponseProductList) {

        rcvProduct.visibility = View.VISIBLE
        val dataProduct: List<DataProduct> = responseProductList.dataProduct
        productadapter.setData(dataProduct)
        txvCategory.text = dataProduct[0].kategori
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when(View.VISIBLE){
            rcvCategory.visibility -> finish()
            rcvCategory.visibility -> {
                rcvProduct.visibility = View.GONE
                rcvCategory.visibility = View.VISIBLE
                txvCategory.text = "Pilih Categori"
            }
        }
    }


}
