package com.syafrin.marketpos.ui.transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.syafrin.marketpos.R

class TransaksiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initView()

    }

    fun initView(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TransaksiFragment(), "tag_fragment")
            .commit()
    }

    override fun onNavigateUp(): Boolean {
        if(supportFragmentManager.findFragmentByTag("tag_fragment") == null){
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TransaksiFragment(), "tag_fragment")
                    .commit()
        }else{
            finish()
        }

        return super.onNavigateUp()
    }
}
