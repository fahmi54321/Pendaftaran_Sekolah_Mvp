package com.example.pendaftaransekolahmvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pendaftaransekolahmvp.R
import com.example.pendaftaransekolahmvp.model.kotlin.ResultsItemPhotoSekolah
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_photo.view.*

class HomeAdapter(var data:List<ResultsItemPhotoSekolah?>?):RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {
    class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var photoSekolah = itemView.photoSekolah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo,parent,false)
        var holder = MainViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size?:0
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var Contants = "http://192.168.43.19/PendaftaranSekolah/foto_sekolah/"
        Glide.with(holder.itemView.context)
            .load(Contants+data?.get(position)?.photo)
            .apply(RequestOptions().error(R.drawable.icon_nopic))
            .into(holder.photoSekolah)
    }
}