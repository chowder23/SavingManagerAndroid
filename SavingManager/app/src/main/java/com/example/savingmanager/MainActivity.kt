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
import Saving
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.util.UniversalTimeScale
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.Layout
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    lateinit var newSaveActivity: Button
    lateinit var mySavingManager: SavingManager
    lateinit var mainIntent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        var dataHandler = DatabaseHandler(this)
        mainIntent=Intent(this,this::class.java)
        mySavingManager = SavingManager()
        mySavingManager.InitFileManager("saves.txt")
        newSaveActivity = findViewById(R.id.buttonMainActivityAddNewSaving)
        val lv = findViewById<ListView>(R.id.listViewSecondLayout)
        val savingsAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, mySavingManager.getAllData())
        lv.adapter=savingsAdapter
        findViewById<View>(R.id.buildinvoice_step1_layout).visibility = View.VISIBLE
        findViewById<View>(R.id.buildinvoice_step2_layout).visibility = View.INVISIBLE
        val nextButton:Button = findViewById(R.id.buttonNewLayout)
        val backButotn:Button = findViewById(R.id.buttonBackToMain)
        nextButton.setOnClickListener()
        {
            findViewById<View>(R.id.buildinvoice_step1_layout).visibility = View.INVISIBLE
            findViewById<View>(R.id.buildinvoice_step2_layout).visibility = View.VISIBLE
            mySavingManager.addSaving(dataHandler.getSaving(0))
        }
        backButotn.setOnClickListener()
        {
            findViewById<View>(R.id.buildinvoice_step1_layout).visibility = View.VISIBLE
            findViewById<View>(R.id.buildinvoice_step2_layout).visibility = View.INVISIBLE
        }

        newSaveActivity.setOnClickListener()
        {
            val newSavingIntent = Intent(this, NewSavingPageActivity::class.java).apply {
                putExtra("extra_object", dataHandler as Serializable)
            }
            startActivity(newSavingIntent)

            mySavingManager = newSavingIntent.getSerializableExtra("extra_object") as SavingManager
        }
    }

}
