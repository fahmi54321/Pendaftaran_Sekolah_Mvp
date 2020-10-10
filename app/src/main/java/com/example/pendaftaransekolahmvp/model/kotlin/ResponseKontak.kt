package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseKontak(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemKontak?>? = null
)

data class ResultsItemKontak(

    @field:SerializedName("id_kontak")
    val id_kontak: String? = null,

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("no_telp_rumah")
    val no_telp_rumah: String? = null,

    @field:SerializedName("no_hp")
    val no_hp: String? = null,

    @field:SerializedName("email_address")
    val email_address: String? = null
)