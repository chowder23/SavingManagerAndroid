import android.view.ViewParent
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.function.BooleanSupplier

class Saving( var name:String, var monthlySavingAmount:Double, var desiredAmmount:Double, var savingId:Int=0,var SavingStartAmount:Double= 0.0):Serializable {
    var savingStartDateTime =  DateTimeFormatter.ISO_INSTANT.format(Instant.now())

    private var elapsedMonths:Int = savingStartDateTime.toString().substring(5,7).toInt() -
            DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString().substring(5,7).toInt()
    private var savedAmount:Double=0.0

    fun getDesiredAmount():Int
    {
        return desiredAmmount.toInt()
    }

    fun getSavingDateTime():String
    {
        return savingStartDateTime.toString().substring(0,10)
    }
    fun toSaveFormat():String{
        return "$name|$monthlySavingAmount|$desiredAmmount|$savingStartDateTime"
    }

    fun getElapsedMonths():Int
    {
        return elapsedMonths
    }

    fun getSavingAmount():Double
    {
        return monthlySavingAmount*elapsedMonths + SavingStartAmount
    }

    fun getMonthsToReachGoal():Int
    {
        return ((desiredAmmount-savedAmount)/monthlySavingAmount).toInt()-getElapsedMonths()
    }

    fun getId():Int
    {
        return savingId
    }

    fun addToSavedAmount(amount:Double)
    {
        savedAmount+=amount
    }
    override fun equals(other: Any?): Boolean {
        val otherSaving = other as Saving
        return otherSaving.name.toLowerCase() == name.toLowerCase()
    }
    override fun toString(): String {
        return "Saving name: $name \n" +
                "Saving amount: ${getSavingAmount()} \n" +
                "Monthly saving: $monthlySavingAmount \n" +
                "Desired amount: $desiredAmmount \n" +
                "Start date: ${savingStartDateTime} \n" +
                "Elapsed months: ${getElapsedMonths()} \n" +
                "Time to reach goal: ${getMonthsToReachGoal()}"
    }
}