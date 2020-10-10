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
import com.example.pendaftaransekolahmvp.presenter.prestasi.PrestasiPresenter
import com.example.pendaftaransekolahmvp.presenter.prestasi.PrestasiView
import kotlinx.android.synthetic.main.activity_data_prestasi.*
import kotlinx.android.synthetic.main.activity_data_prestasi.bttnSimpan
import kotlinx.android.synthetic.main.activity_data_prestasi.idUser

class DataPrestasiActivity : AppCompatActivity(), PrestasiView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    //todo 6 (tambahPrestasi) deklarasi presenter
    var prestasiPresenter:PrestasiPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_prestasi)

        // animasi
        anim()

        //todo 7 (tambahPrestasi) inisialisasi presenter
        prestasiPresenter = PrestasiPresenter(this)

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar2.visibility = View.VISIBLE

            //todo 1 (tambahPrestasi) ambil view
            var id = idUser.text.toString()
            var jenisPrestasi = ""
            if (cbSains.isChecked)
            {
                jenisPrestasi += cbSains.text.toString()+", "
            }
            if (cbSeni.isChecked)
            {
                jenisPrestasi += cbSeni.text.toString()+", "
            }
            if (cbOlahraga.isChecked)
            {
                jenisPrestasi += cbOlahraga.text.toString()+", "
            }
            if (cbLainLain.isChecked)
            {
                jenisPrestasi += cbLainLain.text.toString()+", "
            }
            var tingkat = spinnerTingkatPrestasi.selectedItem.toString()
            var namaPrestasi1 = edtNamaPrestasi1.text.toString()
            var tahun1 = edtTahun1.text.toString()
            var penyelenggara1 = edtPenyelenggara1.text.toString()

            var namaPrestasi2 = edtNamaPrestasi2.text.toString()
            var tahun2 = edtTahun2.text.toString()
            var penyelenggara2 = edtPenyelenggara2.text.toString()

            var namaPrestasi3 = edtNamaPrestasi3.text.toString()
            var tahun3 = edtTahun3.text.toString()
            var penyelenggara3 = edtPenyelenggara3.text.toString()

            //todo 9 (tambahPrestasi) eksekusi presenter
            prestasiPresenter?.tambahPrestasi(id,jenisPrestasi,tingkat,namaPrestasi1,tahun1,penyelenggara1,namaPrestasi2,tahun2,penyelenggara2,namaPrestasi3,tahun3,penyelenggara3)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtJenisPrestasi.startAnimation(left_to_right)
        cbSains.startAnimation(left_to_right)
        cbSeni.startAnimation(left_to_right)
        cbOlahraga.startAnimation(left_to_right)
        cbLainLain.startAnimation(left_to_right)
        ketJenisPrestasi.startAnimation(left_to_right)
        txtTingkat.startAnimation(left_to_right)
        spinnerTingkatPrestasi.startAnimation(left_to_right)
        ketTingkat.startAnimation(left_to_right)
        txtPrestasi.startAnimation(left_to_right)
        edtNamaPrestasi1.startAnimation(left_to_right)
        edtTahun1.startAnimation(left_to_right)
        edtPenyelenggara1.startAnimation(left_to_right)
        edtNamaPrestasi2.startAnimation(left_to_right)
        edtTahun2.startAnimation(left_to_right)
        edtPenyelenggara2.startAnimation(left_to_right)
        edtNamaPrestasi3.startAnimation(left_to_right)
        edtTahun3.startAnimation(left_to_right)
        edtPenyelenggara3.startAnimation(left_to_right)

        ketNamaPrestasi.startAnimation(left_to_right)
        ketTahunPrestasi.startAnimation(left_to_right)
        ketPenyelenggara.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    //todo 8 (tambahPrestasi) implementasi presenter

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