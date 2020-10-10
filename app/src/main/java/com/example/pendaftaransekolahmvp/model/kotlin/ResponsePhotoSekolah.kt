package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponsePhotoSekolah(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemPhotoSekolah?>? = null
)

data class ResultsItemPhotoSekolah(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("photo")
    val photo: String? = null,

    @field:SerializedName("deskripsi")
    val deskripsi: String? = null
)