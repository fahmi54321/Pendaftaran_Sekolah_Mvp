package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponsePribadiDua(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemPribadiDua?>? = null
)

data class ResultsItemPribadiDua(

    @field:SerializedName("id_pribadi_dua")
    val id_pribadi_dua: String? = null,

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("no_akte")
    val no_akte: String? = null,

    @field:SerializedName("agama")
    val agama: String? = null,

    @field:SerializedName("kewarganegaraan")
    val kewarganegaraan: String? = null,

    @field:SerializedName("berkebutuhan_khusus")
    val berkebutuhan_khusus: String? = null,

    @field:SerializedName("alamat_jalan")
    val alamat_jalan: String? = null,

    @field:SerializedName("rt")
    val rt: String? = null,

    @field:SerializedName("rw")
    val rw: String? = null
)