package com.example.savingmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import Saving


class ProfileDbHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE =
                "CREATE TABLE $TABLE_NAME (" +
                PROFILE_ID + " INTEGER PRIMARY KEY," +
                PROFILENAME + " TEXT," +
                SALARY + " INTEGER)"

        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun createTable()
    {
        var db = this.writableDatabase
        val createTableSQL= "CREATE TABLE $TABLE_NAME ( " +
                "$PROFILE_ID INTEGER PRIMARY KEY, " +
                "$PROFILENAME TEXT ," +
                "$SALARY INTEGER )"
        db.execSQL(createTableSQL)
        db.close()
    }

    fun dropTable()
    {
        var db = this.writableDatabase
        db.execSQL("DROP TABLE $TABLE_NAME")
        db.close()
    }
    fun addProfile(profile:Profile): Boolean {
        if(!checkProfile(profile))
            return false
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PROFILENAME, profile.name)
        values.put(SALARY,profile.salary)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }
    fun getProfile(_id: Int): Profile {
        val profile:Profile= Profile()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                profile.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILE_ID)))
                profile.name = cursor.getString(cursor.getColumnIndex(PROFILENAME))
                profile.salary = cursor.getString(cursor.getColumnIndex(SALARY)).toDouble()
            }
        }
        cursor.close()
        return profile
    }

    fun getProfiles():List<Profile>
    {
        var profiles = mutableListOf<Profile>()
        val db= writableDatabase
        val SELECT_QUERY ="SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(SELECT_QUERY,null)
        if(cursor != null)
        {
            val profile= Profile()
            while(cursor.moveToNext())
            {
                profile.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILE_ID)))
                profile.name = cursor.getString(cursor.getColumnIndex(PROFILENAME))
                profile.salary = cursor.getString(cursor.getColumnIndex(SALARY)).toDouble()

                profiles.add(profile)
            }
        }

        return profiles
    }

    fun checkProfile(profile: Profile):Boolean
    {
        var profiles = getProfiles()
        for (tmpProfile in profiles)
        {
            if(profile.name.toLowerCase() == tmpProfile.name.toLowerCase())
                return false
        }
        return true
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "ProfileDB"
        private val TABLE_NAME = "Profile"
        private val PROFILE_ID = "ProfileId"
        private val PROFILENAME = "ProfileName"
        private val SALARY = "Salary"

    }
}