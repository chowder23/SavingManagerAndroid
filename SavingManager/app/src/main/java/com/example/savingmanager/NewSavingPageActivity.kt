package com.example.savingmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import Saving

class NewSavingPageActivity : AppCompatActivity() {
    var dataHandler = DatabaseHandler(this)
    private lateinit var mySavingManager: SavingManager
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
        var done = false
        val saveName: EditText =findViewById(R.id.editTextSavingName)
        val saveMonthlyAmount: EditText =findViewById(R.id.editTextSavingMonthlyAmount)
        val saveDesiredAmount: EditText =findViewById(R.id.editTextSavingDesiredAmount)
        try {
            val monthlySave:Double=saveMonthlyAmount.text.toString().toDouble()
            val desiredAmount:Double=saveDesiredAmount.text.toString().toDouble()
            val newSaving =
                Saving(
                    mySavingManager.nextId(),
                    saveName.text.toString(),
                    monthlySave,
                    desiredAmount
                )
            mySavingManager.addNewSaving(saveName.text.toString(),monthlySave,desiredAmount)
            done=true
            dataHandler.addSaving(newSaving)

        }
        catch(exp:Exception)
        {
            if(exp is ExistingSavingException)
                Toast.makeText(this,exp.message, Toast.LENGTH_LONG).show()
            else if (exp is FileManagerNotInitException)
                Toast.makeText(this,exp.message, Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this,"Monthly amount and desired amount must be a number!", Toast.LENGTH_LONG).show()


        }
        if (done)
        finish()


    }
}
