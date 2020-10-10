package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponseIbuKandung(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("result")
    val results: List<ResultsItemIbuKandung?>? = null
)

data class ResultsItemIbuKandung(

    @field:SerializedName("id_user")
    val id_user: String? = null,

    @field:SerializedName("id_ibu_kandung")
    val id_ibu_kandung: String? = null,

    @field:SerializedName("nama_ibu_kandung")
    val nama_ibu_kandung: String? = null,

    @field:SerializedName("nik_ibu")
    val nik_ibu: String? = null,

    @field:SerializedName("tahun_lahir_ibu")
    val tahun_lahir_ibu: String? = null,

    @field:SerializedName("pendidikan_ibu")
    val pendidikan_ibu: String? = null,

    @field:SerializedName("pekerjaan_ibu")
    val pekerjaan_ibu: String? = null,

    @field:SerializedName("penghasilan_bulanan_ibu")
    val penghasilan_bulanan_ibu: String? = null,

    @field:SerializedName("berkebutuhan_khusus_ibu")
    val berkebutuhan_khusus_ibu: String? = null
)