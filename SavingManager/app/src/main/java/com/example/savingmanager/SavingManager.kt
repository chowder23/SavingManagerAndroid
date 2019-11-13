package com.example.savingmanager
import com.example.savingmanager.ExistingSavingException
import com.example.savingmanager.FileManagerNotInitException
import java.io.Serializable
import Saving


class SavingManager:Serializable {

    private var mySalary:Double = 0.0
    var savings = mutableListOf<Saving>()

    fun setMySalary(salary:Double)
    {
        mySalary=salary;
    }

    fun getMySalary():Int
    {
        return mySalary.toInt()
    }

    fun getRemainingSalary():Int
    {
        var tmpSalary:Double = getMySalary().toDouble();
        for (saving in savings)
        {
            tmpSalary-=saving.monthlySavingAmount
        }
        return tmpSalary.toInt()
    }

    fun addSaving(saving:Saving)
    {
        savings.add(saving)
    }
     fun nextId():Int
    {
        var nextId:Int =0
        for (saving in savings)
        {
            if(saving.getId()>=nextId) nextId=saving.getId()+1
        }

        return nextId
    }
    private fun checkExistingSavingByName(newSaving:Saving):Boolean
    {
        return savings.contains(newSaving)
    }

    fun getSavingsNames():List<String>
    {

        var savingsNames = mutableListOf<String>()
        for(saving in savings)
            {
                savingsNames.add(saving.name)
            }
        return savingsNames
    }


    private fun getSavingsAsString():List<String>
    {
        var savingsAsString= mutableListOf<String>()
        for (saving in savings)
        {
            savingsAsString.add(saving.toSaveFormat())
        }
        return savingsAsString
    }


    fun addAmountToSaving(savingName:String,amount:Double)
    {
        (savings.find { it.name==savingName })?.addToSavedAmount(amount)
    }

    fun getAllData():List<String>
    {
        var allData = mutableListOf<String>()
        allData.add("My salary: ${getMySalary()}")
        allData.add("Remaining salary: ${getRemainingSalary()}")
        allData.add("Number of savings: ${savings.count()}")
        allData.add("Savings: \n")
        for (saving in savings)
        {
            allData.add(saving.toString() + "\n")
        }
        return allData
    }
}


