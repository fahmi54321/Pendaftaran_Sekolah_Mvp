package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseAyahKandung(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemAyahKandung?>? = null
)

data class ResultsItemAyahKandung(

    @field:SerializedName("id_ayah_kandung")
    val id_ayah_kandung: String? = null,

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("nama_ayah_kandung")
    val nama_ayah_kandung: String? = null,

    @field:SerializedName("nik_ayah")
    val nik_ayah: String? = null,

    @field:SerializedName("tahun_lahir_ayah")
    val tahun_lahir_ayah: String? = null,

    @field:SerializedName("pendidikan_ayah")
    val pendidikan_ayah: String? = null,

    @field:SerializedName("pekerjaan_ayah")
    val pekerjaan_ayah: String? = null,

    @field:SerializedName("penghasilan_bulanan_ayah")
    val penghasilan_bulanan_ayah: String? = null,

    @field:SerializedName("berkebutuhan_khusus_ayah")
    val berkebutuhan_khusus_ayah: String? = null
)