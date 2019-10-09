package com.example.savingmanager

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.savingmanager.ui.main.SectionsPagerAdapter
import SavingManager
import Saving
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    lateinit var mySavingManager: SavingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mySavingManager=SavingManager()
        mySavingManager.InitFileManager("saves.txt")

        val saveButton:Button = findViewById(R.id.saveButton)

        saveButton.setOnClickListener{
            saveButtonOnClick()
            var tempString:String = ""
            for (string in mySavingManager.getAllData())
            {
                tempString+= string
            }
            saveButton.text=tempString
        }

    }

    fun saveButtonOnClick()
    {
        var saveName:EditText=findViewById(R.id.saveSavingName)
        var saveMonthlyamount:EditText=findViewById(R.id.saveMonthlyAmount)
        var saveDesiredAmount:EditText=findViewById(R.id.saveDesiredAmount)
        mySavingManager.addNewSaving(saveName.text.toString(),saveMonthlyamount.text.toString().toDouble(),saveDesiredAmount.text.toString().toDouble())
    }

}