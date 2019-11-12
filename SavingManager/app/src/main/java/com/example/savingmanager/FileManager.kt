package com.example.savingmanager
import java.io.File
import java.io.Serializable
import android.content.Context
import android.os.Environment


class FileManager(private val fileName:String)
{
    var filePath = Environment.getExternalStorageDirectory().absolutePath + "profile.txt"
    private var myFile = File(filePath)

    fun init()
    {

        myFile.createNewFile()
    }
    fun saveMyProfile(myProfile:Profile)
    {
        myFile.writeText(myProfile.toString())

    }

    fun loadMyProfile():Profile
    {
        var unParsedString = myFile.readText()
        var parsedString = unParsedString.split('|')
        return (Profile(parsedString[0],parsedString[1].toDouble()))
    }

    fun checkForProfile():Boolean
    {
        return myFile.readLines().count() == 0

    }
}