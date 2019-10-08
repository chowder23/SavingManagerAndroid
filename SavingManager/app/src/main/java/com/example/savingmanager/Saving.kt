import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.function.BooleanSupplier

class Saving(private val savingId:Int, val name:String, val monthlySavingAmount:Double, val desiredAmmount:Double) {
    val savingStartDateTime = LocalDate.now()
    private var elapsedMonths:Int = savingStartDateTime.monthValue.toInt() - LocalDateTime.now().monthValue.toInt()
    private var savedAmount:Double=0.0

    fun toSaveFormat():String{
        return "$name|$monthlySavingAmount|$desiredAmmount|$savingStartDateTime"
    }

    fun getElapsedMonths():Int
    {
        return elapsedMonths
    }

    fun getSavingAmount():Double
    {
        return monthlySavingAmount*elapsedMonths + savedAmount
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