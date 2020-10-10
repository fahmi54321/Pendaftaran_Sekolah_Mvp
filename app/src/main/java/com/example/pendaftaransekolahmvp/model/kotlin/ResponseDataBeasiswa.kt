package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseDataBeasiswa(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemDataBeasiswa?>? = null
)

data class ResultsItemDataBeasiswa(

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("jenis_beasiswa_satu")
    val jenis_beasiswa_satu: String? = null,

    @field:SerializedName("keterangan_satu")
    val keterangan_satu: String? = null,

    @field:SerializedName("tahun_mulai_satu")
    val tahun_mulai_satu: String? = null,

    @field:SerializedName("tahun_selesai_satu")
    val tahun_selesai_satu: String? = null,

    @field:SerializedName("jenis_beasiswa_dua")
    val jenis_beasiswa_dua: String? = null,

    @field:SerializedName("keterangan_dua")
    val keterangan_dua: String? = null,

    @field:SerializedName("tahun_mulai_dua")
    val tahun_mulai_dua: String? = null,

    @field:SerializedName("tahun_selesai_dua")
    val tahun_selesai_dua: String? = null,

    @field:SerializedName("jenis_beasiswa_tiga")
    val jenis_beasiswa_tiga: String? = null,

    @field:SerializedName("keterangan_tiga")
    val keterangan_tiga: String? = null,

    @field:SerializedName("tahun_mulai_tiga")
    val tahun_mulai_tiga: String? = null,

    @field:SerializedName("tahun_selesai_tiga")
    val tahun_selesai_tiga: String? = null
)