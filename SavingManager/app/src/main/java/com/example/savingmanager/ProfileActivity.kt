package com.example.savingmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class ProfileActivity : AppCompatActivity() {

    lateinit var profileDB:ProfileDbHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_profile_layout)
        profileDB= ProfileDbHandler(this)
    }


    fun saveProfile(view:View)
    {
        var profileName = findViewById<EditText>(R.id.editTextProfileName).text.toString()
        var profileSalary = findViewById<EditText>(R.id.editTextProfileSalary).text.toString().toDouble()
        var myProfile = Profile(profileName,profileSalary)
        profileDB.addProfile(myProfile)
        var intent = Intent(this,MainActivity::class.java)
        intent.putExtra("EXTRA",myProfile)
        startActivity(intent)
    }
}
