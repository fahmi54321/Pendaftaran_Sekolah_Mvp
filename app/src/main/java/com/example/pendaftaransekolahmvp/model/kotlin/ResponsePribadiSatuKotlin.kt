package com.example.pendaftaransekolahmvp.model.kotlin

import com.google.gson.annotations.SerializedName

data class ResponsePribadiSatuKotlin(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultItem(

	@field:SerializedName("foto_nisn")
	val fotoNisn: String? = null,

	@field:SerializedName("id_user")
	val id_user: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("foto_nik")
	val fotoNik: String? = null,

	@field:SerializedName("nama_pribadi")
	val namaPribadi: String? = null,

	@field:SerializedName("tempat_lahir")
	val tempatLahir: String? = null,

	@field:SerializedName("nisn")
	val nisn: String? = null,

	@field:SerializedName("id_pribadi_satu")
	val idPribadiSatu: String? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,

	@field:SerializedName("tgl_lahir")
	val tglLahir: String? = null
)
