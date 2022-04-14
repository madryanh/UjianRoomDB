package com.adl.ujianroomdb.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.adl.ujianroomdb.database.model.DataUserModel

@Dao
interface DataUserDao {

    @Insert
    fun insertDataUser(dataUser: DataUserModel)

    @Delete
    fun deleteDataUser(dataUser: DataUserModel)

    @Query("SELECT * FROM DataUserModel")
    fun getAll(): List<DataUserModel>


}