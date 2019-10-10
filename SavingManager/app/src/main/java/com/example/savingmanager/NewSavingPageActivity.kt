package com.example.savingmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import SavingManager

class NewSavingPageActivity : AppCompatActivity() {

    private lateinit var mySavingManager:SavingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_saving_page)
        mySavingManager=intent.getSerializableExtra("extra_object") as SavingManager

        val saveButton:Button=findViewById(R.id.buttonAddNewSaving)
        saveButton.setOnClickListener()
        {
            saveButtonOnClick()
        }
    }

    private fun saveButtonOnClick()
    {
        val saveName: EditText =findViewById(R.id.editTextSavingName)
        val saveMonthlyAmount: EditText =findViewById(R.id.editTextSavingMonthlyAmount)
        val saveDesiredAmount: EditText =findViewById(R.id.editTextSavingDesiredAmount)
        try {
            val monthlySave:Double=saveMonthlyAmount.text.toString().toDouble()
            val desiredAmount:Double=saveDesiredAmount.text.toString().toDouble()
            mySavingManager.addNewSaving(saveName.text.toString(),monthlySave,desiredAmount)

        }
        catch(exp:Exception)
        {
            Toast.makeText(this,"Monthly amount and desired amount must be a number!", Toast.LENGTH_LONG).show()
        }

    }
}
