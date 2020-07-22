package com.syafrin.marketpos.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.database.PrefsManager
import com.syafrin.marketpos.data.model.login.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var presenter: LoginPresenter
    lateinit var prefsManager: PrefsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun initActivity() {
        supportActionBar!!.title = "MASUK"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


    }

    override fun initListener() {
        btn_login.setOnClickListener {
            presenter.doLogin(edtUser.text.toString(), edtPass.text.toString())
        }
    }

    override fun onResult(ResponseLogin: ResponseLogin) {
        //Log.d("Login Activity", "response login: ${ResponseLogin.pegawai}")
        presenter.setPrefs(prefsManager, ResponseLogin.pegawai!!)
        finish()
    }



//    fun doLogin(user: String, pass: String){
//        onLoading(true)
//        ApiService.endPoint.loginUser(user,pass)
//            .enqueue(object : Callback<ResponseLogin>{
//                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                    onLoading(false)
//
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseLogin>,
//                    response: Response<ResponseLogin>
//                ) {
//                    onLoading(false)
//                    if(response.isSuccessful){
//                        val responseLogin: ResponseLogin? = response.body()
//                        showMessage(responseLogin!!.message)
//                    }
//                }
//
//            })
//    }

    override fun onLoading(loading: Boolean){
        when (loading){
            true->{
                pbar.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
            }
            false->{
                pbar.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }
        }
    }

    override fun showMessage(msg: String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish();
        return super.onSupportNavigateUp()
    }
}
