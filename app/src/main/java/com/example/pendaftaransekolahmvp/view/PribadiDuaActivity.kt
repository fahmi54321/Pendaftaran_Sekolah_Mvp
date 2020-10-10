package com.example.pendaftaransekolahmvp.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendaftaransekolahmvp.R
import com.example.pendaftaransekolahmvp.presenter.pribadidua.PribadiDuaPresenter
import com.example.pendaftaransekolahmvp.presenter.pribadidua.PribadiDuaView
import kotlinx.android.synthetic.main.activity_pribadi_dua.*

class PribadiDuaActivity : AppCompatActivity(), PribadiDuaView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    // todo 6 (pribadiDua) deklarasi presenter
    var kebutuhanKhusus:String=""
    var pribadiDuaPresenter:PribadiDuaPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pribadi_dua)

        // animasi
        anim()

        // todo 7 (pribadiDua) inisialisasi presenter
        pribadiDuaPresenter = PribadiDuaPresenter(this)

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar2.visibility = View.VISIBLE

            // todo 1 (pribadiDua) ambil view
            var id = idUser.text.toString()
            var akte = edtAkte.text.toString()
            var agama = spinnerAgama.selectedItem.toString()
            var kwn=""
            if (rbWni.isChecked)
            {
                kwn = "Indonesia (WNI)"
            }
            else if(rbWna.isChecked)
            {
                kwn = "Asing (WNA)"
            }
            if (cbTidak.isChecked)
            {
                kebutuhanKhusus+= cbTidak.getText().toString()
            }
            if(cbAutis.isChecked)
            {
                kebutuhanKhusus+= cbAutis.getText().toString()+","
            }
            if(cbBakatIstimewa.isChecked)
            {
                kebutuhanKhusus+=cbBakatIstimewa.getText().toString()+","
            }
            if(cbCerdasIstimewa.isChecked)
            {
                kebutuhanKhusus+=cbCerdasIstimewa.getText().toString()+","
            }
            if(cbDeksaRingan.isChecked)
            {
                kebutuhanKhusus+=cbDeksaRingan.getText().toString()+","
            }
            if(cbDeksaSedang.isChecked)
            {
                kebutuhanKhusus+=cbDeksaSedang.getText().toString()+","
            }
            if(cbDownSindrome.isChecked)
            {
                kebutuhanKhusus+=cbDownSindrome.getText().toString()+","
            }
            if(cbGrahitaRingan.isChecked)
            {
                kebutuhanKhusus+=cbGrahitaRingan.getText().toString()+","
            }
            if(cbHiperAktif.isChecked)
            {
                kebutuhanKhusus+=cbHiperAktif.getText().toString()+","
            }
            if(cbIndigo.isChecked)
            {
                kebutuhanKhusus+=cbIndigo.getText().toString()+","
            }
            if(cbKesulitanBelajar.isChecked)
            {
                kebutuhanKhusus+=cbKesulitanBelajar.getText().toString()+","
            }
            if(cbLaras.isChecked)
            {
                kebutuhanKhusus+=cbLaras.getText().toString()+","
            }
            if(cbNarkoba.isChecked)
            {
                kebutuhanKhusus+=cbNarkoba.getText().toString()+","
            }
            if(cbNetra.isChecked)
            {
                kebutuhanKhusus+=cbNetra.getText().toString()+","
            }
            if(cbRungu.isChecked)
            {
                kebutuhanKhusus+=cbRungu.getText().toString()+","
            }
            if(cbTunaGanda.isChecked)
            {
                kebutuhanKhusus+=cbTunaGanda.getText().toString()+","
            }
            if(cbWicara.isChecked)
            {
                kebutuhanKhusus+=cbWicara.getText().toString()+","
            }

            var alamatJalan = edtAlamatJalan.text.toString()
            var Rt = edtRt.text.toString()
            var Rw = edtRw.text.toString()

            // todo 9 (pribadiDua) eksekusi presenter
            pribadiDuaPresenter?.tambahPribadiDua(id,akte,agama,kwn,kebutuhanKhusus,alamatJalan,Rt,Rw)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtNoAkte.startAnimation(left_to_right)
        edtAkte.startAnimation(left_to_right)
        ketNoAkte.startAnimation(left_to_right)
        txtAgama.startAnimation(left_to_right)
        spinnerAgama.startAnimation(left_to_right)
        ketAgama.startAnimation(left_to_right)
        txtKewarganegaraan.startAnimation(left_to_right)
        rbWna.startAnimation(left_to_right)
        rbWni.startAnimation(left_to_right)
        ketKewarganegaraan.startAnimation(left_to_right)
        txtBerkebutuhanKhusus.startAnimation(left_to_right)

        cbTidak.startAnimation(left_to_right)
        cbBakatIstimewa.startAnimation(left_to_right)
        cbTunaGanda.startAnimation(left_to_right)
        cbCerdasIstimewa.startAnimation(left_to_right)
        cbDownSindrome.startAnimation(left_to_right)
        cbGrahitaRingan.startAnimation(left_to_right)
        cbNetra.startAnimation(left_to_right)
        cbNarkoba.startAnimation(left_to_right)
        cbHiperAktif.startAnimation(left_to_right)
        cbKesulitanBelajar.startAnimation(left_to_right)
        cbDeksaSedang.startAnimation(left_to_right)
        cbDeksaRingan.startAnimation(left_to_right)
        cbIndigo.startAnimation(left_to_right)
        cbAutis.startAnimation(left_to_right)
        cbLaras.startAnimation(left_to_right)
        cbRungu.startAnimation(left_to_right)
        cbWicara.startAnimation(left_to_right)

        ketBerkebutuhanKhusus.startAnimation(left_to_right)
        txtAlamat.startAnimation(left_to_right)
        edtAlamatJalan.startAnimation(left_to_right)
        ketAlamat.startAnimation(left_to_right)
        txtRt.startAnimation(left_to_right)
        edtRt.startAnimation(left_to_right)
        ketRt.startAnimation(left_to_right)
        txtRw.startAnimation(left_to_right)
        ketRw.startAnimation(left_to_right)
        edtRw.startAnimation(left_to_right)

        bttnSimpan.startAnimation(bottom_to_top)
    }

    // todo 8 (pribadiDua) impelementasi presenter
    override fun onSuccess(message: String) {
        toast("Berhasil")
        val intent = Intent(this,SuccessIsiFormActivity::class.java)
        intent.putExtra("idUser",idUser.text.toString())
        startActivity(intent)
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun hideLoading() {
        bttnSimpan.text="Simpan"
        bttnSimpan.isEnabled = true
        bttnSimpan.setBackgroundColor(Color.rgb(245,112,100))
    }

    override fun hideProgress() {
        progressBar2.visibility = View.GONE
    }

    override fun inputKosong() {
        toast("Tidak boleh kosong")
    }

    fun toast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}