package com.adl.ujianroomdb

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.adl.ujianroomdb.database.UserDatabase
import com.adl.ujianroomdb.database.model.DataUserModel
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.jar.Manifest

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        button.isEnabled = false
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    111)
        else
            button.isEnabled = true

        button.setOnClickListener{
            var city = editText.text.toString()
            var gc = Geocoder(this, Locale.getDefault())
            var addresses = gc.getFromLocationName(city,2)
            var address = addresses.get(0)
            textView6.visibility = View.VISIBLE
            textView6.setText("${address.latitude} \n ${address.longitude}\n ${address.locality}")
        }

        val gender = resources.getStringArray(R.array.Gender)
        val status = resources.getStringArray(R.array.Status)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinner2 = findViewById<Spinner>(R.id.spinner2)

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, gender
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@AddUser,
                        getString(R.string.selected_item) + "" +
                                "" + gender[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        if (spinner2 != null) {
            val adapter2 = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, status
            )
            spinner2.adapter = adapter2

            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@AddUser,
                        getString(R.string.selected_item2) + "" +
                                "" + status[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        btnSendData.setOnClickListener({
            val dataUser = DataUserModel (0, txtInputNama.text.toString(), spinner.selectedItem.toString(), txtInputUmur.text.toString(), spinner2.selectedItem.toString())

            GlobalScope.launch {
                UserDatabase.getInstance(this@AddUser).dataUserDao().insertDataUser(dataUser)

                val intent = Intent()
                intent.putExtra("data",dataUser)
                setResult(RESULT_OK,intent)
                finish()
            }
        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            button.isEnabled = true
    }

}