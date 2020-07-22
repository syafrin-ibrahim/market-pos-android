package com.syafrin.marketpos.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.ui.agen.AgenActivity
import com.syafrin.marketpos.ui.login.LoginActivity
import com.syafrin.marketpos.ui.transaksi.TransaksiActivity
import com.syafrin.marketpos.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefsManager = PrefsManager(this)
        presenter = MainPresenter(this)

    }

    override fun onStart() {
        super.onStart()

        when(prefsManager.prefIslogin){
            true->{
                crvUser.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
            }
            false->{
                crvUser.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }
        }
    }


    override fun initActivity() {

        crvAgent.setOnClickListener {
            if(prefsManager.prefIslogin){
                startActivity(Intent(this, AgenActivity::class.java))
            }else{
                showMessage("anda belum Login")
            }
        }

        crvTransaction.setOnClickListener {
            if(prefsManager.prefIslogin){
                startActivity(Intent(this, TransaksiActivity::class.java))
            }else{
                showMessage("anda belum login")
            }
        }

        crvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        btn_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java ))
        }

    }

    override fun showMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }


}
