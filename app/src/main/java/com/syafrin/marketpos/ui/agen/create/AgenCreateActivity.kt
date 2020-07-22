package com.syafrin.marketpos.ui.agen.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lazday.poslaravel.util.GalleryHelper
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import com.syafrin.marketpos.ui.agen.AgenMapsActivity
import com.syafrin.marketpos.utils.FileUtils
import kotlinx.android.synthetic.main.activity_agen_create.*

class AgenCreateActivity : AppCompatActivity(), AgenCreateContract.View {
    private var uriImage:Uri? = null

    private var pickImage = 1

    lateinit var presenter: AgenCreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agen_create)

        presenter = AgenCreatePresenter(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == pickImage && resultCode == Activity.RESULT_OK){
            uriImage = data!!.data
            imgToko.setImageURI(uriImage)
            var img: String = FileUtils.getFile(this,uriImage).toString()
            Toast.makeText(this, img, Toast.LENGTH_LONG).show()
        }

    }

    override fun initActivity() {
        supportActionBar!!.title="Agen Baru"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun initListener() {
        edLokasi.setOnClickListener {
            startActivity(Intent(this, AgenMapsActivity::class.java))
        }

        imgToko.setOnClickListener {
            if(GalleryHelper.permissionGallery(this, this, pickImage)){
                GalleryHelper.openGallery(this)
            }
        }

        btn_save.setOnClickListener {
            val store = edNamaToko.text
            val pml = edPemilik.text
            val almt = edAlamat.text
            val loc = edLokasi.text

            if(store.isNullOrEmpty() || pml.isNullOrEmpty() || almt.isNullOrEmpty()
                || loc.isNullOrEmpty()  || uriImage == null){
                showMessage("Lengkapi Data dengan Benar")
            }else{
                presenter.storeAgen(store.toString(), pml.toString(), almt.toString(),
                Constant.LATITUDE, Constant.LONGITUDE, FileUtils.getFile(this, uriImage))
                //finish()
                //Toast.makeText(this, "filenya " + FileUtils.getFile(uriImage).toString(), Toast.LENGTH_LONG).s
               // showMessage("aman " + FileUtils.getFile(this, uriImage));
                           }
        }

    }

    override fun onLoading(loading: Boolean) {
        when(loading){
            true -> {
                progressbar.visibility = View.VISIBLE
                btn_save.visibility = View.GONE
            }
            false -> {
                progressbar.visibility = View.GONE
                btn_save.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseAgenUpdate: ResponseAgenUpdate) {
        showMessage(responseAgenUpdate.msg)
        finish()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }



    override fun onStart() {
        super.onStart()
        if(Constant.LATITUDE.isNotEmpty()){
            edLokasi.setText("${Constant.LATITUDE}, ${Constant.LONGITUDE}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.LONGITUDE = ""
        Constant.LATITUDE = ""
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}
