package com.example.pendaftaransekolahmvp.network.kotlin

import com.example.pendaftaransekolahmvp.model.java.ResponsePribadiSatu
import com.example.pendaftaransekolahmvp.model.kotlin.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*


//todo 2.1 Config API
interface ConfigApi {

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email_address") email_address:String,
        @Field("password") password:String
    ): Single<ResponseUser>

    @GET("selectUser.php")
    fun selectUser(
        @Query("id_user") id_user:String
    ): Call<ResponseUser>

    @GET("selectPhotoSekolah.php")
    fun selectPhotoSekolah(
    ): Call<ResponsePhotoSekolah>

    @FormUrlEncoded
    @POST("tambahPribadiDua.php")
    fun tambahPribadiDua(
        @Field("id_user") id_user:String,
        @Field("no_akte") no_akte:String,
        @Field("agama") agama:String,
        @Field("kewarganegaraan") kewarganegaraan:String,
        @Field("berkebutuhan_khusus") berkebutuhan_khusus:String,
        @Field("alamat_jalan") alamat_jalan:String,
        @Field("rt") rt:String,
        @Field("rw") rw:String
    ): Call<ResponsePribadiDua>

    @FormUrlEncoded
    @POST("tambahPribadiTiga.php")
    fun tambahPribadiTiga(
        @Field("id_user") id_user:String,
        @Field("nama_dusun") nama_dusun:String,
        @Field("nama_kelurahan") nama_kelurahan:String,
        @Field("kecamatan") kecamatan:String,
        @Field("kode_pos") kode_pos:String,
        @Field("lintang") lintang:String,
        @Field("bujur") bujur:String
    ): Call<ResponsePribadiTiga>

    @FormUrlEncoded
    @POST("tambahPribadiEmpat.php")
    fun tambahPribadiEmpat(
        @Field("id_user") id_user:String,
        @Field("tempat_tinggal") tempat_tinggal:String,
        @Field("mode_transportasi") mode_transportasi:String,
        @Field("no_kk") no_kk:String,
        @Field("anak_keberapa") anak_keberapa:String,
        @Field("penerima_kps") penerima_kps:String,
        @Field("no_kps") no_kps:String
    ): Call<ResponsePribadiEmpat>

    @FormUrlEncoded
    @POST("tambahPribadiLima.php")
    fun tambahPribadiLima(
        @Field("id_user") id_user:String,
        @Field("usulan_dari_sekolah") usulan_dari_sekolah:String,
        @Field("penerima_kip") penerima_kip:String,
        @Field("nomor_kip") nomor_kip:String,
        @Field("nama_tertera_kip") nama_tertera_kip:String,
        @Field("terima_fisik_kip") terima_fisik_kip:String,
        @Field("alasan_menerima_kip") alasan_menerima_kip:String,
        @Field("bank") bank:String,
        @Field("no_rek_bank") no_rek_bank:String,
        @Field("nama_rek") nama_rek:String
    ): Call<ResponsePribadiLima>

    @FormUrlEncoded
    @POST("tambahAyahKandung.php")
    fun tambahAyahKandung(
        @Field("id_user") id_user:String,
        @Field("nama_ayah_kandung") nama_ayah_kandung:String,
        @Field("nik_ayah") nik_ayah:String,
        @Field("tahun_lahir_ayah") tahun_lahir_ayah:String,
        @Field("pendidikan_ayah") pendidikan_ayah:String,
        @Field("pekerjaan_ayah") pekerjaan_ayah:String,
        @Field("penghasilan_bulanan_ayah") penghasilan_bulanan_ayah:String,
        @Field("berkebutuhan_khusus_ayah") berkebutuhan_khusus_ayah:String
    ): Call<ResponseAyahKandung>

    @FormUrlEncoded
    @POST("tambahIbuKandung.php")
    fun tambahIbuKandung(
        @Field("id_user") id_user:String,
        @Field("nama_ibu_kandung") nama_ibu_kandung:String,
        @Field("nik_ibu") nik_ibu:String,
        @Field("tahun_lahir_ibu") tahun_lahir_ibu:String,
        @Field("pendidikan_ibu") pendidikan_ibu:String,
        @Field("pekerjaan_ibu") pekerjaan_ibu:String,
        @Field("penghasilan_bulanan_ibu") penghasilan_bulanan_ibu:String,
        @Field("berkebutuhan_khusus_ibu") berkebutuhan_khusus_ibu:String
    ): Call<ResponseIbuKandung>

    @FormUrlEncoded
    @POST("tambahWali.php")
    fun tambahWali(
        @Field("id_user") id_user:String,
        @Field("nama_wali") nama_wali:String,
        @Field("nik_wali") nik_wali:String,
        @Field("tahun_lahir_wali") tahun_lahir_wali:String,
        @Field("pendidikan_wali") pendidikan_wali:String,
        @Field("pekerjaan_wali") pekerjaan_wali:String,
        @Field("penghasilan_bulanan_wali") penghasilan_bulanan_wali:String
    ): Call<ResponseWali>

    @FormUrlEncoded
    @POST("tambahKontak.php")
    fun tambahKontak(
        @Field("id_user") id_user:String,
        @Field("no_telp_rumah") no_telp_rumah:String,
        @Field("no_hp") no_hp:String,
        @Field("email_address") email_address:String
    ): Call<ResponseKontak>

    @FormUrlEncoded
    @POST("tambahDataPeriodik.php")
    fun tambahDataPeriodik(
        @Field("id_user") id_user:String,
        @Field("tinggi_badan") tinggi_badan:String,
        @Field("berat_badan") berat_badan:String,
        @Field("jarak_tempat_tinggal") jarak_tempat_tinggal:String,
        @Field("km") km:String,
        @Field("waktu_tempuh_jam") waktu_tempuh_jam:String,
        @Field("waktu_tempuh_menit") waktu_tempuh_menit:String,
        @Field("waktu_tempuh_ke_sekolah") waktu_tempuh_ke_sekolah:String,
        @Field("jml_saudara") jml_saudara:String
    ): Call<ResponseDataPeriodik>

    @FormUrlEncoded
    @POST("tambahDataPrestasi.php")
    fun tambahDataPrestasi(
        @Field("id_user") id_user:String,
        @Field("jenis_prestasi") jenis_prestasi:String,
        @Field("tingkat") tingkat:String,
        @Field("nama_prestasi_satu") nama_prestasi_satu:String,
        @Field("tahun_satu") tahun_satu:String,
        @Field("penyelenggara_satu") penyelenggara_satu:String,
        @Field("nama_prestasi_dua") nama_prestasi_dua:String,
        @Field("tahun_dua") tahun_dua:String,
        @Field("penyelenggara_dua") penyelenggara_dua:String,
        @Field("nama_prestasi_tiga") nama_prestasi_tiga:String,
        @Field("tahun_tiga") tahun_tiga:String,
        @Field("penyelenggara_tiga") penyelenggara_tiga:String
    ): Call<ResponseDataPrestasi>

    @FormUrlEncoded
    @POST("tambahDataBeasiswa.php")
    fun tambahDataBeasiswa(
        @Field("id_user") id_user:String,
        @Field("jenis_beasiswa_satu") jenis_beasiswa_satu:String,
        @Field("keterangan_satu") keterangan_satu:String,
        @Field("tahun_mulai_satu") tahun_mulai_satu:String,
        @Field("tahun_selesai_satu") tahun_selesai_satu:String,
        @Field("jenis_beasiswa_dua") jenis_beasiswa_dua:String,
        @Field("keterangan_dua") keterangan_dua:String,
        @Field("tahun_mulai_dua") tahun_mulai_dua:String,
        @Field("tahun_selesai_dua") tahun_selesai_dua:String,
        @Field("jenis_beasiswa_tiga") jenis_beasiswa_tiga:String,
        @Field("keterangan_tiga") keterangan_tiga:String,
        @Field("tahun_mulai_tiga") tahun_mulai_tiga:String,
        @Field("tahun_selesai_tiga") tahun_selesai_tiga:String
    ): Call<ResponseDataBeasiswa>

    @GET("selectPribadiSatu.php")
    fun selectPribadiSatu(
        @Query("id_user") id_user:String
    ): Call<ResponsePribadiSatuKotlin>

    @GET("selectPribadiDua.php")
    fun selectPribadiDua(
        @Query("id_user") id_user:String
    ): Call<ResponsePribadiDua>

    @GET("selectPribadiTiga.php")
    fun selectPribadiTiga(
        @Query("id_user") id_user:String
    ): Call<ResponsePribadiTiga>

    @GET("selectPribadiEmpat.php")
    fun selectPribadiEmpat(
        @Query("id_user") id_user:String
    ): Call<ResponsePribadiEmpat>

    @GET("selectPribadiLima.php")
    fun selectPribadiLima(
        @Query("id_user") id_user:String
    ): Call<ResponsePribadiLima>

    @GET("selectAyahKandung.php")
    fun selectAyahKandung(
        @Query("id_user") id_user:String
    ): Call<ResponseAyahKandung>

    @GET("selectIbuKandung.php")
    fun selectIbuKandung(
        @Query("id_user") id_user:String
    ): Call<ResponseIbuKandung>

    @GET("selectWali.php")
    fun selectWali(
        @Query("id_user") id_user:String
    ): Call<ResponseWali>

    @GET("selectKontak.php")
    fun selectKontak(
        @Query("id_user") id_user:String
    ): Call<ResponseKontak>

    @GET("selectDataPeriodik.php")
    fun selectDataPeriodik(
        @Query("id_user") id_user:String
    ): Call<ResponseDataPeriodik>

    @GET("selectPrestasi.php")
    fun selectDataPrestasi(
        @Query("id_user") id_user:String
    ): Call<ResponseDataPrestasi>

    @GET("selectBeasiswa.php")
    fun selectDataBeasiswa(
        @Query("id_user") id_user:String
    ): Call<ResponseDataBeasiswa>
}