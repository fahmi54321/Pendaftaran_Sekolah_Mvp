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
import com.example.pendaftaransekolahmvp.presenter.dataperiodik.DataPeriodikPresenter
import com.example.pendaftaransekolahmvp.presenter.dataperiodik.DataPeriodikView
import kotlinx.android.synthetic.main.activity_data_periodik.*
import kotlinx.android.synthetic.main.activity_data_periodik.bttnSimpan

class DataPeriodikActivity : AppCompatActivity(), DataPeriodikView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    //todo 6 (dataPeriodik) deklarasi pressenter
    var dataPeriodikPresenter:DataPeriodikPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_periodik)

        // animasi
        anim()

        //todo 6 (dataPeriodik) inisiaslisasi pressenter
        dataPeriodikPresenter = DataPeriodikPresenter(this)
        val bundle = intent.extras
        idUserDataPeriodik.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar2.visibility = View.VISIBLE

            //todo 1 (dataPeriodik) ambil view
            var id = idUserDataPeriodik.text.toString()
            var tinggiBadan = edtTinggiBadan.text.toString()
            var beratBadan = edtBeratBadan.text.toString()
            var jarakTempatTinggal = ""
            if (rbKurang1Km.isChecked)
            {
                jarakTempatTinggal = "Kurang dari 1 km"
            }
            if (rbLebih1Km.isChecked)
            {
                jarakTempatTinggal = "Lebih dari 1 km"
            }
            var km = edtKm.text.toString()
            var waktuTemputJam = waktuTempuhJam.text.toString()
            var waktuTemputMenit = waktuTempuhMenit.text.toString()
            var waktuTempuhSekolah = ""
            var jmlSaudaraKandung = edtJmlSaudaraKandung.text.toString()

            //todo 8 (dataPeriodik) implementasi pressenter
            dataPeriodikPresenter?.tambahDataPeriodik(id,tinggiBadan,beratBadan,jarakTempatTinggal,km,waktuTemputJam,waktuTemputMenit,waktuTempuhSekolah,jmlSaudaraKandung)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtTinggiBadan.startAnimation(left_to_right)
        edtTinggiBadan.startAnimation(left_to_right)
        txtCm.startAnimation(left_to_right)
        txtBeratBadan.startAnimation(left_to_right)
        edtBeratBadan.startAnimation(left_to_right)
        txtKg.startAnimation(left_to_right)
        ketBeratBadan.startAnimation(left_to_right)
        txtJarakTempatTinggal.startAnimation(left_to_right)
        rbKurang1Km.startAnimation(left_to_right)
        rbLebih1Km.startAnimation(left_to_right)
        ketJarakTempatTinggal.startAnimation(left_to_right)
        txtSebutkan.startAnimation(left_to_right)
        edtKm.startAnimation(left_to_right)
        txtKm.startAnimation(left_to_right)
        ketSebutkan.startAnimation(left_to_right)
        txtWaktuTempuh.startAnimation(left_to_right)
        waktuTempuhJam.startAnimation(left_to_right)
        txtJam.startAnimation(left_to_right)
        waktuTempuhMenit.startAnimation(left_to_right)
        txtMenit.startAnimation(left_to_right)
        ketWaktuTempuh.startAnimation(left_to_right)
        txtJmlSaudara.startAnimation(left_to_right)
        edtJmlSaudaraKandung.startAnimation(left_to_right)
        ketJmlSaudara.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    //todo 7 (dataPeriodik) implementasi pressenter
    override fun onSuccess(message: String) {
        toast("Berhasil")
        val intent = Intent(this,SuccessIsiFormActivity::class.java)
        intent.putExtra("idUser",idUserDataPeriodik.text.toString())
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