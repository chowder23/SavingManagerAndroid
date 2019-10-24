package com.example.savingmanager

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutInit()



    }

    fun layoutInit()
    {
        findViewById<View>(R.id.layoutMain).visibility = View.VISIBLE
        findViewById<View>(R.id.layoutShowAllSaving).visibility =View.INVISIBLE
        findViewById<View>(R.id.layoutAddNewSaving).visibility =View.INVISIBLE
    }

    fun changeToAddSavingLayout(view:View)
    {
        findViewById<View>(R.id.layoutMain).visibility = View.INVISIBLE
        findViewById<View>(R.id.layoutAddNewSaving).visibility =View.VISIBLE
    }

    fun changeToShowAllSavingLayout(view:View)
    {
        findViewById<View>(R.id.layoutMain).visibility = View.INVISIBLE
        findViewById<View>(R.id.layoutShowAllSaving).visibility =View.VISIBLE
    }

    fun changeToMainLayout(view: View)
    {
        layoutInit()
    }


}
