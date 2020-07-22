package com.syafrin.marketpos.ui.transaksi


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.data.model.transaction.DataTransaction
import com.syafrin.marketpos.data.model.transaction.ResponseTransactionList
import com.syafrin.marketpos.ui.cart.CartActivity
import com.syafrin.marketpos.ui.transaksi.detail.TransaksiDetailFragment
import kotlinx.android.synthetic.main.fragment_transaksi.*

/**
 * A simple [Fragment] subclass.
 */
class TransaksiFragment : Fragment(), TransaksiContract.View {

    private lateinit var  prefsManager: PrefsManager
    private lateinit var transactionAdapter: TransaksiAdapter
    private lateinit var presenter: TransaksiPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_transaksi, container, false)

        prefsManager = PrefsManager(context!!)
        presenter = TransaksiPresenter(this)
        initListener(view)
        return view

    }

    override fun initFragment() {
        transactionAdapter = TransaksiAdapter(context!!, arrayListOf()){
            dataTransaction, position ->
            onClickTransaction(dataTransaction.no_faktur!!)
        }
    }

    override fun onStart() {

        super.onStart()
        (activity as  AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransactionByUsername(prefsManager.prefUsername)
    }
    override fun initListener(view: View) {
        val rcvTransaction = view.findViewById<RecyclerView>(R.id.rcvTrans)
        val fab = view.findViewById<FloatingActionButton>(R.id.fabTrans)
        val swipeTrans = view.findViewById<SwipeRefreshLayout>(R.id.swipetrans)

        rcvTransaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        swipeTrans.setOnRefreshListener {
            presenter.getTransactionByUsername(prefsManager.prefUsername)
        }

        fab.setOnClickListener {
            context!!.startActivity(Intent(context, CartActivity::class.java))
        }
    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> swipetrans.isRefreshing = true
            false -> swipetrans.isRefreshing = false
        }
    }

    override fun onResult(responseTransactionList: ResponseTransactionList) {
        val dataTransaction: List<DataTransaction> = responseTransactionList.dataTransaction
        transactionAdapter.setData(dataTransaction)
    }

    override fun onClickTransaction(invoice: String) {

        Constant.INVOICE  = invoice
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.container,
                TransaksiDetailFragment(), "transaksi_detail_fragment")
            .commit()
    }




}
