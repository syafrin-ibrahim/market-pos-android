package com.syafrin.marketpos.ui.agen

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.model.DataAgen
import com.syafrin.marketpos.data.model.ResponseAgen

import kotlinx.android.synthetic.main.activity_agen.*
import kotlinx.android.synthetic.main.content_agen.*


class AgenActivity : AppCompatActivity(), AgenContract.View {

    lateinit var presenter: AgenPresenter
    lateinit var agenAdapter: AgenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agen)
        setSupportActionBar(toolbar)

        presenter = AgenPresenter(this)

    }

    override fun onStart() {

        super.onStart()
        presenter.getAgen()
    }
    override fun initActivity() {
        supportActionBar!!.title ="Agen"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true )
    }

    override fun initListener() {
        agenAdapter = AgenAdapter(this, arrayListOf())

        rcvAgen.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agenAdapter
        }

        swipe.setOnRefreshListener {
            presenter.view
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onLoadingAgen(loading: Boolean) {
        when(loading){
                true -> swipe.isRefreshing = true
                false -> swipe.isRefreshing = false
        }
    }

    override fun onResultAgen(responseAgen: ResponseAgen) {
        val dataAgen: List<DataAgen> = responseAgen.dataAgen
        agenAdapter.setData(dataAgen)
    }

    override fun showMessage() {

    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}
