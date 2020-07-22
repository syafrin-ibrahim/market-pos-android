package com.syafrin.marketpos.ui.agen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.syafrin.marketpos.R
import com.syafrin.marketpos.data.Constant
import com.syafrin.marketpos.data.model.agen.DataAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgen
import com.syafrin.marketpos.data.model.agen.ResponseAgenUpdate
import com.syafrin.marketpos.ui.agen.create.AgenCreateActivity
import com.syafrin.marketpos.ui.agen.update.AgenUpdateActivity
import com.syafrin.marketpos.utils.MapsHelper
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lazday.poslaravel.util.GlideHelper
import kotlinx.android.synthetic.main.activity_agen.*
import kotlinx.android.synthetic.main.content_agen.*
import kotlinx.android.synthetic.main.dialog_agen.view.*


class AgenActivity : AppCompatActivity(), AgenContract.View, OnMapReadyCallback{


    override fun onMapReady(map: GoogleMap?) {
        val LatLng = LatLng(agent.latitude!!.toDouble(), agent.longitude!!.toDouble())
        map!!.addMarker(MarkerOptions().position(LatLng).title(agent.nama_toko))
        map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng, 18f))
    }


    override fun showDialogStore(dataAgen: DataAgen, position: Int) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_agen, null)

        GlideHelper.setImage(applicationContext, dataAgen.img_toko!!, view.imgStore)
        view.txtStore.setText(dataAgen.nama_toko)
        view.txtOwner.setText(dataAgen.nama_pemilik)
        view.txtAddress.setText(dataAgen.alamat)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        view.imgClose.setOnClickListener {
            supportFragmentManager.beginTransaction().remove(mapFragment).commit()
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()

    }


    lateinit var presenter: AgenPresenter
    lateinit var agenAdapter: AgenAdapter
    lateinit var agent: DataAgen

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
        MapsHelper.permissionMap(this, this)
    }

    override fun initListener() {
        agenAdapter = AgenAdapter(this, arrayListOf()){
                dataAgen: DataAgen, position: Int, type: String ->
            agent = dataAgen
           when(type){
                "update"-> startActivity(Intent(this, AgenUpdateActivity::class.java))
                "delete" -> showConfirmDelete(dataAgen, position)
                "detail" -> showDialogStore(dataAgen, position)
            }
        }

        rcvAgen.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agenAdapter
        }

        swipe.setOnRefreshListener {
            presenter.getAgen()
        }
        fab.setOnClickListener { view ->
            startActivity(Intent(this, AgenCreateActivity::class.java))
        }
    }

    override fun onResultDeleteAgen(responseAgenUpdate: ResponseAgenUpdate) {
        showMessage(responseAgenUpdate.msg)
    }

    override fun showConfirmDelete(dataAgen: DataAgen, position: Int) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Confirmasi")
        dialog.setMessage("Yakin Akan Menghapus Data Ini ${dataAgen.nama_toko} ? ")
        dialog.setPositiveButton("Hapus"){
                dialog, which -> presenter.deleteAgen(Constant.AGENT_ID)
            agenAdapter.removeAgent(position)
            dialog.dismiss()
        }

        dialog.setNegativeButton("Batal"){
            dialog, which ->
            dialog.dismiss()
        }
        dialog.show()

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

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}
