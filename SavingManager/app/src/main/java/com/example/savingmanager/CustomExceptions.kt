package com.example.savingmanager
import Saving
class FileManagerNotInitException:Exception()
{
    override val message: String?
        get() = "File Manager hasn't been initialized yet!"
}

class ExistingSavingException(var saving: Saving): Exception()
{
    override val message: String?
        get() = "The saving: ${saving.name} already exist!"
}