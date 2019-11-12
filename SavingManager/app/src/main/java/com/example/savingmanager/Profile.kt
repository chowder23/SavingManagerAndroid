package com.example.savingmanager

import java.io.Serializable

class Profile(var name:String ="",var salary:Double=0.0,var id:Int=0):Serializable
{
    override fun toString(): String {
        return "$id.  Name: $name, Salary: $salary"
    }

    fun load(name:String,salary:Double)
    {
        this.name=name
        this.salary=salary
    }


}