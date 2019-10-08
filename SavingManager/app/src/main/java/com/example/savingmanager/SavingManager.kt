class SavingManager {

    private var mySalary:Double = 0.0
    var myFileManager = FileManager("BadBoy.txt")
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

    fun InitFileManager(fileName:String)
    {
        myFileManager.setMyFile(fileName)
    }

    fun addNewSaving( name:String, monthlySavingAmount:Double, desiredAmount:Double)
    {
        val newSaving = Saving(nextId(),name,monthlySavingAmount,desiredAmount)
        if(checkExistingSavingByName(newSaving)) throw Exception("Ez a megtakarítás: ${newSaving.name} már létezik!")
        else savings.add(newSaving)
    }

    private fun nextId():Int
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

    fun Save()
    {
        checkInitialization()
        myFileManager.saveDataToFile(getSavingsAsString())
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

    private fun checkInitialization()
    {
        if(myFileManager.IsInitialized()) else throw Exception("File Manager hasn't been initialized yet!")
    }

    fun Load()
    {
        checkInitialization()
        for (line in myFileManager.loadDataFromFile())
        {
            var splittedLine = line.split('|')
            addNewSaving(splittedLine[0],splittedLine[1].toDouble(),splittedLine[2].toDouble())
        }
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