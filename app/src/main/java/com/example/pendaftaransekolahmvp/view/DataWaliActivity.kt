package com.example.pendaftaransekolahmvp.view

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.pendaftaransekolahmvp.R
import com.example.pendaftaransekolahmvp.presenter.wali.WaliPresenter
import com.example.pendaftaransekolahmvp.presenter.wali.WaliView
import kotlinx.android.synthetic.main.activity_data_wali.*
import kotlinx.android.synthetic.main.activity_data_wali.bttnSimpan
import kotlinx.android.synthetic.main.activity_data_wali.edtTanggalLahir
import kotlinx.android.synthetic.main.activity_data_wali.idUser
import kotlinx.android.synthetic.main.activity_data_wali.imgTanggal
import kotlinx.android.synthetic.main.activity_data_wali.ketPekerjaan
import kotlinx.android.synthetic.main.activity_data_wali.ketPendidikan
import kotlinx.android.synthetic.main.activity_data_wali.ketTahunLahir
import kotlinx.android.synthetic.main.activity_data_wali.progressBar6
import kotlinx.android.synthetic.main.activity_data_wali.rb1Juta_2Juta
import kotlinx.android.synthetic.main.activity_data_wali.rb2Juta_5Juta
import kotlinx.android.synthetic.main.activity_data_wali.rb5000_1Juta
import kotlinx.android.synthetic.main.activity_data_wali.rb5Juta_20Juta
import kotlinx.android.synthetic.main.activity_data_wali.rbKurang500
import kotlinx.android.synthetic.main.activity_data_wali.rbLebih20Juta
import kotlinx.android.synthetic.main.activity_data_wali.spinnerPekerjaan
import kotlinx.android.synthetic.main.activity_data_wali.spinnerPendidikan
import kotlinx.android.synthetic.main.activity_data_wali.txtPekerjaan
import kotlinx.android.synthetic.main.activity_data_wali.txtPendidikan
import kotlinx.android.synthetic.main.activity_data_wali.txtTahunLahir
import java.text.SimpleDateFormat
import java.util.*

class DataWaliActivity : AppCompatActivity(), WaliView {

    //todo 6 (wali) deklarasi presenter
    var waliPresenter:WaliPresenter?=null

    var datePickerDialog: DatePickerDialog? = null
    var dateFormatter: SimpleDateFormat? = null

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_wali)

        // animasi
        anim()

        //todo 7 (wali) inisialisasi presenter
        waliPresenter = WaliPresenter(this)

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {
            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar6.visibility = View.VISIBLE

            //todo 1 (wali) ambil view
            var id = idUser.text.toString()
            var namaWali = edtNamaWali.text.toString()
            var nikWali = edtNikWali.text.toString()
            var tahunLahirWali = edtTanggalLahir.text.toString()
            var pendidikanWali = spinnerPendidikan.selectedItem.toString()
            var pekerjaanWali = spinnerPekerjaan.selectedItem.toString()
            var penghasilanBulananWali = ""
            if (rbKurang500.isChecked)
            {
                penghasilanBulananWali = "Kurang dari 500.000"
            }
            if (rb5000_1Juta.isChecked)
            {
                penghasilanBulananWali = "500.000 - 1.000.000"
            }
            if (rb1Juta_2Juta.isChecked)
            {
                penghasilanBulananWali = "1.000.000 - 1.999.999"
            }
            if (rb2Juta_5Juta.isChecked)
            {
                penghasilanBulananWali = "2.000.000 - 4.999.999"
            }
            if (rb5Juta_20Juta.isChecked)
            {
                penghasilanBulananWali = "5.000.000 - 19.999.999"
            }
            if (rbLebih20Juta.isChecked)
            {
                penghasilanBulananWali = "Lebih dari 20 Juta"
            }

            // todo 9 (wali) eksekusi presenter
            waliPresenter?.tambahWali(id,namaWali,nikWali,tahunLahirWali,pendidikanWali,pekerjaanWali,penghasilanBulananWali)
        }

        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        imgTanggal.setOnClickListener(View.OnClickListener { showTanggal() })
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtNamaWali.startAnimation(left_to_right)
        edtNamaWali.startAnimation(left_to_right)
        ketNamaWali.startAnimation(left_to_right)
        txtNikWali.startAnimation(left_to_right)
        edtNikWali.startAnimation(left_to_right)
        ketNikWali.startAnimation(left_to_right)
        txtTahunLahir.startAnimation(left_to_right)
        imgTanggal.startAnimation(left_to_right)
        edtTanggalLahir.startAnimation(left_to_right)
        txtPendidikan.startAnimation(left_to_right)
        spinnerPendidikan.startAnimation(left_to_right)
        ketPendidikan.startAnimation(left_to_right)
        txtPekerjaan.startAnimation(left_to_right)
        spinnerPekerjaan.startAnimation(left_to_right)
        ketPekerjaan.startAnimation(left_to_right)
        txtPenghasilanBulananWali.startAnimation(left_to_right)
        rbKurang500.startAnimation(left_to_right)
        rb5000_1Juta.startAnimation(left_to_right)
        rb1Juta_2Juta.startAnimation(left_to_right)
        rb2Juta_5Juta.startAnimation(left_to_right)
        rb5Juta_20Juta.startAnimation(left_to_right)
        rbLebih20Juta.startAnimation(left_to_right)
        ketPenghasilanBulananWali.startAnimation(left_to_right)
        ketTahunLahir.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    private fun showTanggal() {
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[year, monthOfYear] = dayOfMonth
                edtTanggalLahir.setText(dateFormatter!!.format(newDate.time))
            },
            newCalendar[Calendar.YEAR],
            newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog?.show()
    }

    //todo 8 (wali) implementasi presenter
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
        progressBar6.visibility = View.GONE
    }

    override fun inputKosong() {
        toast("Data tidak boleh kosong")
    }
    fun toast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}