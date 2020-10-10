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
import com.example.pendaftaransekolahmvp.presenter.ibukandung.IbuKandungPresenter
import com.example.pendaftaransekolahmvp.presenter.ibukandung.IbuKandungView
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.*
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.bttnSimpan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbAutis
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbBakatIstimewa
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbCerdasIstimewa
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbDeksaRingan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbDeksaSedang
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbDownSindrome
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbGrahitaRingan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbHiperAktif
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbIndigo
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbKesulitanBelajar
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbLaras
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbNarkoba
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbNetra
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbRungu
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbTidak
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbTunaGanda
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.cbWicara
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.edtTanggalLahir
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.idUser
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.imgTanggal
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.ketPekerjaan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.ketPendidikan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.ketPenghasilanBulanan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.ketTahunLahir
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.progressBar6
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.rb1Juta_2Juta
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.rb2Juta_5Juta
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.rb5000_1Juta
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.rb5Juta_20Juta
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.rbKurang500
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.rbLebih20Juta
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.spinnerPekerjaan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.spinnerPendidikan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.txtPekerjaan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.txtPendidikan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.txtPenghasilanBulanan
import kotlinx.android.synthetic.main.activity_data_ibu_kandung.txtTahunLahir
import java.text.SimpleDateFormat
import java.util.*

class DataIbuKandungActivity : AppCompatActivity(), IbuKandungView {
    // todo 6 (ibu kandung) deklarasi presenter
    var ibuKandungPresenter:IbuKandungPresenter?=null

    var datePickerDialog: DatePickerDialog? = null
    var dateFormatter: SimpleDateFormat? = null

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_ibu_kandung)

        // animasi
        anim()

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        // todo 7 (ibu kandung) inisialisasi presenter
        ibuKandungPresenter = IbuKandungPresenter(this)

        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        imgTanggal.setOnClickListener(View.OnClickListener { showTanggal() })

        bttnSimpan.setOnClickListener {
            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar6.visibility = View.VISIBLE

            //todo 1 (ibu kandung) ambil view
            var id = idUser.text.toString()
            var namaIbuKandung = edtNamaIbuKandung.text.toString()
            var nikIbu = edtNikIbu.text.toString()
            var tahunLahirIbu = edtTanggalLahir.text.toString()
            var pendidikanIbu = spinnerPendidikan.selectedItem.toString()
            var pekerjaanIbu = spinnerPekerjaan.selectedItem.toString()
            var penghasilanBulananIbu = ""
            if (rbKurang500.isChecked)
            {
                penghasilanBulananIbu = "Kurang dari 500.000"
            }
            if (rb5000_1Juta.isChecked)
            {
                penghasilanBulananIbu = "500.000 - 1.000.000"
            }
            if (rb1Juta_2Juta.isChecked)
            {
                penghasilanBulananIbu = "1.000.000 - 1.999.999"
            }
            if (rb2Juta_5Juta.isChecked)
            {
                penghasilanBulananIbu = "2.000.000 - 4.999.999"
            }
            if (rb5Juta_20Juta.isChecked)
            {
                penghasilanBulananIbu = "5.000.000 - 19.999.999"
            }
            if (rbLebih20Juta.isChecked)
            {
                penghasilanBulananIbu = "Lebih dari 20 Juta"
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

            // todo 9 (ibu kandung) eksekusi presenter
            ibuKandungPresenter?.tambahIbuKandung(id,namaIbuKandung,nikIbu,tahunLahirIbu,pendidikanIbu,pekerjaanIbu,penghasilanBulananIbu,kebutuhanKhusus)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtNamaIbu.startAnimation(left_to_right)
        edtNamaIbuKandung.startAnimation(left_to_right)
        ketNamaIbu.startAnimation(left_to_right)
        txtNikIbu.startAnimation(left_to_right)
        edtNikIbu.startAnimation(left_to_right)
        ketNikIbu.startAnimation(left_to_right)
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
        txtBerkebutuhanKhususIbu.startAnimation(left_to_right)
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
        ketBerkebutuhanKhususIbu.startAnimation(left_to_right)
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

    // todo 8 (ibu kandung) implementasi presenter
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