package com.example.pendaftaransekolahmvp.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pendaftaransekolahmvp.*
import com.example.pendaftaransekolahmvp.adapter.HomeAdapter
import com.example.pendaftaransekolahmvp.model.java.ResponsePribadiSatu
import com.example.pendaftaransekolahmvp.model.kotlin.*
import com.example.pendaftaransekolahmvp.network.kotlin.ConfigNetwork
import com.example.pendaftaransekolahmvp.presenter.home.*
import com.example.pendaftaransekolahmvp.presenter.home.selectAyahKandung.SelectAyahKandungPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectAyahKandung.SelectAyahKandungView
import com.example.pendaftaransekolahmvp.presenter.home.selectDataBeasiswa.SelectDataBeasiswaPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectDataBeasiswa.SelectDataBeasiswaView
import com.example.pendaftaransekolahmvp.presenter.home.selectDataPeriodik.SelectDataPeriodikPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectDataPeriodik.SelectDataPeriodikView
import com.example.pendaftaransekolahmvp.presenter.home.selectDataPrestasi.SelectDataPrestasiPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectDataPrestasi.SelectDataPrestasiView
import com.example.pendaftaransekolahmvp.presenter.home.selectIbuKandung.SelectIbuKandungPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectIbuKandung.SelectIbuKandungView
import com.example.pendaftaransekolahmvp.presenter.home.selectKontak.SelectKontakPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectKontak.SelectKontakView
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiDua.SelectPribadiDuaPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiDua.SelectPribadiDuaView
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiEmpat.SelectPribadiEmpatPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiEmpat.SelectPribadiEmpatView
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiLima.SelectPribadiLimaPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiLima.SelectPribadiLimaView
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiSatu.SelectPribadiSatuPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiSatu.SelectPribadiSatuView
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiTiga.selectPribadiTigaPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectPribadiTiga.selectPribadiTigaView
import com.example.pendaftaransekolahmvp.presenter.home.selectWali.SelectWaliPresenter
import com.example.pendaftaransekolahmvp.presenter.home.selectWali.SelectWaliView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.kartu_pribadi_satu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeActivity : AppCompatActivity(), HomeView, PhotoView, SelectPribadiSatuView,
    SelectPribadiDuaView, selectPribadiTigaView, SelectPribadiEmpatView, SelectPribadiLimaView,
    SelectAyahKandungView, SelectIbuKandungView, SelectWaliView, SelectKontakView,
    SelectDataPeriodikView, SelectDataPrestasiView, SelectDataBeasiswaView {

    lateinit var top_to_bottom : Animation
    lateinit var bottom_to_top : Animation
    lateinit var left_to_right : Animation
    lateinit var right_to_left : Animation
    lateinit var scale_animation : Animation

    //todo 5 (selectPribadiDua) deklarasi presenter
    var selectPribadiDuaPresenter: SelectPribadiDuaPresenter?=null

    //todo 5 (selectPribadiSatu) deklarasi presenter
    var selectPribadiSatuPresenter: SelectPribadiSatuPresenter?=null

    //todo 6 (getFotoProfile) deklarasi presenter
    var photoPresenter: PhotoPresenter? = null

    //todo 6 (login) deklarasi presenter
    var homePresenter: HomePresenter? = null

    //todo 5 (selectPribadiTiga) deklarasi presenter
    var selectPribadiTigaPresenter:selectPribadiTigaPresenter?=null

    //todo 5 (selectPribadiEmpat) deklarasi presenter
    var selectPribadiEmpatPresenter:SelectPribadiEmpatPresenter?=null

    //todo 5 (selectPribadiLima) deklarasi presenter
    var selectPribadiLimaPresenter:SelectPribadiLimaPresenter?=null

    // todo 5 (selectAyahKandung) deklarasi presenter
    var selectAyahKandungPresenter:SelectAyahKandungPresenter?=null

    // todo 5 (selectIbuKandung) deklarasi presenter
    var selectIbuKandungPresenter:SelectIbuKandungPresenter?=null

    //todo 5 (selectWali) deklarasi presenter
    var selectWaliPresenter : SelectWaliPresenter?=null

    // todo 5 (selectKontak) deklarasi presenter
    var selectKontakPresenter:SelectKontakPresenter?=null

    //todo 5 (selectDataPeriodik) deklarasi presenter
    var selectDataPeriodikPresenter:SelectDataPeriodikPresenter?=null

    //todo 5 (selectDataPrestasi) deklarasi presenter
    var selectDataPrestasiPrestasi:SelectDataPrestasiPresenter?=null

    //todo 5 (selectDataBeasiswa) deklarasi presenter
    var selectDataBeasiswaPresenter: SelectDataBeasiswaPresenter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //animasi
        anim()

        val bundle = intent.extras
        id_user.text = bundle?.getString("id_user")

        //todo 1 (getFotoProfile) ambil view
        var idUser = id_user.text.toString()

        //inisialisasi presenter
        inisialisasiPresenter()

        //todo 9 (login) eksekusi presenter
        homePresenter?.getProfile(idUser)

        //todo 9 (getFotoProfile) eksekusi presenter
        photoPresenter?.getPhoto()

        //todo 8 (selectPribadiSatu) eksekusi presenter
        selectPribadiSatuPresenter?.selectPribadiSatu(idUser)

        //todo 8 (selectPribadiDua) eksekusi presenter
        selectPribadiDuaPresenter?.selectPribadiDua(idUser)

        //todo 8 (selectPribadiTiga) eksekusi presenter
        selectPribadiTigaPresenter?.selectPribadiTiga(idUser)

        //todo 8 (selectPribadiEmpat) eksekusi presenter
        selectPribadiEmpatPresenter?.selectPribadiEmpat(idUser)

        //todo 8 (selectPribadiLima) eksekusi presenter
        selectPribadiLimaPresenter?.selectPribadiLima(idUser)

        // todo 8 (selectAyahKandung) eksekusi presenter
        selectAyahKandungPresenter?.selectAyahKandung(idUser)

        // todo 8 (selectIbuKandung) eksekusi presenter
        selectIbuKandungPresenter?.selectIbuKandung(idUser)

        //todo 8 (selectWali) eksekusi presenter
        selectWaliPresenter?.selectWali(idUser)

        // todo 8 (selectKontak) eksekusi presenter
        selectKontakPresenter?.selectKontak(idUser)

        // todo 8 (selectDataPeriodik) eksekusi presenter
        selectDataPeriodikPresenter?.selectDataPeriodik(idUser)

        //todo 8 (selectDataPrestasi) eksekusi presenter
        selectDataPrestasiPrestasi?.selectDataPrestasi(idUser)

        //todo 8 (selectDataBeasiswa) eksekusi presenter
        selectDataBeasiswaPresenter?.selectDataBeasiswa(idUser)

        pribadibagian1.setOnClickListener {
            val intent = Intent(
                this,
                PribadiSatuActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }

        selectpribadibagian1.setOnClickListener {
            showDialogPribadiSatu()
        }

        pribadibagian2.setOnClickListener {
            val intent = Intent(
                this,
                PribadiDuaActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectpribadibagian2.setOnClickListener {
            showDialogPribadiDua()
        }
        pribadibagian3.setOnClickListener {
            val intent = Intent(
                this,
                PribadiTigaActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectpribadibagian3.setOnClickListener {
            showDialogPribadiTiga()
        }
        pribadibagian4.setOnClickListener {
            val intent = Intent(
                this,
                PribadiEmpatActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectpribadibagian4.setOnClickListener {
            showDialogPribadiEmpat()
        }
        pribadibagian5.setOnClickListener {
            val intent = Intent(
                this,
                PribadiLimaActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectpribadibagian5.setOnClickListener {
            showDialogPribadiLima()
        }
        ayahKandung.setOnClickListener {
            val intent = Intent(
                this,
                DataAyahKandungActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectAyahKandung.setOnClickListener {
            showDialogAyahKandung()
        }
        ibuKandung.setOnClickListener {
            val intent = Intent(
                this,
                DataIbuKandungActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectIbuKandung.setOnClickListener {
            showDialogIbuKandung()
        }
        wali.setOnClickListener {
            val intent = Intent(
                this,
                DataWaliActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectWali.setOnClickListener {
            showDialogWali()
        }
        kontak.setOnClickListener {
            val intent = Intent(
                this,
                DataKontakActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectKontak.setOnClickListener {
            showDialogKontak()
        }
        dataPeriodik.setOnClickListener {
            val intent = Intent(
                this,
                DataPeriodikActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectDataPeriodik.setOnClickListener {
            showDialogDataPeriodik()
        }
        prestasi.setOnClickListener {
            val intent = Intent(
                this,
                DataPrestasiActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectDataPrestasi.setOnClickListener {
            showDialogDataPrestasi()
        }
        beasiswa.setOnClickListener {
            val intent = Intent(
                this,
                DataBeasiswaActivity::class.java
            )
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
        selectDataBeasiswa.setOnClickListener {
            showDialogDataBeasiswa()
        }
    }

    private fun showDialogDataBeasiswa() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_beasiswa)


        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val idBeasiswa = dialog.findViewById<TextView>(R.id.idBeasiswa)
        val txtIdBeasiswa = dialog.findViewById<TextView>(R.id.txtIdBeasiswa)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val beasiswa = dialog.findViewById<TextView>(R.id.beasiswa)
        val jenisBeasiswa1 = dialog.findViewById<TextView>(R.id.txtJenisBeasiswa1)
        val keterangan1 = dialog.findViewById<TextView>(R.id.txtKeterangan1)
        val tahunMulai1 = dialog.findViewById<TextView>(R.id.txtTahunMulai1)
        val tahunSelesai1 = dialog.findViewById<TextView>(R.id.txtTahunSelesai1)
        val jenisBeasiswa2 = dialog.findViewById<TextView>(R.id.txtJenisBeasiswa2)
        val keterangan2 = dialog.findViewById<TextView>(R.id.txtKeterangan2)
        val tahunMulai2 = dialog.findViewById<TextView>(R.id.txtTahunMulai2)
        val tahunSelesai2 = dialog.findViewById<TextView>(R.id.txtTahunSelesai2)
        val jenisBeasiswa3 = dialog.findViewById<TextView>(R.id.txtJenisBeasiswa3)
        val keterangan3 = dialog.findViewById<TextView>(R.id.txtKeterangan3)
        val tahunMulai3 = dialog.findViewById<TextView>(R.id.txtTahunMulai3)
        val tahunSelesai3 = dialog.findViewById<TextView>(R.id.txtTahunSelesai3)

        imgLogo.startAnimation(left_to_right)
        idBeasiswa.startAnimation(left_to_right)
        txtIdBeasiswa.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        beasiswa.startAnimation(left_to_right)
        jenisBeasiswa1.startAnimation(left_to_right)
        keterangan1.startAnimation(left_to_right)
        tahunMulai1.startAnimation(left_to_right)
        tahunSelesai1.startAnimation(left_to_right)
        jenisBeasiswa2.startAnimation(left_to_right)
        keterangan2.startAnimation(left_to_right)
        tahunMulai2.startAnimation(left_to_right)
        tahunSelesai2.startAnimation(left_to_right)
        jenisBeasiswa3.startAnimation(left_to_right)
        keterangan3.startAnimation(left_to_right)
        tahunMulai3.startAnimation(left_to_right)
        tahunSelesai3.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectDataBeasiswa(id).enqueue(object : Callback<ResponseDataBeasiswa>{
            override fun onFailure(call: Call<ResponseDataBeasiswa>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ResponseDataBeasiswa>,
                response: Response<ResponseDataBeasiswa>
            ) {
                if (response.isSuccessful)
                {
                    var data=response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdBeasiswa.text = data?.get(i)?.id_user
                            jenisBeasiswa1.text = data?.get(i)?.jenis_beasiswa_dua
                            keterangan1.text = data?.get(i)?.keterangan_satu
                            tahunMulai1.text = data?.get(i)?.tahun_mulai_satu
                            tahunSelesai1.text = data?.get(i)?.tahun_selesai_dua
                            jenisBeasiswa2.text = data?.get(i)?.jenis_beasiswa_dua
                            keterangan2.text = data?.get(i)?.keterangan_dua
                            tahunMulai2.text = data?.get(i)?.tahun_mulai_dua
                            tahunSelesai2.text = data?.get(i)?.tahun_selesai_dua
                            jenisBeasiswa3.text = data?.get(i)?.jenis_beasiswa_tiga
                            keterangan3.text = data?.get(i)?.keterangan_tiga
                            tahunMulai3.text = data?.get(i)?.tahun_mulai_tiga
                            tahunSelesai3.text = data?.get(i)?.tahun_selesai_tiga
                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogDataPrestasi() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_prestasi)


        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val idPrestasi = dialog.findViewById<TextView>(R.id.idPrestasi)
        val txtIdPrestai = dialog.findViewById<TextView>(R.id.txtIdPrestasi)
        val jenisPrestasi = dialog.findViewById<TextView>(R.id.jenisPrestasi)
        val txtJenisPrestasi = dialog.findViewById<TextView>(R.id.txtJenisPrestasi)
        val tingkat = dialog.findViewById<TextView>(R.id.tingkat)
        val txtTingkat = dialog.findViewById<TextView>(R.id.txtTingkat)
        val prestasi = dialog.findViewById<TextView>(R.id.prestasi)
        val txtNamaPrestasi1 = dialog.findViewById<TextView>(R.id.txtNamaPrestasi1)
        val txtTahun1 = dialog.findViewById<TextView>(R.id.txtTahun1)
        val txtPenyelenggara1 = dialog.findViewById<TextView>(R.id.txtPenyelenggara1)
        val txtNamaPrestasi2 = dialog.findViewById<TextView>(R.id.txtNamaPrestasi2)
        val txtTahun2 = dialog.findViewById<TextView>(R.id.txtTahun2)
        val txtPenyelenggara2 = dialog.findViewById<TextView>(R.id.txtPenyelenggara2)
        val txtNamaPrestasi3 = dialog.findViewById<TextView>(R.id.txtNamaPrestasi3)
        val txtTahun3 = dialog.findViewById<TextView>(R.id.txtTahun3)
        val txtPenyelenggara3 = dialog.findViewById<TextView>(R.id.txtPenyelenggara3)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        idPrestasi.startAnimation(left_to_right)
        txtIdPrestai.startAnimation(left_to_right)
        jenisPrestasi.startAnimation(left_to_right)
        txtJenisPrestasi.startAnimation(left_to_right)
        tingkat.startAnimation(left_to_right)
        txtTingkat.startAnimation(left_to_right)
        prestasi.startAnimation(left_to_right)
        txtNamaPrestasi1.startAnimation(left_to_right)
        txtTahun1.startAnimation(left_to_right)
        txtPenyelenggara1.startAnimation(left_to_right)
        txtNamaPrestasi2.startAnimation(left_to_right)
        txtTahun2.startAnimation(left_to_right)
        txtPenyelenggara2.startAnimation(left_to_right)
        txtNamaPrestasi3.startAnimation(left_to_right)
        txtTahun3.startAnimation(left_to_right)
        txtPenyelenggara3.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectDataPrestasi(id).enqueue(object : Callback<ResponseDataPrestasi>{
            override fun onFailure(call: Call<ResponseDataPrestasi>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<ResponseDataPrestasi>,
                response: Response<ResponseDataPrestasi>
            ) {
                if (response.isSuccessful)
                {
                    var data=response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdPrestai.text = data?.get(i)?.id_prestasi
                            txtIdUser.text = data?.get(i)?.id_user
                            txtJenisPrestasi.text = data?.get(i)?.jenis_prestasi
                            txtTingkat.text = data?.get(i)?.tingkat
                            txtNamaPrestasi1.text = data?.get(i)?.nama_prestasi_satu
                            txtTahun1.text = data?.get(i)?.tahun_satu
                            txtPenyelenggara1.text = data?.get(i)?.penyelenggara_satu
                            txtNamaPrestasi2.text = data?.get(i)?.nama_prestasi_dua
                            txtTahun2.text = data?.get(i)?.tahun_dua
                            txtPenyelenggara2.text = data?.get(i)?.penyelenggara_dua
                            txtNamaPrestasi3.text = data?.get(i)?.nama_prestasi_tiga
                            txtTahun3.text = data?.get(i)?.tahun_tiga
                            txtPenyelenggara3.text = data?.get(i)?.penyelenggara_tiga
                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogDataPeriodik() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_periodik)


        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val idPeriodik = dialog.findViewById<TextView>(R.id.idPeriodik)
        val txtIdPeriodik = dialog.findViewById<TextView>(R.id.txtIdPeriodik)
        val beratBadan = dialog.findViewById<TextView>(R.id.beratBadan)
        val txtBeratBadan = dialog.findViewById<TextView>(R.id.txtBeratBadan)
        val tinggiBadan = dialog.findViewById<TextView>(R.id.tinggiBadan)
        val txtTinggiBadan = dialog.findViewById<TextView>(R.id.txtTinggiBadan)
        val jarakKeSekolah = dialog.findViewById<TextView>(R.id.jarakKeSekolah)
        val txtJarakKeSekolah = dialog.findViewById<TextView>(R.id.txtJarakKeSekolah)
        val sebutkan = dialog.findViewById<TextView>(R.id.sebutkan)
        val txtSebutkan = dialog.findViewById<TextView>(R.id.txtSebutkan)
        val waktuKeSekolah = dialog.findViewById<TextView>(R.id.waktuKeSekolah)
        val txtWaktuKeSekolah = dialog.findViewById<TextView>(R.id.txtWaktuKeSekolah)
        val jmlSaudara = dialog.findViewById<TextView>(R.id.jmlSaudara)
        val txtJmlSaudara = dialog.findViewById<TextView>(R.id.txtJmlSaudara)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        idPeriodik.startAnimation(left_to_right)
        txtIdPeriodik.startAnimation(left_to_right)
        beratBadan.startAnimation(left_to_right)
        txtBeratBadan.startAnimation(left_to_right)
        tinggiBadan.startAnimation(left_to_right)
        txtTinggiBadan.startAnimation(left_to_right)
        jarakKeSekolah.startAnimation(left_to_right)
        txtJarakKeSekolah.startAnimation(left_to_right)
        sebutkan.startAnimation(left_to_right)
        txtSebutkan.startAnimation(left_to_right)
        waktuKeSekolah.startAnimation(left_to_right)
        txtWaktuKeSekolah.startAnimation(left_to_right)
        jmlSaudara.startAnimation(left_to_right)
        txtJmlSaudara.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectDataPeriodik(id).enqueue(object : Callback<ResponseDataPeriodik>{
            override fun onFailure(call: Call<ResponseDataPeriodik>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseDataPeriodik>,
                response: Response<ResponseDataPeriodik>
            ) {
                if (response.isSuccessful)
                {
                    var data= response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdPeriodik.text = data?.get(i)?.id_data_periodik
                            txtIdUser.text = data?.get(i)?.id_user
                            txtTinggiBadan.text = data?.get(i)?.tinggi_badan
                            txtBeratBadan.text = data?.get(i)?.berat_badan
                            txtJarakKeSekolah.text = data?.get(i)?.jarak_tempat_tinggal
                            txtSebutkan.text = data?.get(i)?.km
                            txtWaktuKeSekolah.text = data?.get(i)?.waktu_tempuh_ke_sekolah
                            txtJmlSaudara.text = data?.get(i)?.jml_saudara
                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogKontak() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_kontak)


        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val idKontak = dialog.findViewById<TextView>(R.id.idKontak)
        val txtIdKontak = dialog.findViewById<TextView>(R.id.txtIdKontak)
        val noTelp = dialog.findViewById<TextView>(R.id.noTelp)
        val txtNoTelp = dialog.findViewById<TextView>(R.id.txtNoTelp)
        val noHp = dialog.findViewById<TextView>(R.id.noHp)
        val txtNoHp = dialog.findViewById<TextView>(R.id.txtNoHp)
        val emailAddress = dialog.findViewById<TextView>(R.id.emailAddress)
        val txtEmailAddress = dialog.findViewById<TextView>(R.id.txtEmailAddress)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        idKontak.startAnimation(left_to_right)
        txtIdKontak.startAnimation(left_to_right)
        noTelp.startAnimation(left_to_right)
        txtNoTelp.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        noHp.startAnimation(left_to_right)
        txtNoHp.startAnimation(left_to_right)
        emailAddress.startAnimation(left_to_right)
        txtEmailAddress.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectKontak(id).enqueue(object : Callback<ResponseKontak>{
            override fun onFailure(call: Call<ResponseKontak>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseKontak>,
                response: Response<ResponseKontak>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdKontak.text = data?.get(i)?.id_kontak
                            txtIdUser.text = data?.get(i)?.id_user
                            txtNoTelp.text = data?.get(i)?.no_telp_rumah
                            txtNoHp.text = data?.get(i)?.no_hp
                            txtEmailAddress.text = data?.get(i)?.email_address
                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogWali() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_wali)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val idWali = dialog.findViewById<TextView>(R.id.idWali)
        val txtIdWali = dialog.findViewById<TextView>(R.id.txtIdWali)
        val namaWali = dialog.findViewById<TextView>(R.id.namaWali)
        val txtNamaWali = dialog.findViewById<TextView>(R.id.txtNamaWali)
        val nikWali = dialog.findViewById<TextView>(R.id.nikWali)
        val txtNikWali = dialog.findViewById<TextView>(R.id.txtNikWali)
        val tahunLahir = dialog.findViewById<TextView>(R.id.thnLahir)
        val txtTahunLahir = dialog.findViewById<TextView>(R.id.txtThnLahir)
        val pendidikan = dialog.findViewById<TextView>(R.id.pendidikan)
        val txtPendidikan = dialog.findViewById<TextView>(R.id.txtPendidikan)
        val pekerjaan = dialog.findViewById<TextView>(R.id.pekerjaan)
        val txtPekerjaan = dialog.findViewById<TextView>(R.id.txtPekerjaan)
        val penghasilanBulanan = dialog.findViewById<TextView>(R.id.penghasilanBulanan)
        val txtPenghasilanBulanan = dialog.findViewById<TextView>(R.id.txtPenghasilanBulanan)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        idWali.startAnimation(left_to_right)
        txtIdWali.startAnimation(left_to_right)
        namaWali.startAnimation(left_to_right)
        txtNamaWali.startAnimation(left_to_right)
        nikWali.startAnimation(left_to_right)
        txtNikWali.startAnimation(left_to_right)
        tahunLahir.startAnimation(left_to_right)
        txtTahunLahir.startAnimation(left_to_right)
        pendidikan.startAnimation(left_to_right)
        txtPendidikan.startAnimation(left_to_right)
        pekerjaan.startAnimation(left_to_right)
        txtPekerjaan.startAnimation(left_to_right)
        penghasilanBulanan.startAnimation(left_to_right)
        txtPenghasilanBulanan.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectWali(id).enqueue(object : Callback<ResponseWali>{
            override fun onFailure(call: Call<ResponseWali>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ResponseWali>, response: Response<ResponseWali>) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdWali.text = data?.get(i)?.id_wali
                            txtIdUser.text = data?.get(i)?.id_user
                            txtNamaWali.text = data?.get(i)?.nama_wali
                            txtNikWali.text = data?.get(i)?.nik_wali
                            txtTahunLahir.text = data?.get(i)?.tahun_lahir_wali
                            txtPendidikan.text = data?.get(i)?.pendidikan_wali
                            txtPekerjaan.text = data?.get(i)?.pekerjaan_wali
                            txtPenghasilanBulanan.text = data?.get(i)?.penghasilan_bulanan_wali
                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
            }

        })

        dialog.show()    }

    private fun showDialogIbuKandung() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_ibu_kandung)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val idIbu = dialog.findViewById<TextView>(R.id.idIbuKandung)
        val txtIdIbu = dialog.findViewById<TextView>(R.id.txtIdIbuKandung)
        val namaIbu = dialog.findViewById<TextView>(R.id.namaIbu)
        val txtNamaIbu = dialog.findViewById<TextView>(R.id.txtNamaIbu)
        val nikIbu = dialog.findViewById<TextView>(R.id.nikIbu)
        val txtNikIbu = dialog.findViewById<TextView>(R.id.txtNikIbu)
        val tahunLahir = dialog.findViewById<TextView>(R.id.thnLahir)
        val txtTahunLahir = dialog.findViewById<TextView>(R.id.txtThnLahir)
        val pendidikan = dialog.findViewById<TextView>(R.id.pendidikan)
        val txtPendidikan = dialog.findViewById<TextView>(R.id.txtPendidikan)
        val pekerjaan = dialog.findViewById<TextView>(R.id.pekerjaan)
        val txtPekerjaan = dialog.findViewById<TextView>(R.id.txtPekerjaan)
        val penghasilanBulanan = dialog.findViewById<TextView>(R.id.penghasilanBulanan)
        val txtPenghasilanBulanan = dialog.findViewById<TextView>(R.id.txtPenghasilanBulanan)
        val berkebutuhanKhusus = dialog.findViewById<TextView>(R.id.berkebutuhanKhusus)
        val txtBerkebutuhanKhusus = dialog.findViewById<TextView>(R.id.txtBerkebutuhanKhusus)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        idIbu.startAnimation(left_to_right)
        txtIdIbu.startAnimation(left_to_right)
        namaIbu.startAnimation(left_to_right)
        txtNamaIbu.startAnimation(left_to_right)
        nikIbu.startAnimation(left_to_right)
        txtNikIbu.startAnimation(left_to_right)
        tahunLahir.startAnimation(left_to_right)
        txtTahunLahir.startAnimation(left_to_right)
        pendidikan.startAnimation(left_to_right)
        txtPendidikan.startAnimation(left_to_right)
        pekerjaan.startAnimation(left_to_right)
        txtPekerjaan.startAnimation(left_to_right)
        penghasilanBulanan.startAnimation(left_to_right)
        txtPenghasilanBulanan.startAnimation(left_to_right)
        berkebutuhanKhusus.startAnimation(left_to_right)
        txtBerkebutuhanKhusus.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectIbuKandung(id).enqueue(object : Callback<ResponseIbuKandung>{
            override fun onFailure(call: Call<ResponseIbuKandung>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseIbuKandung>,
                response: Response<ResponseIbuKandung>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdIbu.text = data?.get(i)?.id_ibu_kandung
                            txtIdUser.text = data?.get(i)?.id_user
                            txtNamaIbu.text = data?.get(i)?.nama_ibu_kandung
                            txtNikIbu.text = data?.get(i)?.nik_ibu
                            txtTahunLahir.text = data?.get(i)?.tahun_lahir_ibu
                            txtPendidikan.text = data?.get(i)?.pendidikan_ibu
                            txtPekerjaan.text = data?.get(i)?.pekerjaan_ibu
                            txtPenghasilanBulanan.text = data?.get(i)?.penghasilan_bulanan_ibu
                            txtBerkebutuhanKhusus.text = data?.get(i)?.berkebutuhan_khusus_ibu
                        }
                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogAyahKandung() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_ayah_kandung)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val idAyah = dialog.findViewById<TextView>(R.id.idAyahKandung)
        val txtIdAyah = dialog.findViewById<TextView>(R.id.txtIdAyahKandung)
        val namaAyah = dialog.findViewById<TextView>(R.id.namaAyah)
        val txtNamaAyah = dialog.findViewById<TextView>(R.id.txtNamaAyah)
        val nikAyah = dialog.findViewById<TextView>(R.id.nikAyah)
        val txtNikAyah = dialog.findViewById<TextView>(R.id.txtNikAyah)
        val tahunLahir = dialog.findViewById<TextView>(R.id.thnLahir)
        val txtTahunLahir = dialog.findViewById<TextView>(R.id.txtThnLahir)
        val pendidikan = dialog.findViewById<TextView>(R.id.pendidikan)
        val txtPendidikan = dialog.findViewById<TextView>(R.id.txtPendidikan)
        val pekerjaan = dialog.findViewById<TextView>(R.id.pekerjaan)
        val txtPekerjaan = dialog.findViewById<TextView>(R.id.txtPekerjaan)
        val penghasilanBulanan = dialog.findViewById<TextView>(R.id.penghasilanBulanan)
        val txtPenghasilanBulanan = dialog.findViewById<TextView>(R.id.txtPenghasilanBulanan)
        val berkebutuhanKhusus = dialog.findViewById<TextView>(R.id.berkebutuhanKhusus)
        val txtBerkebutuhanKhusus = dialog.findViewById<TextView>(R.id.txtBerkebutuhanKhusus)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        idAyah.startAnimation(left_to_right)
        txtIdAyah.startAnimation(left_to_right)
        namaAyah.startAnimation(left_to_right)
        txtNamaAyah.startAnimation(left_to_right)
        nikAyah.startAnimation(left_to_right)
        txtNikAyah.startAnimation(left_to_right)
        tahunLahir.startAnimation(left_to_right)
        txtTahunLahir.startAnimation(left_to_right)
        pendidikan.startAnimation(left_to_right)
        txtPendidikan.startAnimation(left_to_right)
        pekerjaan.startAnimation(left_to_right)
        txtPekerjaan.startAnimation(left_to_right)
        penghasilanBulanan.startAnimation(left_to_right)
        txtPenghasilanBulanan.startAnimation(left_to_right)
        berkebutuhanKhusus.startAnimation(left_to_right)
        txtBerkebutuhanKhusus.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectAyahKandung(id).enqueue(object : Callback<ResponseAyahKandung>{
            override fun onFailure(call: Call<ResponseAyahKandung>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponseAyahKandung>,
                response: Response<ResponseAyahKandung>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdAyah.text = data?.get(i)?.id_ayah_kandung
                            txtIdUser.text = data?.get(i)?.id_user
                            txtNamaAyah.text = data?.get(i)?.nama_ayah_kandung
                            txtNikAyah.text = data?.get(i)?.nik_ayah
                            txtTahunLahir.text = data?.get(i)?.tahun_lahir_ayah
                            txtPendidikan.text = data?.get(i)?.pendidikan_ayah
                            txtPekerjaan.text = data?.get(i)?.pekerjaan_ayah
                            txtPenghasilanBulanan.text = data?.get(i)?.penghasilan_bulanan_ayah
                            txtBerkebutuhanKhusus.text = data?.get(i)?.berkebutuhan_khusus_ayah
                        }
                    }
                    else
                    {

                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogPribadiLima() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_pribadi_lima)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idPribadi = dialog.findViewById<TextView>(R.id.idPribadi)
        val txtIdPribadi = dialog.findViewById<TextView>(R.id.txtIdPribadi)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val usulanDariSekolah = dialog.findViewById<TextView>(R.id.usulanDariSekolah)
        val txtUsulanDariSekolah = dialog.findViewById<TextView>(R.id.txtUsulanDariSekolah)
        val penerimaKip = dialog.findViewById<TextView>(R.id.penerimaKip)
        val txtPenerimaKip = dialog.findViewById<TextView>(R.id.txtPenerimaKip)
        val noKip = dialog.findViewById<TextView>(R.id.noKip)
        val txtNoKip = dialog.findViewById<TextView>(R.id.txtNoKip)
        val namaKip = dialog.findViewById<TextView>(R.id.namaKip)
        val txtNamaKip = dialog.findViewById<TextView>(R.id.txtNamaKip)
        val terimaFisikKip = dialog.findViewById<TextView>(R.id.terimaFisikKip)
        val txtTerimaFisikKip = dialog.findViewById<TextView>(R.id.txtTerimaFisikKip)
        val alasanMerimaKip = dialog.findViewById<TextView>(R.id.alasanMenerimaKip)
        val txtAlasanMerimaKip = dialog.findViewById<TextView>(R.id.txtAlasanMenerimaKip)
        val bank = dialog.findViewById<TextView>(R.id.bank)
        val txtBank = dialog.findViewById<TextView>(R.id.txtBank)
        val noBank = dialog.findViewById<TextView>(R.id.noBank)
        val txtNoBank = dialog.findViewById<TextView>(R.id.txtNoBank)
        val namaRek = dialog.findViewById<TextView>(R.id.namaRek)
        val txtNamaRek = dialog.findViewById<TextView>(R.id.txtNamaRek)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idPribadi.startAnimation(left_to_right)
        txtIdPribadi.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        usulanDariSekolah.startAnimation(left_to_right)
        txtUsulanDariSekolah.startAnimation(left_to_right)
        penerimaKip.startAnimation(left_to_right)
        txtPenerimaKip.startAnimation(left_to_right)
        noKip.startAnimation(left_to_right)
        txtNoKip.startAnimation(left_to_right)
        namaKip.startAnimation(left_to_right)
        txtNamaKip.startAnimation(left_to_right)
        terimaFisikKip.startAnimation(left_to_right)
        txtTerimaFisikKip.startAnimation(left_to_right)
        alasanMerimaKip.startAnimation(left_to_right)
        txtAlasanMerimaKip.startAnimation(left_to_right)
        bank.startAnimation(left_to_right)
        txtBank.startAnimation(left_to_right)
        noBank.startAnimation(left_to_right)
        txtNoBank.startAnimation(left_to_right)
        namaRek.startAnimation(left_to_right)
        txtNamaRek.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectPribadiLima(id).enqueue(object : Callback<ResponsePribadiLima>{
            override fun onFailure(call: Call<ResponsePribadiLima>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ResponsePribadiLima>,
                response: Response<ResponsePribadiLima>
            ) {
                var data = response.body()?.results
                if (data?.size?:0>0)
                {
                    for (i: Int in data?.indices ?: 0..1) {
                        txtIdPribadi.text = data?.get(i)?.id_pribadi_lima
                        txtIdUser.text = data?.get(i)?.id_user
                        txtUsulanDariSekolah.text = data?.get(i)?.usulan_dari_sekolah
                        txtPenerimaKip.text = data?.get(i)?.penerima_kip
                        txtNoKip.text = data?.get(i)?.nomor_kip
                        txtNamaKip.text = data?.get(i)?.nama_tertera_kip
                        txtTerimaFisikKip.text = data?.get(i)?.terima_fisik_kip
                        txtAlasanMerimaKip.text = data?.get(i)?.alasan_menerima_kip
                        txtBank.text = data?.get(i)?.bank
                        txtNoBank.text = data?.get(i)?.no_rek_bank
                        txtNamaRek.text = data?.get(i)?.nama_rek
                    }
                }
                else
                {

                }
            }

        })

        dialog.show()
    }

    private fun showDialogPribadiEmpat() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_pribadi_empat)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idPribadi = dialog.findViewById<TextView>(R.id.idPribadi)
        val txtIdPribadi = dialog.findViewById<TextView>(R.id.txtIdPribadi)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val tempatTinggal = dialog.findViewById<TextView>(R.id.tempatTinggal)
        val txtTempatTinggal = dialog.findViewById<TextView>(R.id.txtTempatTinggal)
        val modeTransportasi = dialog.findViewById<TextView>(R.id.modeTrasnportasi)
        val txtModeTransportasi = dialog.findViewById<TextView>(R.id.txtModeTrasnportasi)
        val kk = dialog.findViewById<TextView>(R.id.kk)
        val txtKk = dialog.findViewById<TextView>(R.id.txtKk)
        val anakKe = dialog.findViewById<TextView>(R.id.anakKe)
        val txtAnakKe = dialog.findViewById<TextView>(R.id.txtAnakKe)
        val penerimaKps = dialog.findViewById<TextView>(R.id.penerimaKps)
        val txtPenerimaKps = dialog.findViewById<TextView>(R.id.txtPenerimaKps)
        val kps = dialog.findViewById<TextView>(R.id.kps)
        val txtKps = dialog.findViewById<TextView>(R.id.txtKps)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idPribadi.startAnimation(left_to_right)
        txtIdPribadi.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        tempatTinggal.startAnimation(left_to_right)
        txtTempatTinggal.startAnimation(left_to_right)
        modeTransportasi.startAnimation(left_to_right)
        txtModeTransportasi.startAnimation(left_to_right)
        kk.startAnimation(left_to_right)
        txtKk.startAnimation(left_to_right)
        anakKe.startAnimation(left_to_right)
        txtAnakKe.startAnimation(left_to_right)
        penerimaKps.startAnimation(left_to_right)
        txtPenerimaKps.startAnimation(left_to_right)
        kps.startAnimation(left_to_right)
        txtKps.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectPribadiEmpat(id).enqueue(object : Callback<ResponsePribadiEmpat>{
            override fun onFailure(call: Call<ResponsePribadiEmpat>, t: Throwable) {
                Log.d("Response Gagal 2",t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePribadiEmpat>,
                response: Response<ResponsePribadiEmpat>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdPribadi.text = data?.get(i)?.id_pribadi_empat
                            txtIdUser.text = data?.get(i)?.id_user
                            txtTempatTinggal.text = data?.get(i)?.tempat_tinggal
                            txtModeTransportasi.text = data?.get(i)?.mode_transportasi
                            txtKk.text = data?.get(i)?.no_kk
                            txtAnakKe.text = data?.get(i)?.anak_keberapa
                            txtPenerimaKps.text = data?.get(i)?.penerima_kps
                            txtKps.text = data?.get(i)?.no_kps
                        }
                    }
                    else
                    {
                        Log.d("Data Kosong",response.message())
                    }
                }
                else
                {
                    Log.d("Response Gagal 1",response.message())
                }
            }

        })

        dialog.show()
    }

    private fun showDialogPribadiTiga() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_pribadi_tiga)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val idPribadi = dialog.findViewById<TextView>(R.id.idPribadi)
        val txtIdPribadi = dialog.findViewById<TextView>(R.id.txtIdPribadi)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtIdUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val namaDusun = dialog.findViewById<TextView>(R.id.namaDusun)
        val txtNamaDusun = dialog.findViewById<TextView>(R.id.txtNamaDusun)
        val namaKel = dialog.findViewById<TextView>(R.id.namaKel)
        val txtNamaKel = dialog.findViewById<TextView>(R.id.txtNamaKel)
        val namaKec = dialog.findViewById<TextView>(R.id.kec)
        val txtNamaKec = dialog.findViewById<TextView>(R.id.txtKec)
        val kodePos = dialog.findViewById<TextView>(R.id.kodePos)
        val txtKodePos = dialog.findViewById<TextView>(R.id.txtKodePos)
        val lintang = dialog.findViewById<TextView>(R.id.lintang)
        val txtLintang = dialog.findViewById<TextView>(R.id.txtLintang)
        val bujur = dialog.findViewById<TextView>(R.id.bujur)
        val txtBujur = dialog.findViewById<TextView>(R.id.txtBujur)

        idPribadi.startAnimation(left_to_right)
        txtIdPribadi.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtIdUser.startAnimation(left_to_right)
        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        namaDusun.startAnimation(left_to_right)
        txtNamaDusun.startAnimation(left_to_right)
        namaKel.startAnimation(left_to_right)
        txtNamaKel.startAnimation(left_to_right)
        namaKec.startAnimation(left_to_right)
        txtNamaKec.startAnimation(left_to_right)
        kodePos.startAnimation(left_to_right)
        txtKodePos.startAnimation(left_to_right)
        lintang.startAnimation(left_to_right)
        txtLintang.startAnimation(left_to_right)
        bujur.startAnimation(left_to_right)
        txtBujur.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectPribadiTiga(id).enqueue(object : Callback<ResponsePribadiTiga>{
            override fun onFailure(call: Call<ResponsePribadiTiga>, t: Throwable) {
                Log.d("Response Gagal 2",t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePribadiTiga>,
                response: Response<ResponsePribadiTiga>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdPribadi.text = data?.get(i)?.id_pribadi_tiga
                            txtIdUser.text = data?.get(i)?.id_user
                            txtNamaDusun.text = data?.get(i)?.nama_dusun
                            txtNamaKel.text = data?.get(i)?.nama_kelurahan
                            txtNamaKec.text = data?.get(i)?.kecamatan
                            txtKodePos.text = data?.get(i)?.kode_pos
                            txtLintang.text = data?.get(i)?.lintang
                            txtBujur.text = data?.get(i)?.bujur
                        }
                    }
                    else
                    {
                        Log.d("Data Kosong",response.message())
                    }
                }
                else
                {
                    Log.d("Response Gagal 1",response.message())
                }
            }

        })

        dialog.show()
    }

    private fun inisialisasiPresenter() {

        //todo 6 (selectDataBeasiswa) inisialisasi presenter
        selectDataBeasiswaPresenter = SelectDataBeasiswaPresenter(this)

        //todo 6 (selectDataPrestasi) inisialisasi presenter
        selectDataPrestasiPrestasi = SelectDataPrestasiPresenter(this)

        //todo 6 (selectDataPeriodik) inisialisasi presenter
        selectDataPeriodikPresenter = SelectDataPeriodikPresenter(this)

        // todo 6 (selectKontak) inisialisasi presenter
        selectKontakPresenter = SelectKontakPresenter(this)

        //todo 6 (selectWali) inisialisasi presenter
        selectWaliPresenter = SelectWaliPresenter(this)

        // todo 6 (selectIbuKandung) inisialisasi presenter
        selectIbuKandungPresenter = SelectIbuKandungPresenter(this)

        // todo 6 (selectAyahKandung) inisialisasi presenter
        selectAyahKandungPresenter = SelectAyahKandungPresenter(this)

        //todo 6 (selectPribadiLima) inisialisasi presenter
        selectPribadiLimaPresenter = SelectPribadiLimaPresenter(this)

        //todo 6 (selectPribadiEmpat) inisialisasi presenter
        selectPribadiEmpatPresenter = SelectPribadiEmpatPresenter(this)

        //todo 6 (selectPribadiDua) inisialisasi presenter
        selectPribadiDuaPresenter =
            SelectPribadiDuaPresenter(
                this
            )

        //todo 7 (login) inisiasliasi presenter
        homePresenter = HomePresenter(this)

        //todo 6 (selectPribadiSatu) inisialisasi presenter
        selectPribadiSatuPresenter =
            SelectPribadiSatuPresenter(
                this
            )

        //todo 7 (getFotoProfile) inisialisasi presenter
        photoPresenter = PhotoPresenter(this)

        //todo 6 (selectPribadiTiga) inisialisasi presenter
        selectPribadiTigaPresenter = selectPribadiTigaPresenter(this)

    }

    fun anim() {
        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        right_to_left = AnimationUtils.loadAnimation(this,R.anim.right_to_left)
        scale_animation = AnimationUtils.loadAnimation(this,R.anim.scale_animation)

        txtSelamatDatang.startAnimation(scale_animation)
        txtSd30.startAnimation(scale_animation)
        imgProfile.startAnimation(scale_animation)
        rvPhoto.startAnimation(scale_animation)

        pribadibagian1.startAnimation(left_to_right)
        selectpribadibagian1.startAnimation(left_to_right)
        selectpribadibagian2.startAnimation(left_to_right)
        selectpribadibagian3.startAnimation(left_to_right)
        selectpribadibagian4.startAnimation(left_to_right)
        selectpribadibagian5.startAnimation(left_to_right)
        selectAyahKandung.startAnimation(left_to_right)
        selectIbuKandung.startAnimation(left_to_right)
        selectWali.startAnimation(left_to_right)
        selectKontak.startAnimation(left_to_right)
        selectDataPeriodik.startAnimation(left_to_right)
        selectDataPrestasi.startAnimation(left_to_right)
        selectDataBeasiswa.startAnimation(left_to_right)
        pribadibagian4.startAnimation(left_to_right)
        ibuKandung.startAnimation(left_to_right)
        dataPeriodik.startAnimation(left_to_right)

        pribadibagian2.startAnimation(scale_animation)
        pribadibagian5.startAnimation(scale_animation)
        wali.startAnimation(scale_animation)
        prestasi.startAnimation(scale_animation)

        pribadibagian3.startAnimation(right_to_left)
        ayahKandung.startAnimation(right_to_left)
        kontak.startAnimation(right_to_left)
        beasiswa.startAnimation(right_to_left)
    }

    private fun showDialogPribadiDua() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_pribadi_dua)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idPribadi = dialog.findViewById<TextView>(R.id.idPribadi)
        val txtIdPribadi = dialog.findViewById<TextView>(R.id.txtIdPribadi)
        val idUser = dialog.findViewById<TextView>(R.id.idUser)
        val txtidUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val akte = dialog.findViewById<TextView>(R.id.akte)
        val txtAkte = dialog.findViewById<TextView>(R.id.txtAkte)
        val agama = dialog.findViewById<TextView>(R.id.agama)
        val txtAgama = dialog.findViewById<TextView>(R.id.txtAgama)
        val kwn = dialog.findViewById<TextView>(R.id.kwn)
        val txtKwn = dialog.findViewById<TextView>(R.id.txtKwn)
        val berkebutuhanKhusus = dialog.findViewById<TextView>(R.id.berkebutuhanKhusus)
        val txtberkebutuhanKhusus = dialog.findViewById<TextView>(R.id.txtBerkebutuhanKhusus)
        val alamat = dialog.findViewById<TextView>(R.id.alamat)
        val txtAlamat = dialog.findViewById<TextView>(R.id.txtAlamat)
        val rt = dialog.findViewById<TextView>(R.id.rt)
        val txtRt = dialog.findViewById<TextView>(R.id.txtRt)
        val rw = dialog.findViewById<TextView>(R.id.rw)
        val txtRw = dialog.findViewById<TextView>(R.id.txtRw)

        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        idPribadi.startAnimation(left_to_right)
        txtIdPribadi.startAnimation(left_to_right)
        idUser.startAnimation(left_to_right)
        txtidUser.startAnimation(left_to_right)
        akte.startAnimation(left_to_right)
        txtAkte.startAnimation(left_to_right)
        agama.startAnimation(left_to_right)
        txtAgama.startAnimation(left_to_right)
        kwn.startAnimation(left_to_right)
        txtKwn.startAnimation(left_to_right)
        berkebutuhanKhusus.startAnimation(left_to_right)
        txtberkebutuhanKhusus.startAnimation(left_to_right)
        alamat.startAnimation(left_to_right)
        txtAlamat.startAnimation(left_to_right)
        rt.startAnimation(left_to_right)
        txtRt.startAnimation(left_to_right)
        rw.startAnimation(left_to_right)
        txtRw.startAnimation(left_to_right)

        var id = id_user.text.toString()
        ConfigNetwork.getRetrofit().selectPribadiDua(id).enqueue(object : Callback<ResponsePribadiDua>{
            override fun onFailure(call: Call<ResponsePribadiDua>, t: Throwable) {
                toast("Gagal")
                Log.d("Response Gagal 2",t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePribadiDua>,
                response: Response<ResponsePribadiDua>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.results
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            txtIdPribadi.text = data?.get(i)?.id_pribadi_dua
                            txtidUser.text = data?.get(i)?.id_user
                            txtAkte.text = data?.get(i)?.no_akte
                            txtAgama.text = data?.get(i)?.agama
                            txtKwn.text = data?.get(i)?.kewarganegaraan
                            txtberkebutuhanKhusus.text = data?.get(i)?.berkebutuhan_khusus
                            txtAlamat.text = data?.get(i)?.alamat_jalan
                            txtRt.text = data?.get(i)?.rt
                            txtRw.text = data?.get(i)?.rw
                        }
                    }
                    else
                    {
                        Log.d("Data Kosong",response.message())
                    }
                }
                else
                {
                    Log.d("Response Gagal 1",response.message())
                }
            }

        })

        dialog.show()
    }

    private fun showDialogPribadiSatu() {
        val dialog = Dialog(this)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.kartu_pribadi_satu)

        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val imgLogo = dialog.findViewById<ImageView>(R.id.imgLogo)
        val txtJudul = dialog.findViewById<TextView>(R.id.txtJudul)
        val idPribadi = dialog.findViewById<TextView>(R.id.IdPribadi)
        val id_User = dialog.findViewById<TextView>(R.id.IdUser)
        val txtidUser = dialog.findViewById<TextView>(R.id.txtIdUser)
        val txtPribadi = dialog.findViewById<TextView>(R.id.txtIdPribadi)
        val namaPribadi = dialog.findViewById<TextView>(R.id.namaPribadi)
        val txtNamaPribadi = dialog.findViewById<TextView>(R.id.txtNamaPribadi)
        val jk = dialog.findViewById<TextView>(R.id.jk)
        val txtJk = dialog.findViewById<TextView>(R.id.txtJk)
        val nisn = dialog.findViewById<TextView>(R.id.nisn)
        val txtNisn = dialog.findViewById<TextView>(R.id.txtNisn)
        val nik = dialog.findViewById<TextView>(R.id.nik)
        val txtNik = dialog.findViewById<TextView>(R.id.txtNik)
        val tempatLahir = dialog.findViewById<TextView>(R.id.tempatLahir)
        val txtTempatLahir = dialog.findViewById<TextView>(R.id.txtTempatLahir)
        val tglLahir = dialog.findViewById<TextView>(R.id.tglLahir)
        val txtTglLahir = dialog.findViewById<TextView>(R.id.txtTglLahir)
        val fotoNisn = dialog.findViewById<TextView>(R.id.fotoNisn)
        val imgFotoNisn = dialog.findViewById<ImageView>(R.id.imgFotoNisn)
        val fotoNik = dialog.findViewById<TextView>(R.id.fotoNik)
        val imgFotoNik = dialog.findViewById<ImageView>(R.id.imgFotoNik)

        idPribadi.startAnimation(left_to_right)
        id_User.startAnimation(left_to_right)
        txtidUser.startAnimation(left_to_right)
        imgLogo.startAnimation(left_to_right)
        txtJudul.startAnimation(left_to_right)
        txtPribadi.startAnimation(left_to_right)
        namaPribadi.startAnimation(left_to_right)
        txtNamaPribadi.startAnimation(left_to_right)
        jk.startAnimation(left_to_right)
        txtJk.startAnimation(left_to_right)
        nisn.startAnimation(left_to_right)
        txtNisn.startAnimation(left_to_right)
        nik.startAnimation(left_to_right)
        txtNik.startAnimation(left_to_right)
        tempatLahir.startAnimation(left_to_right)
        txtTempatLahir.startAnimation(left_to_right)
        tglLahir.startAnimation(left_to_right)
        txtTglLahir.startAnimation(left_to_right)
        fotoNisn.startAnimation(left_to_right)
        imgFotoNisn.startAnimation(left_to_right)
        fotoNik.startAnimation(left_to_right)
        imgFotoNik.startAnimation(left_to_right)

        var id = id_user.text.toString()

        ConfigNetwork.getRetrofit().selectPribadiSatu(id).enqueue(object : Callback<ResponsePribadiSatuKotlin>{
            override fun onFailure(call: Call<ResponsePribadiSatuKotlin>, t: Throwable) {
                toast("Gagal Total")
                Log.d("Response Gagal Total",t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePribadiSatuKotlin>,
                response: Response<ResponsePribadiSatuKotlin>
            ) {
                if (response.isSuccessful)
                {
                    var data = response.body()?.result
                    if (data?.size?:0>0)
                    {
                        for (i: Int in data?.indices ?: 0..1) {
                            var Contants = "http://192.168.43.19/PendaftaranSekolah/foto_pribadi_satu/"
                            txtPribadi.text = data?.get(i)?.idPribadiSatu
                            txtidUser.text = data?.get(i)?.id_user
                            txtNamaPribadi.text = data?.get(i)?.namaPribadi
                            txtJk.text = data?.get(i)?.jenisKelamin
                            txtNisn.text = data?.get(i)?.nisn
                            txtNik.text = data?.get(i)?.nik
                            txtTempatLahir.text = data?.get(i)?.tempatLahir
                            txtTglLahir.text = data?.get(i)?.tglLahir
                            Glide.with(dialog.context)
                                .load(Contants + data?.get(i)?.fotoNik)
                                .apply(RequestOptions().error(R.drawable.icon_nopic))
                                .into(imgFotoNik)

                            Glide.with(dialog.context)
                                .load(Contants + data?.get(i)?.fotoNisn)
                                .apply(RequestOptions().error(R.drawable.icon_nopic))
                                .into(imgFotoNisn)
                        }
                    }
                    else
                    {
                        Log.d("Response Gagal 1",response.message())
                    }
                }
                else
                {
                    Log.d("Response Gagal 2",response.message())
                }
            }

        })


        dialog.show()
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //todo 8 (login) implementasi presenter
    override fun onSuccess(message: String, results: List<ResultsItemLogin?>?) {
        progressBar.visibility = View.GONE
        bgLoading.visibility = View.GONE
        for (i: Int in results?.indices ?: 0..1) {
            var Contants = "http://192.168.43.19/PendaftaranSekolah/foto_user/"
            Glide.with(this)
                .load(Contants + results?.get(i)?.photo)
                .apply(RequestOptions().error(R.drawable.icon_nopic))
                .into(imgProfile)
        }
    }

    override fun onError(message: String) {
        toast("Gagal")
    }

    //todo 8 (getFotoProfile) implementasi presenter
    override fun onSuccessPhoto(message: String, results: List<ResultsItemPhotoSekolah?>?) {
        progressBar.visibility = View.GONE
        bgLoading.visibility = View.GONE
        rvPhoto.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        rvPhoto.adapter = HomeAdapter(results)
    }

    override fun onErrorPhoto(message: String) {
        toast("Gagal")
    }

    //todo 7 (selectPribadiSatu) implementasi presenter

    override fun onSuccessSelect(message: String, results: List<ResultItem?>?) {
        selectpribadibagian1.visibility = View.VISIBLE
        pribadibagian1.visibility = View.GONE
    }

    override fun onErrorSelect(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelect() {
    }

    override fun hideProgressSelect() {
    }

    //todo 7 (selectPribadiDua) implementasi presenter
    override fun onSuccessSelectPribadiDua(
        message: String,
        results: List<ResultsItemPribadiDua?>?
    ) {
        selectpribadibagian2.visibility = View.VISIBLE
        pribadibagian2.visibility = View.GONE
    }

    override fun onErrorSelectPribadiDua(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectPribadiDua() {
    }

    override fun hideProgressSelectPribadiDua() {
    }

    //todo 7 (selectPribadiTiga) impelentasi presenter
    override fun onSuccessSelectPribadiTiga(
        message: String,
        results: List<ResultsItemPribadiTiga?>?
    ) {
        selectpribadibagian3.visibility = View.VISIBLE
        pribadibagian3.visibility = View.GONE
    }

    //todo 7 (selectPribadiEmpat) impelentasi presenter
    override fun onErrorSelectPribadiTiga(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectPribadiTiga() {
    }

    override fun hideProgressSelectPribadiTiga() {
    }

    override fun onSuccessSelectPribadiEmpat(
        message: String,
        results: List<ResultsItemPribadiEmpat?>?
    ) {
        selectpribadibagian4.visibility = View.VISIBLE
        pribadibagian4.visibility = View.GONE
    }

    override fun onErrorSelectPribadiEmpat(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectPribadiEmpat() {
    }

    override fun hideProgressSelectPribadiEmpat() {
    }

    //todo 7 (selectPribadiLima) implementasi presenter
    override fun onSuccessSelectPribadiLima(
        message: String,
        results: List<ResultsItemPribadiLima?>?
    ) {
        selectpribadibagian5.visibility = View.VISIBLE
        pribadibagian5.visibility = View.GONE
    }

    override fun onErrorSelectPribadiLima(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectPribadiLima() {
    }

    override fun hideProgressSelectPribadiLima() {
    }

    // todo 7 (selectAyahKandung) implementasi presenter
    override fun onSuccessSelectAyahKandung(
        message: String,
        results: List<ResultsItemAyahKandung?>?
    ) {
        selectAyahKandung.visibility = View.VISIBLE
        ayahKandung.visibility = View.GONE
    }

    override fun onErrorSelectAyahKandung(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectAyahKandung() {
    }

    override fun hideProgressSelectAyahKandung() {
    }

    // todo 7 (selectIbuKandung) implementasi presenter
    override fun onSuccessSelectIbuKandung(
        message: String,
        results: List<ResultsItemIbuKandung?>?
    ) {
        selectIbuKandung.visibility = View.VISIBLE
        ibuKandung.visibility = View.GONE
    }

    override fun onErrorSelectIbuKandung(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectIbuKandung() {
    }

    override fun hideProgressSelectIbuKandung() {
    }

    //todo 7 (selectWali) implementasi presenter
    override fun onSuccessSelectWali(message: String, results: List<ResultsItemWali?>?) {
        selectWali.visibility = View.VISIBLE
        wali.visibility = View.GONE
    }

    override fun onErrorSelectWali(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectWali() {
    }

    override fun hideProgressSelectWali() {
    }

    // todo 7 (selectKontak) implementasi presenter
    override fun onSuccessSelectKontak(message: String, results: List<ResultsItemKontak?>?) {
        selectKontak.visibility = View.VISIBLE
        kontak.visibility = View.GONE
    }

    override fun onErrorSelectKontak(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectKontak() {
    }

    override fun hideProgressSelectKontak() {
    }

    // todo 7 (selectDataPeriodik) implementasi presenter
    override fun onSuccessSelectDataPeriodik(
        message: String,
        results: List<ResultsItemDataPeriodik?>?
    ) {
        selectDataPeriodik.visibility = View.VISIBLE
        dataPeriodik.visibility = View.GONE
    }

    override fun onErrorSelectDataPeriodik(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectDataPeriodik() {
    }

    override fun hideProgressSelectDataPeriodik() {
    }

    //todo 7 (selectDataPrestasi) implementasi presenter
    override fun onSuccessSelectDataPrestasi(
        message: String,
        results: List<ResultsItemDataPrestasi?>?
    ) {
        selectDataPrestasi.visibility = View.VISIBLE
        prestasi.visibility = View.GONE
    }

    override fun onErrorSelectDataPrestasi(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectDataPrestasi() {
    }

    override fun hideProgressSelectDataPrestasi() {
    }

    //todo 7 (selectDataBeasiswa) implementasi presenter
    override fun onSuccessSelectDataBeasiswa(
        message: String,
        results: List<ResultsItemDataBeasiswa?>?
    ) {
        selectDataBeasiswa.visibility = View.VISIBLE
        beasiswa.visibility = View.GONE
    }

    override fun onErrorSelectDataBeasiswa(message: String) {
        toast("Data Kosong")
    }

    override fun hideLoadingSelectDataBeasiswa() {
    }

    override fun hideProgressSelectDataBeasiswa() {
    }
}

