package com.example.savingmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

class NewSavingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_saving_page)
    }


    /*fun saveButtonOnClick()
    {
        var saveName: EditText =findViewById(R.id.saveSavingName)
        var saveMonthlyamount: EditText =findViewById(R.id.saveMonthlyAmount)
        var saveDesiredAmount: EditText =findViewById(R.id.saveDesiredAmount)
        try {
            var monthlySave:Double=saveMonthlyamount.text.toString().toDouble()
            var desiredAmount:Double=saveDesiredAmount.text.toString().toDouble()
            mySavingManager.addNewSaving(saveName.text.toString(),saveMonthlyamount.text.toString().toDouble(),saveDesiredAmount.text.toString().toDouble())

        }
        catch(exp:Exception)
        {
            Toast.makeText(this,"Monthly amount and desired amount must be a number!", Toast.LENGTH_LONG).show()
        }

    }*/
}
