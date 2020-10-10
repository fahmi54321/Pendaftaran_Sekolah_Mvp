package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseDataPrestasi(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemDataPrestasi?>? = null
)

data class ResultsItemDataPrestasi(

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("id_prestasi")
    val id_prestasi: String? = null,

    @field:SerializedName("jenis_prestasi")
    val jenis_prestasi: String? = null,

    @field:SerializedName("tingkat")
    val tingkat: String? = null,

    @field:SerializedName("nama_prestasi_satu")
    val nama_prestasi_satu: String? = null,

    @field:SerializedName("tahun_satu")
    val tahun_satu: String? = null,

    @field:SerializedName("penyelenggara_satu")
    val penyelenggara_satu: String? = null,

    @field:SerializedName("nama_prestasi_dua")
    val nama_prestasi_dua: String? = null,

    @field:SerializedName("tahun_dua")
    val tahun_dua: String? = null,

    @field:SerializedName("penyelenggara_dua")
    val penyelenggara_dua: String? = null,

    @field:SerializedName("nama_prestasi_tiga")
    val nama_prestasi_tiga: String? = null,

    @field:SerializedName("tahun_tiga")
    val tahun_tiga: String? = null,

    @field:SerializedName("penyelenggara_tiga")
    val penyelenggara_tiga: String? = null
)