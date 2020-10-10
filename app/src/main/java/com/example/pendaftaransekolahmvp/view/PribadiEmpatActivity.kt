package com.example.pendaftaransekolahmvp.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.pendaftaransekolahmvp.R
import com.example.pendaftaransekolahmvp.presenter.pribadiempat.PribadiEmpatPresenter
import com.example.pendaftaransekolahmvp.presenter.pribadiempat.PribadiEmpatView
import kotlinx.android.synthetic.main.activity_pribadi_dua.*
import kotlinx.android.synthetic.main.activity_pribadi_empat.*
import kotlinx.android.synthetic.main.activity_pribadi_empat.bttnSimpan
import kotlinx.android.synthetic.main.activity_pribadi_empat.idUser
import kotlinx.android.synthetic.main.activity_pribadi_tiga.*

class PribadiEmpatActivity : AppCompatActivity(), PribadiEmpatView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    //todo 6 (pribadiEmpat) deklarasi presenter
    var pribadiEmpatPresenter:PribadiEmpatPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pribadi_empat)

        // animasi
        anim()

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        //todo 7 (pribadiEmpat) inisialisasi presenter
        pribadiEmpatPresenter = PribadiEmpatPresenter(this)

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar4.visibility = View.VISIBLE

            //todo 1 (pribadiEmpat) ambil view
            var id = idUser.text.toString()
            var tempatTinggal = spinnerTempatTinggal.selectedItem.toString()
            var modeTransportasi = spinnerModeTransportasi.selectedItem.toString()
            var noKK = edtNomorKK.text.toString()
            var anakKeberapa = edtAnakKeberapa.text.toString()
            var penerimaKps = ""
            if (rbYa.isChecked)
            {
                penerimaKps = "Ya"
            }
            if (rbTidak.isChecked)
            {
                penerimaKps = "Tidak"
            }
            var noKps = edtNoKps.text.toString()

            //todo 9 (pribadiEmpat) eksekusi presenter
            pribadiEmpatPresenter?.tambahPribadiEmpat(id,tempatTinggal,modeTransportasi,noKK,anakKeberapa,penerimaKps, noKps)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtTempatTinggal.startAnimation(left_to_right)
        spinnerTempatTinggal.startAnimation(left_to_right)
        ketTempatTinggal.startAnimation(left_to_right)
        txtModeTransportasi.startAnimation(left_to_right)
        spinnerModeTransportasi.startAnimation(left_to_right)
        ketModeTransportasi.startAnimation(left_to_right)
        txtNoKk.startAnimation(left_to_right)
        edtNomorKK.startAnimation(left_to_right)
        ketNoKk.startAnimation(left_to_right)
        txtAnakKe.startAnimation(left_to_right)
        edtAnakKeberapa.startAnimation(left_to_right)
        ketAnakKe.startAnimation(left_to_right)
        txtPenerimaKps.startAnimation(left_to_right)
        rbYa.startAnimation(left_to_right)
        rbTidak.startAnimation(left_to_right)
        ketPenerimaKps.startAnimation(left_to_right)
        txtNoKps.startAnimation(left_to_right)
        edtNoKps.startAnimation(left_to_right)
        ketNoKps.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    //todo 8 (pribadiEmpat) implementasi presenter
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
        progressBar4.visibility = View.GONE
    }

    override fun inputKosong() {
        toast("Tidak boleh kosong")
    }

    fun toast(message: String)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}