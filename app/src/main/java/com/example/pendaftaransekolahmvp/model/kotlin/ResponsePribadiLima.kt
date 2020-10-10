package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponsePribadiLima(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemPribadiLima?>? = null
)

data class ResultsItemPribadiLima(

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("id_pribadi_lima")
    val id_pribadi_lima: String? = null,

    @field:SerializedName("usulan_dari_sekolah")
    val usulan_dari_sekolah: String? = null,

    @field:SerializedName("penerima_kip")
    val penerima_kip: String? = null,

    @field:SerializedName("nomor_kip")
    val nomor_kip: String? = null,

    @field:SerializedName("nama_tertera_kip")
    val nama_tertera_kip: String? = null,

    @field:SerializedName("terima_fisik_kip")
    val terima_fisik_kip: String? = null,

    @field:SerializedName("alasan_menerima_kip")
    val alasan_menerima_kip: String? = null,

    @field:SerializedName("bank")
    val bank: String? = null,

    @field:SerializedName("no_rek_bank")
    val no_rek_bank: String? = null,

    @field:SerializedName("nama_rek")
    val nama_rek: String? = null
)