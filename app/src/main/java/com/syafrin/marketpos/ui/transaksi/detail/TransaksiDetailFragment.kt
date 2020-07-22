package com.syafrin.marketpos.ui.transaksi.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.model.transaction.detail.DataDetail
import com.syafrin.marketpos.data.model.transaction.detail.ResponseTransactionDetail
import kotlinx.android.synthetic.main.fragment_transaksi_detail.*

/**
 * A simple [Fragment] subclass.
 */
class TransaksiDetailFragment : Fragment(), TransaksiDetailContract.View {

    lateinit var detailAdapter:TransaksiDetailAdapter
    lateinit var presenter: TransaksiDetailPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transaksi_detail, container, false)
        presenter = TransaksiDetailPresenter(this)
        initListener(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi Detail"
        presenter.getTransactionByInvoice(Constant.INVOICE)
    }
    override fun initFragment() {
        detailAdapter = TransaksiDetailAdapter(context!!, arrayListOf())

    }

    override fun initListener(view: View) {
        val txtInvoice = view.findViewById<TextView>(R.id.txtInvoiceDetail)
        val rcvDetail = view.findViewById<RecyclerView>(R.id.rcDetail)
        val swipeDetail = view.findViewById<SwipeRefreshLayout>(R.id.swipeDetail)

        txtInvoice.text = Constant.INVOICE
        rcvDetail!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter

        }
         swipeDetail.setOnRefreshListener {
             presenter.getTransactionByInvoice(Constant.INVOICE)

         }

    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true-> {
                swipeDetail.isRefreshing = true
            }
            false -> {
                swipeDetail.isRefreshing = false
            }
        }
    }

    override fun onResult(responseTransactionDetail: ResponseTransactionDetail) {

        val dataDetail: List<DataDetail> = responseTransactionDetail.dataDetail
        detailAdapter.setData(dataDetail)

    }



}
