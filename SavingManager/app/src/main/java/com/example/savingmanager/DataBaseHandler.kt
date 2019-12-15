package com.example.savingmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import Saving


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ( " +
                "$SAVINGID INTEGER PRIMARY KEY, " +
                "$SAVINGNAME TEXT ," +
                "$MONTHLYSAVINGAMOUNT TEXT , " +
                "$DESIREDAMOUNT TEXT, " +
                "$SAVINGAMOUNT TEXT, " +
                "$SAVINGSTARTDATETIME TEXT )"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun createTable()
    {
        var db = this.writableDatabase
        val createTableSQL= "CREATE TABLE $TABLE_NAME ( " +
                "$SAVINGID INTEGER PRIMARY KEY, " +
                "$SAVINGNAME TEXT ," +
                "$MONTHLYSAVINGAMOUNT TEXT , " +
                "$DESIREDAMOUNT TEXT, " +
                "$SAVINGAMOUNT TEXT, " +
                "$SAVINGSTARTDATETIME TEXT )"
        db.execSQL(createTableSQL)
        db.close()
    }

    fun dropTable()
    {
        var db = this.writableDatabase
        db.execSQL("DROP TABLE $TABLE_NAME")
        db.close()
    }

    fun deleteSaving(saving:Saving)
    {
        var db=this.writableDatabase
        db.delete(TABLE_NAME,"$SAVINGID=?", arrayOf(saving.getId().toString()))
        db.close()

    }

    fun updateSaving(saving:Saving):Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(SAVINGNAME, saving.name)
        values.put(MONTHLYSAVINGAMOUNT,saving.monthlySavingAmount)
        values.put(DESIREDAMOUNT,saving.desiredAmmount)
        values.put(SAVINGAMOUNT,saving.getSavingAmount())
        values.put (SAVINGSTARTDATETIME,saving.savingStartDateTime.toString())
        val _success = db.update(TABLE_NAME, values, "$SAVINGID=?", arrayOf(saving.getId().toString()))
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun addSaving(saving:Saving): Boolean {
        if(!checkSave(saving))
            return false
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(SAVINGNAME, saving.name)
        values.put(MONTHLYSAVINGAMOUNT,saving.monthlySavingAmount)
        values.put(DESIREDAMOUNT,saving.desiredAmmount)
        values.put(SAVINGAMOUNT,saving.getSavingAmount())
        values.put (SAVINGSTARTDATETIME,saving.savingStartDateTime.toString())
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }
    fun getSaving(_id: Int): Saving {
        val saving:Saving= Saving("",0.0,0.0)
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                saving.savingId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SAVINGID)))
                saving.name = cursor.getString(cursor.getColumnIndex(SAVINGNAME))
                saving.monthlySavingAmount = cursor.getString(cursor.getColumnIndex(
                    MONTHLYSAVINGAMOUNT)).toDouble()
                saving.addToSavedAmount(cursor.getString(cursor.getColumnIndex(SAVINGAMOUNT)).toDouble())
                saving.desiredAmmount = cursor.getString(cursor.getColumnIndex(DESIREDAMOUNT)).toDouble()
                saving.savingStartDateTime =cursor.getString(cursor.getColumnIndex(
                    SAVINGSTARTDATETIME))
            }
        }
        cursor.close()
        return saving
    }

    fun getSavings():List<Saving>
    {
        var savings = mutableListOf<Saving>()
        val db= writableDatabase
        val SELECT_QUERY ="SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(SELECT_QUERY,null)
        if(cursor != null)
        {

            while(cursor.moveToNext())
            {
                var saving:Saving= Saving("",0.0,0.0)
                saving.savingId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SAVINGID)))
                saving.name = cursor.getString(cursor.getColumnIndex(SAVINGNAME))
                saving.monthlySavingAmount = cursor.getString(cursor.getColumnIndex(
                    MONTHLYSAVINGAMOUNT)).toDouble()
                saving.SavingStartAmount= cursor.getString(cursor.getColumnIndex(SAVINGAMOUNT)).toDouble()
                saving.desiredAmmount = cursor.getString(cursor.getColumnIndex(DESIREDAMOUNT)).toDouble()
                saving.savingStartDateTime =cursor.getString(cursor.getColumnIndex(
                    SAVINGSTARTDATETIME))
                savings.add(saving)
            }
        }

        return savings
    }

    fun checkSave(saving:Saving):Boolean
    {
        var savings = getSavings()
        for (tmpSaving in savings)
        {
            if(tmpSaving.name.toLowerCase() == saving.name.toLowerCase())
                return false
        }
        return true
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "MySavingsDB"
        private val TABLE_NAME = "Savings"
        private val SAVINGID = "SavingId"
        private val SAVINGNAME = "SavingName"
        private val SAVINGAMOUNT = "SavingAmount"
        private val MONTHLYSAVINGAMOUNT = "MonthlySavingAmount"
        private val DESIREDAMOUNT = "DesiredAmount"
        private val SAVINGSTARTDATETIME = "SavingStartDateTime"

    }
}