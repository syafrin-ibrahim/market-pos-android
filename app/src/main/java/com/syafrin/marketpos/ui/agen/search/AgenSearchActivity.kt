package com.syafrin.marketpos.ui.agen.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.model.agen.DataAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgen
import kotlinx.android.synthetic.main.activity_agent_search.*

class AgenSearchActivity : AppCompatActivity(), AgenSearchContract.View {


    private lateinit var agentSearchPresenter: AgenSearchPresenter
    private lateinit var agenSearchAdapter: AgenSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agent_search)

        agentSearchPresenter = AgenSearchPresenter(this)
        agentSearchPresenter.getAgent()
    }


    override fun initActivity() {
            supportActionBar!!.title = "Pilih Agen"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun iniListener() {
            agenSearchAdapter = AgenSearchAdapter(this, arrayListOf()){
                dataAgen: DataAgen, position: Int ->
                Constant.AGENT_ID = dataAgen.kd_agen!!
                Constant.AGENT_NAME = dataAgen.nama_toko!!
                Constant.IS_CHANGE = true
                finish()
            }

            edtSearch.setOnEditorActionListener { v, actionId, event ->
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    agentSearchPresenter.searchAgent(edtSearch.text.toString())
                    true
                }else{
                    false
                }
            }
        rcvAgentSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agenSearchAdapter

        }

        swipeAgent.setOnRefreshListener {
            agentSearchPresenter.getAgent()
        }
    }

    override fun onLoadingAgent(loading: Boolean) {
        when(loading){
            true-> {
                swipeAgent.isRefreshing = true
            }
            false -> {
                swipeAgent.isRefreshing = false
            }
        }
    }

    override fun onResultAgent(responseAgen: ResponseAgen) {
        val dataAgen: List<DataAgen> = responseAgen.dataAgen
        agenSearchAdapter.setData(dataAgen)
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }


}
