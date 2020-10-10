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
import com.example.pendaftaransekolahmvp.presenter.pribadilima.PribadiLimaPresenter
import com.example.pendaftaransekolahmvp.presenter.pribadilima.PribadiLimaView
import kotlinx.android.synthetic.main.activity_pribadi_dua.*
import kotlinx.android.synthetic.main.activity_pribadi_empat.*
import kotlinx.android.synthetic.main.activity_pribadi_lima.*
import kotlinx.android.synthetic.main.activity_pribadi_lima.bttnSimpan
import kotlinx.android.synthetic.main.activity_pribadi_lima.idUser

class PribadiLimaActivity : AppCompatActivity(), PribadiLimaView {

    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation

    // todo 6 (pribadiLima) deklarasi presenter
    var pribadiLimaPresenter:PribadiLimaPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pribadi_lima)

        // animasi
        anim()

        // todo 7 (pribadiLima) inisialisasi presenter
        pribadiLimaPresenter = PribadiLimaPresenter(this)

        val bundle = intent.extras
        idUser.text = bundle?.getString("id")

        bttnSimpan.setOnClickListener {

            bttnSimpan.text="LOADING..."
            bttnSimpan.isEnabled = false
            bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67))
            progressBar5.visibility = View.VISIBLE

            // todo 1 (pribadiLima) ambil view
            //id
            var id = idUser.text.toString()

            //usulanDariSekolah
            var usulanDariSekolah = ""
            if (rbYaUsulan.isChecked)
            {
                usulanDariSekolah = "Ya"
            }
            if (rbTidakUsulan.isChecked)
            {
                usulanDariSekolah = "Tidak"
            }

            //penerimaKip
            var penerimaKip = ""
            if (rbYaPenerima.isChecked)
            {
                penerimaKip = "Ya"
            }
            if (rbTidakPenerima.isChecked)
            {
                penerimaKip = "Tidak"
            }

            //nomorKip
            var nomorKip = edtNomorKip.text.toString()

            //Nama Kip
            var namaTerteraKip = NamaTerteraKip.text.toString()

            //terima fisik kip
            var terimaFisikKip = ""
            if (rbYaTerimaFisik.isChecked)
            {
                terimaFisikKip = "Ya"
            }
            if (rbTidakTerimaFisik.isChecked)
            {
                terimaFisikKip = "Tidak"
            }

            //Alasan menerima kip
            var alasanMenerimaKip=""
            if (rbPemegangPkh.isChecked)
            {
                alasanMenerimaKip = "Pemegang PKH/KPS/KIP"
            }
            if (rbPenerimaBSM.isChecked)
            {
                alasanMenerimaKip = "Pemegang BSM"
            }
            if (rbYatimPiatu.isChecked)
            {
                alasanMenerimaKip = "Yatim Piatu/Panti Asuhan/Panti Sosial"
            }
            if (rbDampakBencanaAlam.isChecked)
            {
                alasanMenerimaKip = "Dampak Bencana Alam"
            }
            if (rbPernahDropOut.isChecked)
            {
                alasanMenerimaKip = "Pernah Drop OUT"
            }
            if (rbSiswaMiskin.isChecked)
            {
                alasanMenerimaKip = "Siswa Miskin/Rentan Miskin"
            }
            if (rbDaerahKonflik.isChecked)
            {
                alasanMenerimaKip = "Daerah Konflik"
            }
            if (rbKeluargaTerpidana.isChecked)
            {
                alasanMenerimaKip="Keluarga Terpidana"
            }
            if (rbKelainanFisik.isChecked)
            {
                alasanMenerimaKip = "Kelainan Fisik"
            }

            //bank
            var bank = edtBank.text.toString()

            //no rek
            var noRekBank = edtNoRekBank.text.toString()

            //nama Rekening
            var namaRekening = edtRekeningAtasNama.text.toString()

            // todo 9 (pribadiLima) eksekusi presenter
            pribadiLimaPresenter?.tambahPribadiLima(id,usulanDariSekolah,penerimaKip,nomorKip,namaTerteraKip,terimaFisikKip,alasanMenerimaKip,bank,noRekBank,namaRekening)
        }
    }

    private fun anim() {
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)

        txtUsulanSekolah.startAnimation(left_to_right)
        rbYaUsulan.startAnimation(left_to_right)
        rbTidakUsulan.startAnimation(left_to_right)
        ketUsulanSekolah.startAnimation(left_to_right)
        txtPenerimaKip.startAnimation(left_to_right)
        rbYaPenerima.startAnimation(left_to_right)
        rbTidakPenerima.startAnimation(left_to_right)
        PenerimaKip.startAnimation(left_to_right)
        txtNomorKip.startAnimation(left_to_right)
        edtNomorKip.startAnimation(left_to_right)
        ketNomorKip.startAnimation(left_to_right)
        txtNamaKip.startAnimation(left_to_right)
        NamaTerteraKip.startAnimation(left_to_right)
        ketNamaKip.startAnimation(left_to_right)
        txtTerimaFisikKip.startAnimation(left_to_right)
        rbYaTerimaFisik.startAnimation(left_to_right)
        rbTidakTerimaFisik.startAnimation(left_to_right)
        ketTerimaFisikKip.startAnimation(left_to_right)
        txtAlasanMenerimaKip.startAnimation(left_to_right)
        rbPemegangPkh.startAnimation(left_to_right)
        rbPenerimaBSM.startAnimation(left_to_right)
        rbYatimPiatu.startAnimation(left_to_right)
        rbDampakBencanaAlam.startAnimation(left_to_right)
        rbPernahDropOut.startAnimation(left_to_right)
        rbSiswaMiskin.startAnimation(left_to_right)
        rbDaerahKonflik.startAnimation(left_to_right)
        rbKeluargaTerpidana.startAnimation(left_to_right)
        rbKelainanFisik.startAnimation(left_to_right)
        ketAlasanMenerimaKip.startAnimation(left_to_right)
        txtBank.startAnimation(left_to_right)
        edtBank.startAnimation(left_to_right)
        ketBank.startAnimation(left_to_right)
        txtNoBank.startAnimation(left_to_right)
        edtNoRekBank.startAnimation(left_to_right)
        ketNoBank.startAnimation(left_to_right)
        txtNamaRekening.startAnimation(left_to_right)
        edtRekeningAtasNama.startAnimation(left_to_right)
        ketNamaRekening.startAnimation(left_to_right)
        bttnSimpan.startAnimation(bottom_to_top)
    }

    // todo 8 (pribadiLima) implementasi presenter
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
        progressBar5.visibility = View.GONE
    }

    fun toast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}