import java.io.File

class FileManager(private val fileName:String)
{
    private var myFile = File(fileName)
    private var initialized:Boolean = false

    fun setMyFile(fileName:String)
    {
        myFile= File(fileName)
        initialized=true
    }
    fun IsInitialized():Boolean
    {
        return initialized
    }
    fun saveDataToFile(savingsStrings:List<String>)
    {
        myFile.delete()
        for (savingString in savingsStrings)
        {
            myFile.appendText("$savingString\n")
        }
    }

    fun loadDataFromFile():List<String>
    {
        var savingsStrings = mutableListOf<String>()
        for (line in myFile.readLines())
        {
            savingsStrings.add(line)
        }
        return savingsStrings
    }
}