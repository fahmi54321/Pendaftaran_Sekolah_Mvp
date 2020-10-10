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
import com.example.pendaftaransekolahmvp.presenter.pribaditiga.PribadiTigaPresenter
import com.example.pendaftaransekolahmvp.presenter.pribaditiga.PribadiTigaView
import com.example.pendaftaransekolahmvp.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_pribadi_tiga.*
import kotlinx.android.synthetic.main.activity_pribadi_tiga.bttnSimpan

class PribadiTigaActivity : AppCompatActivity(), PribadiTigaView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    //todo 6 (pribadiTiga) deklarasi presenter
    var pribadiTigaPresenter:PribadiTigaPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pribadi_tiga)

        // animasi
        anim()

        //todo 7 (pribadiTiga) inisialisasi presenter
        pribadiTigaPresenter = PribadiTigaPresenter(this)

        val bundle = intent.extras
        txtIdUser.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar3.visibility = View.VISIBLE

            //todo 1 (pribadiTiga) ambil view
            var id = txtIdUser.text.toString()
            var namaDusun = edtNamaDusun.text.toString()
            var namaKelurahan = edtNamaKelurahan.text.toString()
            var kecamatan = edtNamaKecamatan.text.toString()
            var kodePos = edtKodePos.text.toString()
            var lintang = edtLintang.text.toString()
            var bujur = edtBujur.text.toString()

            //todo 9 (pribadiTiga) eksekusi presenter
            pribadiTigaPresenter?.tambahPribadiTiga(id,namaDusun, namaKelurahan, kecamatan, kodePos, lintang, bujur)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtNamaDusun.startAnimation(left_to_right)
        edtNamaDusun.startAnimation(left_to_right)
        ketNamaDusun.startAnimation(left_to_right)
        txtNamaDusun.startAnimation(left_to_right)
        edtNamaDusun.startAnimation(left_to_right)
        ketNamaDusun.startAnimation(left_to_right)
        txtKelurahan.startAnimation(left_to_right)
        edtNamaKelurahan.startAnimation(left_to_right)
        ketKelurahan.startAnimation(left_to_right)
        txtKecamatan.startAnimation(left_to_right)
        edtNamaKecamatan.startAnimation(left_to_right)
        ketKecamatan.startAnimation(left_to_right)
        txtKodePos.startAnimation(left_to_right)
        edtKodePos.startAnimation(left_to_right)
        ketKodePos.startAnimation(left_to_right)
        txtLintang.startAnimation(left_to_right)
        edtLintang.startAnimation(left_to_right)
        ketLintang.startAnimation(left_to_right)
        txtBujur.startAnimation(left_to_right)
        edtBujur.startAnimation(left_to_right)
        ketBujur.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    //todo 8 (pribadiTiga) implementasi presenter
    override fun onSuccess(message: String) {
        toast("Berhasil")
        val intent = Intent(this,SuccessIsiFormActivity::class.java)
        intent.putExtra("idUser",txtIdUser.text.toString())
        startActivity(intent)
    }

    override fun onError(message: String) {
        toast("Error")
    }

    override fun hideLoading() {
        bttnSimpan.text="Simpan"
        bttnSimpan.isEnabled = true
        bttnSimpan.setBackgroundColor(Color.rgb(245,112,100))
    }

    override fun hideProgress() {
        progressBar3.visibility = View.GONE
    }

    override fun inputKosong() {
        toast("Data tidak boleh kosong")
    }

    fun toast(message: String)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}