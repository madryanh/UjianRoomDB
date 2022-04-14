package com.adl.ujianroomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adl.ujianroomdb.database.dao.DataUserDao
import com.adl.ujianroomdb.database.model.DataUserModel

@Database( version = 1, entities = [DataUserModel::class])
abstract class UserDatabase:RoomDatabase() {

    abstract fun dataUserDao():DataUserDao

    companion object{

        var instance:UserDatabase?=null

        @Synchronized
        fun getInstance(ctx: Context):UserDatabase{

            if(instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    UserDatabase::class.java,
                    "db_userData"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }

    }


}