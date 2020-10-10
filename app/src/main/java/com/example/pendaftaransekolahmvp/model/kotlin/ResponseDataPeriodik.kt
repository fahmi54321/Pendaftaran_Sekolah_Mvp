package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseDataPeriodik(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemDataPeriodik?>? = null
)

data class ResultsItemDataPeriodik(

    @field:SerializedName("id_data_periodik")
    val id_data_periodik: String? = null,

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("tinggi_badan")
    val tinggi_badan: String? = null,

    @field:SerializedName("berat_badan")
    val berat_badan: String? = null,

    @field:SerializedName("jarak_tempat_tinggal")
    val jarak_tempat_tinggal: String? = null,

    @field:SerializedName("km")
    val km: String? = null,

    @field:SerializedName("waktu_tempuh_jam")
    val waktu_tempuh_jam: String? = null,

    @field:SerializedName("waktu_tempuh_menit")
    val waktu_tempuh_menit: String? = null,

    @field:SerializedName("waktu_tempuh_ke_sekolah")
    val waktu_tempuh_ke_sekolah: String? = null,

    @field:SerializedName("jml_saudara")
    val jml_saudara: String? = null
)