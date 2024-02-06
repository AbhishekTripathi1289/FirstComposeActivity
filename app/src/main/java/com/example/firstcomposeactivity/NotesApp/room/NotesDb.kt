package com.example.firstcomposeactivity.NotesApp.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Notes::class], version = 1)
abstract class NotesDb : RoomDatabase()
{
    companion object
    {
        val DB_NAME = "notes_db"
    }
    abstract fun getDao(): NotesDao
}