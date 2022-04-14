package com.adl.ujianroomdb.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DataUserModel(@PrimaryKey(autoGenerate = true) val id:Long = 0,
                         val nama:String, val gender:String,
                         val umur:String, val status:String) : Parcelable