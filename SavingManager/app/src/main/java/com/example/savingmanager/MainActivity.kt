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
import android.text.Editable
import android.text.Layout
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_saving_layout.*
import java.io.Serializable



class MainActivity : AppCompatActivity() {

    val dbHandler:DatabaseHandler = DatabaseHandler(this)
    var myProfile = Profile("",0.0)
    var profileDB = ProfileDbHandler(this)
    lateinit var listSaving:List<Saving>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listSaving = dbHandler.getSavings()
        setContentView(R.layout.activity_main)
        startProfileActivity()
        layoutInit()
        RefreshData()


        buttonUpdate.setOnClickListener {
            val saving = Saving(
                editTextSavingName.text.toString(),
                editTextMonthlyAmount.text.toString().toDouble(),
                editTextDesiredAmount.text.toString().toDouble(),
                Integer.parseInt(textViewSavingId.text.toString()),
                editTextSavingAmount.text.toString().toDouble()

            )

            showToast(dbHandler.updateSaving(saving).toString())
            RefreshData()
        }

        buttonDelete.setOnClickListener {
            val saving = Saving(
                editTextSavingName.text.toString(),
                editTextMonthlyAmount.text.toString().toDouble(),
                editTextDesiredAmount.text.toString().toDouble(),
                Integer.parseInt(textViewSavingId.text.toString())
            )
            dbHandler.deleteSaving(saving)
            RefreshData()
        }

    }

    fun startProfileActivity()
    {
        var profiles = profileDB.getProfiles()
        if(profiles.isEmpty()) {
            var intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        else
        {
            myProfile = profiles[0]
            actualizeSalary()
        }

    }

    fun RefreshData()
    {
        listSaving = dbHandler.getSavings()
        val adapter = ListSavingAdapter(this@MainActivity,listSaving,
            editTextSavingName,
            editTextSavingAmount,
            editTextMonthlyAmount,
            editTextDesiredAmount,
            textViewSavingId)
        listViewAllSaving.adapter=adapter

    }
    fun actualizeSalary()
    {
        var savings = dbHandler.getSavings()
        for(saving in savings)
        {
            myProfile.salary-=saving.monthlySavingAmount
        }
        findViewById<TextView>(R.id.textViewProfileInfo).text = myProfile.toString()
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
        RefreshData()

        //var layoutAllSaving:ListView = findViewById(R.id.listViewAllSaving)
       // layoutAllSaving.adapter=ArrayAdapter(this, android.R.layout.simple_list_item_1,dbHandler.getSavings())
    }

    fun addNewSaving(view:View)
    {
        val savingName = findViewById<EditText>(R.id.editTextSavingNameAdd).text.toString()
        val monthlySavingAmount:Double = findViewById<EditText>(R.id.editTextSavingMonthlyAmount).text.toString().toDouble()
        val desiredamount = findViewById<EditText>(R.id.editTextSavingDesiredAmount).text.toString().toDouble()
        val saving = Saving(savingName,monthlySavingAmount,desiredamount)
        findViewById<EditText>(R.id.editTextSavingNameAdd).text = "".toEditable()
        findViewById<EditText>(R.id.editTextSavingMonthlyAmount).text = "".toEditable()
        findViewById<EditText>(R.id.editTextSavingDesiredAmount).text = "".toEditable()
        var succes = dbHandler.addSaving(saving)
        showToast(if(succes) "Saving added" else "Can't add saving")

    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    fun showToast(message:String)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

    }

    fun changeToMainLayout(view: View)
    {
        layoutInit()
    }


}
