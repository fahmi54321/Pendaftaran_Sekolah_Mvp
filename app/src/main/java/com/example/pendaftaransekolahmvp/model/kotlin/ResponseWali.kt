package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseWali(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemWali?>? = null
)

data class ResultsItemWali(

    @field:SerializedName("id_wali")
    val id_wali: String? = null,

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("nama_wali")
    val nama_wali: String? = null,

    @field:SerializedName("nik_wali")
    val nik_wali: String? = null,

    @field:SerializedName("tahun_lahir_wali")
    val tahun_lahir_wali: String? = null,

    @field:SerializedName("pendidikan_wali")
    val pendidikan_wali: String? = null,

    @field:SerializedName("pekerjaan_wali")
    val pekerjaan_wali: String? = null,

    @field:SerializedName("penghasilan_bulanan_wali")
    val penghasilan_bulanan_wali: String? = null
)