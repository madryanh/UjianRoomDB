package com.adl.ujianroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adl.ujianroomdb.R
import com.adl.ujianroomdb.database.model.DataUserModel

class DataUserAdapter(val data :ArrayList<DataUserModel>) : RecyclerView.Adapter<DataUserViewHolder>() {
    lateinit var parent: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataUserViewHolder {
        this.parent = parent

        return DataUserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: DataUserViewHolder, position: Int) {
        holder.bindData(this@DataUserAdapter,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}