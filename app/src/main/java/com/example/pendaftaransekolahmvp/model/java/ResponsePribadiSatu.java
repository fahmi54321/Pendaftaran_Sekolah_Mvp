package com.example.pendaftaransekolahmvp.model.java;

import java.util.List;

public class ResponsePribadiSatu {
    private String id_pribadi_satu;
    private String id_user;
    private String nama_pribadi;
    private String jenis_kelamin;
    private String nisn;
    private String foto_nisn;
    private String nik;
    private String foto_nik;
    private String tgl_lahir;
    private String tempat_lahir;
    private String message;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNama_pribadi() {
        return nama_pribadi;
    }

    public void setNama_pribadi(String nama_pribadi) {
        this.nama_pribadi = nama_pribadi;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getId_pribadi_satu() {
        return id_pribadi_satu;
    }

    public void setId_pribadi_satu(String id_pribadi_satu) {
        this.id_pribadi_satu = id_pribadi_satu;
    }


    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getFoto_nisn() {
        return foto_nisn;
    }

    public void setFoto_nisn(String foto_nisn) {
        this.foto_nisn = foto_nisn;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFoto_nik() {
        return foto_nik;
    }

    public void setFoto_nik(String foto_nik) {
        this.foto_nik = foto_nik;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public class ResultItemPribadiSatu
    {
        private List<ResponsePribadiSatu> results;

        public List<ResponsePribadiSatu> getResults() {
            return results;
        }

        public void setResults(List<ResponsePribadiSatu> results) {
            this.results = results;
        }
    }
}
