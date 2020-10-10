package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseUser(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemLogin?>? = null
)

data class ResultsItemLogin(

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("email_address")
    val email_address: String? = null,

    @field:SerializedName("nama_lengkap")
    val nama_lengkap: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null,

    @field:SerializedName("confirm_password")
    val confirm_password: String? = null,

    @field:SerializedName("nohp")
    val nohp: String? = null,

    @field:SerializedName("photo")
    val photo: String? = null
)