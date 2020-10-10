package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponsePribadiTiga(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemPribadiTiga?>? = null
)

data class ResultsItemPribadiTiga(

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("id_pribadi_tiga")
    val id_pribadi_tiga: String? = null,

    @field:SerializedName("nama_dusun")
    val nama_dusun: String? = null,

    @field:SerializedName("nama_kelurahan")
    val nama_kelurahan: String? = null,

    @field:SerializedName("kecamatan")
    val kecamatan: String? = null,

    @field:SerializedName("kode_pos")
    val kode_pos: String? = null,

    @field:SerializedName("lintang")
    val lintang: String? = null,

    @field:SerializedName("bujur")
    val bujur: String? = null
)