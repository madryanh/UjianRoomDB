package com.adl.ujianroomdb.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

class DataUserViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val nama = view.lblNama
    val gender = view.lblGender
    val umur = view.lblUmur
    val status = view.lblStatus

    fun bindData(adapter: DataUserAdapter, position: Int){

        nama.setText(adapter.data.get(position).nama)
        gender.setText(adapter.data.get(position).gender)
        umur.setText(adapter.data.get(position).umur)
        status.setText(adapter.data.get(position).status)

    }

}