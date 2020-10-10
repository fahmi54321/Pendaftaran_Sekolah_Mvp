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
import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemLogin
import com.example.pendaftaransekolahmvp.presenter.login.LoginPresenter
import com.example.pendaftaransekolahmvp.presenter.login.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var top_to_bottom : Animation
    lateinit var bottom_to_top : Animation
    //todo 6 (login) deklarasi presenter
    var loginPresenter:LoginPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)

        txtSign.startAnimation(top_to_bottom)
        txtIn.startAnimation(top_to_bottom)
        txtPleaseEnter.startAnimation(top_to_bottom)
        edtemailAddress.startAnimation(bottom_to_top)
        edtpassword.startAnimation(bottom_to_top)
        txtForgotPassword.startAnimation(bottom_to_top)
        bttnSignin.startAnimation(bottom_to_top)
        txtDontHaveAccount.startAnimation(bottom_to_top)
        txtSignup.startAnimation(bottom_to_top)

        //todo 7 (login) inisialiasi presenter
        loginPresenter = LoginPresenter(this)

        txtSignup.setOnClickListener {
            val intent = Intent(this,
                RegisterActivity::class.java)
            startActivity(intent)
        }

        bttnSignin.setOnClickListener {

            bttnSignin.text = "LOADING..."
            bttnSignin.isEnabled = false
            edtemailAddress.isEnabled = false
            edtpassword.isEnabled = false
            progressBar.visibility = View.VISIBLE
            bttnSignin.setBackgroundColor(Color.rgb(181, 76, 67))

            //todo 1 (login) ambil view
            var email_address = edtemailAddress.text.toString()
            var password = edtpassword.text.toString()

            //todo 9 (login) eksekusi presenter
            loginPresenter?.login(email_address,password)
        }
    }

    //todo 8 (login) implementasi presenter
    override fun onSuccess(message: String,results: List<ResultsItemLogin?>?) {
        toast("Berhasil")
        val intent = Intent(this,
            HomeActivity::class.java)
        for (i:Int in results?.indices?:0..1)
        {
            intent.putExtra("id_user", results?.get(i)?.id_user)
        }
        startActivity(intent)
        finishAffinity()
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    override fun inputKosong() {
        toast("Tidak boleh data kosong")
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun hideLoading() {
        bttnSignin.text = "SIGN IN"
        bttnSignin.isEnabled = true
        edtemailAddress.isEnabled = true
        edtpassword.isEnabled = true
        bttnSignin.setBackgroundColor(Color.rgb(245,112,100))
    }

    fun toast(message: String)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

}