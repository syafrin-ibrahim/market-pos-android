package com.syafrin.marketpos.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syafrin.marketpos.R
import android.widget.Toast
import com.syafrin.marketpos.data.database.PrefsManager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: UserPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        prefsManager = PrefsManager(this)
        presenter = UserPresenter(this)
        presenter.doLogin(prefsManager)

    }


    override fun initActivity() {
        supportActionBar!!.hide()
    }

    override fun initListener() {
        btn_back.setOnClickListener {
            finish()
        }

        txt_log_out.setOnClickListener {
            presenter.doLogout(prefsManager)
        }
    }

    override fun onResultLogin(prefsManager: PrefsManager) {
            txt_user.text = prefsManager.prefUsername
            txt_name.text = prefsManager.prefsNamaPegawai
            txt_jk.text = prefsManager.prefsJk
            txt_alamat.text = prefsManager.prefsAlamat

    }

    override fun onResultLogOut() {
        finish()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }


}
