package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponsePribadiEmpat(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemPribadiEmpat?>? = null
)

data class ResultsItemPribadiEmpat(

    @field:SerializedName("id_pribadi_empat")
    val id_pribadi_empat: String? = null,

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("tempat_tinggal")
    val tempat_tinggal: String? = null,

    @field:SerializedName("mode_transportasi")
    val mode_transportasi: String? = null,

    @field:SerializedName("no_kk")
    val no_kk: String? = null,

    @field:SerializedName("anak_keberapa")
    val anak_keberapa: String? = null,

    @field:SerializedName("penerima_kps")
    val penerima_kps: String? = null,

    @field:SerializedName("no_kps")
    val no_kps: String? = null
)