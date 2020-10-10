package com.example.pendaftaransekolahmvp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pendaftaransekolahmvp.R
import kotlinx.android.synthetic.main.activity_success_isi_form.*

class SuccessIsiFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_isi_form)

        val bundle = intent.extras
        txtId.text = bundle?.getString("idUser")

        bttnHome.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("id_user",txtId.text.toString())
            startActivity(intent)
        }

        bttnLanjutkan.setOnClickListener {
            val intent = Intent(this,
                PribadiTigaActivity::class.java)
            intent.putExtra("id",txtId.text.toString())
            startActivity(intent)
        }
    }
}