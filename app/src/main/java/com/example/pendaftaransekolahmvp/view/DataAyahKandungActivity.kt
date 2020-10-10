package com.example.pendaftaransekolahmvp.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pendaftaransekolahmvp.R
import com.example.pendaftaransekolahmvp.presenter.ayahkandung.AyahKandungView
import com.example.pendaftaransekolahmvp.presenter.ayahkandung.AyahkandungPresenter
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.*
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.bttnSimpan
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbAutis
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbBakatIstimewa
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbCerdasIstimewa
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbDeksaRingan
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbDeksaSedang
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbDownSindrome
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbGrahitaRingan
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbHiperAktif
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbIndigo
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbKesulitanBelajar
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbLaras
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbNarkoba
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbNetra
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbRungu
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbTidak
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbTunaGanda
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.cbWicara
import kotlinx.android.synthetic.main.activity_data_ayah_kandung.idUser
import java.text.SimpleDateFormat
import java.util.*

class DataAyahKandungActivity : AppCompatActivity(), AyahKandungView {

    var datePickerDialog: DatePickerDialog? = null
    var dateFormatter: SimpleDateFormat? = null

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    //todo 6 (ayah kandung) deklarasi presenter
    var ayahkandungPresenter:AyahkandungPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_ayah_kandung)

        // animasi
        anim()

        //todo 7 (ayah kandung) inisialisasi presenter
        ayahkandungPresenter = AyahkandungPresenter(this)

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)

        imgTanggal.setOnClickListener(View.OnClickListener { showTanggal() })

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar6.visibility = View.VISIBLE

            //todo 1 (ayah kandung) ambil view
            var id = idUser.text.toString()
            var namaAyahKandung = edtNamaAyahKandung.text.toString()
            var nikAyah = edtNikAyah.text.toString()
            var tahunLahirAyah = edtTanggalLahir.text.toString()
            var pendidikanAyah = spinnerPendidikan.selectedItem.toString()
            var pekerjaanAyah = spinnerPekerjaan.selectedItem.toString()
            var penghasilanBulananAyah = ""
            if (rbKurang500.isChecked)
            {
                penghasilanBulananAyah = "Kurang dari 500.000"
            }
            if (rb5000_1Juta.isChecked)
            {
                penghasilanBulananAyah = "500.000 - 1.000.000"
            }
            if (rb1Juta_2Juta.isChecked)
            {
                penghasilanBulananAyah = "1.000.000 - 1.999.999"
            }
            if (rb2Juta_5Juta.isChecked)
            {
                penghasilanBulananAyah = "2.000.000 - 4.999.999"
            }
            if (rb5Juta_20Juta.isChecked)
            {
                penghasilanBulananAyah = "5.000.000 - 19.999.999"
            }
            if (rbLebih20Juta.isChecked)
            {
                penghasilanBulananAyah = "Lebih dari 20 Juta"
            }

            var kebutuhanKhusus:String=""
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


            //todo 9 (ayah kandung) eksekusi presenter
            ayahkandungPresenter?.tambahAyahKandung(id,namaAyahKandung,nikAyah,tahunLahirAyah,pendidikanAyah,pekerjaanAyah,penghasilanBulananAyah,kebutuhanKhusus)

        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtNamaAyah.startAnimation(left_to_right)
        edtNamaAyahKandung.startAnimation(left_to_right)
        ketNamaAyah.startAnimation(left_to_right)
        txtNikAyah.startAnimation(left_to_right)
        edtNikAyah.startAnimation(left_to_right)
        ketNikAyah.startAnimation(left_to_right)
        txtTahunLahir.startAnimation(left_to_right)
        imgTanggal.startAnimation(left_to_right)
        edtTanggalLahir.startAnimation(left_to_right)
        txtPendidikan.startAnimation(left_to_right)
        spinnerPendidikan.startAnimation(left_to_right)
        ketPendidikan.startAnimation(left_to_right)
        txtPekerjaan.startAnimation(left_to_right)
        spinnerPekerjaan.startAnimation(left_to_right)
        ketPekerjaan.startAnimation(left_to_right)
        txtPenghasilanBulanan.startAnimation(left_to_right)
        rbKurang500.startAnimation(left_to_right)
        rb5000_1Juta.startAnimation(left_to_right)
        rb1Juta_2Juta.startAnimation(left_to_right)
        rb2Juta_5Juta.startAnimation(left_to_right)
        rb5Juta_20Juta.startAnimation(left_to_right)
        rbLebih20Juta.startAnimation(left_to_right)
        ketPenghasilanBulanan.startAnimation(left_to_right)
        txtBerkebutuhanKhususayah.startAnimation(left_to_right)
        cbTidak.startAnimation(left_to_right)
        cbNetra.startAnimation(left_to_right)
        cbIndigo.startAnimation(left_to_right)
        cbBakatIstimewa.startAnimation(left_to_right)
        cbNarkoba.startAnimation(left_to_right)
        cbAutis.startAnimation(left_to_right)
        cbTunaGanda.startAnimation(left_to_right)
        ketTahunLahir.startAnimation(left_to_right)

        cbHiperAktif.startAnimation(left_to_right)
        cbLaras.startAnimation(left_to_right)
        cbCerdasIstimewa.startAnimation(left_to_right)
        cbKesulitanBelajar.startAnimation(left_to_right)
        cbRungu.startAnimation(left_to_right)
        cbDownSindrome.startAnimation(left_to_right)
        cbDeksaRingan.startAnimation(left_to_right)
        cbDeksaSedang.startAnimation(left_to_right)
        cbWicara.startAnimation(left_to_right)
        cbGrahitaRingan.startAnimation(left_to_right)
        ketBerkebutuhanKhususAyah.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    private fun showTanggal() {
        val newCalendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
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

    //todo 8 (ayah kandung) implementasi presenter
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
        toast("Tidak boleh ada yang kosong")
    }
    fun toast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}