package com.adl.ujianroomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.adl.ujianroomdb.adapter.DataUserAdapter
import com.adl.ujianroomdb.database.UserDatabase
import com.adl.ujianroomdb.database.model.DataUserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDatabase

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                //if(result.data?.hasExtra("data")!!){
                //lstMobile.add(result.data!!.extras?.getParcelable<MobileModel>("data")!!)
                //mobileadapter.notifyDataSetChanged()
                //}
                GlobalScope.launch {
                    lstData.clear()
                    lstData.addAll(ArrayList(getAllData()))

                    this@MainActivity.runOnUiThread({
                        useradapter.notifyDataSetChanged()
                    })
                }

            }
        }

    lateinit var useradapter: DataUserAdapter
    var lstData = ArrayList<DataUserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "userdatadb"
        ).build()

        GlobalScope.launch {

            lstData = ArrayList(getAllData())

            this@MainActivity.runOnUiThread({
                useradapter = DataUserAdapter(lstData)
                lstItemUser.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = useradapter
                }
            })

        }

        btnAddUser.setOnClickListener({
            val intent = Intent(this@MainActivity, AddUser::class.java)
            resultLauncher.launch(intent)
        })

    }
    fun getAllData(): List<DataUserModel> {
        return UserDatabase.getInstance(this@MainActivity).dataUserDao().getAll()

    }
}