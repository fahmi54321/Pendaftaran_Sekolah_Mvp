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
import com.example.pendaftaransekolahmvp.presenter.beasiswa.BeasiswaPresenter
import com.example.pendaftaransekolahmvp.presenter.beasiswa.BeasiswaView
import kotlinx.android.synthetic.main.activity_data_beasiswa.*
import kotlinx.android.synthetic.main.activity_data_beasiswa.bttnSimpan
import kotlinx.android.synthetic.main.activity_data_beasiswa.idUser

class DataBeasiswaActivity : AppCompatActivity(), BeasiswaView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    //todo 6 (tambahBeasiswa) deklarasi presenter
    var beasiswaPresenter:BeasiswaPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_beasiswa)

        // animasi
        anim()

        //todo 7 (tambahBeasiswa) inisialisasi presenter
        beasiswaPresenter = BeasiswaPresenter(this)

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar2.visibility = View.VISIBLE

            //todo 1 (tambahBeasiswa) ambil view
            var id = idUser.text.toString()
            var jenisBeasiswa1 = spinnerJenisPrestasi1.selectedItem.toString()
            var keterangan1 = edtKeterangan1.text.toString()
            var tahunMulai1 = edtTahunMulai1.text.toString()
            var tahunSelesai1 = edtTahunSelesai1.text.toString()

            var jenisBeasiswa2 = spinnerJenisPrestasi2.selectedItem.toString()
            var keterangan2 = edtKeterangan2.text.toString()
            var tahunMulai2 = edtTahunMulai2.text.toString()
            var tahunSelesai2 = edtTahunSelesai2.text.toString()

            var jenisBeasiswa3 = spinnerJenisPrestasi3.selectedItem.toString()
            var keterangan3 = edtKeterangan3.text.toString()
            var tahunMulai3 = edtTahunMulai3.text.toString()
            var tahunSelesai3 = edtTahunSelesai3.text.toString()

            beasiswaPresenter?.tambahBeasiswa(id,jenisBeasiswa1,keterangan1,tahunMulai1,tahunSelesai1,jenisBeasiswa2,keterangan2,tahunMulai2,tahunSelesai2,jenisBeasiswa3,keterangan3,tahunMulai3,tahunSelesai3)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtBeasiswa.startAnimation(left_to_right)
        spinnerJenisPrestasi1.startAnimation(left_to_right)
        edtKeterangan1.startAnimation(left_to_right)
        edtTahunMulai1.startAnimation(left_to_right)
        edtTahunSelesai1.startAnimation(left_to_right)
        spinnerJenisPrestasi2.startAnimation(left_to_right)
        edtKeterangan2.startAnimation(left_to_right)
        edtTahunMulai2.startAnimation(left_to_right)
        edtTahunSelesai2.startAnimation(left_to_right)
        spinnerJenisPrestasi3.startAnimation(left_to_right)
        edtKeterangan3.startAnimation(left_to_right)
        edtTahunMulai3.startAnimation(left_to_right)
        edtTahunSelesai3.startAnimation(left_to_right)
        ketJenisPrestasi.startAnimation(left_to_right)
        ketKeterangan.startAnimation(left_to_right)
        ketTahunMulai.startAnimation(left_to_right)
        ketTahunSelesai.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    //todo 8 (tambahBeasiswa) implementasi presenter
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
        toast("Tidak boleh ada yang kosong")
    }
    fun toast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}