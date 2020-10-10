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
import com.example.pendaftaransekolahmvp.presenter.kontak.KontakPresenter
import com.example.pendaftaransekolahmvp.presenter.kontak.KontakView
import kotlinx.android.synthetic.main.activity_data_kontak.*
import kotlinx.android.synthetic.main.activity_data_kontak.bttnSimpan
import kotlinx.android.synthetic.main.activity_data_kontak.idUser
import kotlinx.android.synthetic.main.activity_data_kontak.progressBar6
import kotlinx.android.synthetic.main.activity_data_wali.*

class DataKontakActivity : AppCompatActivity(), KontakView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    // todo 6 (tambahKontak) deklarasi presenter
    var kontakPresenter:KontakPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_kontak)

        // animasi
        anim()

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        // todo 7 (tambahKontak) inisialisasi presenter
        kontakPresenter = KontakPresenter(this)

        bttnSimpan.setOnClickListener {
            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar6.visibility = View.VISIBLE

            // todo 1 (tambahKontak) ambil view
            var id = idUser.text.toString()
            var noTelpRumah = edtNoTelpRumah.text.toString()
            var noHp = edtNoHp.text.toString()
            var emailAddress = edtEmailAddress.text.toString()

            // todo 9 (tambahKontak) eksekusi presenter
            kontakPresenter?.tambahKontak(id,noTelpRumah, noHp, emailAddress)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtNoTelpRumah.startAnimation(left_to_right)
        edtNoTelpRumah.startAnimation(left_to_right)
        ketNoTelp.startAnimation(left_to_right)
        txtNoHp.startAnimation(left_to_right)
        edtNoHp.startAnimation(left_to_right)
        ketNoHp.startAnimation(left_to_right)
        txtEmailAddress.startAnimation(left_to_right)
        edtEmailAddress.startAnimation(left_to_right)
        ketEmailAddress.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    // todo 8 (tambahKontak) implementasi presenter
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