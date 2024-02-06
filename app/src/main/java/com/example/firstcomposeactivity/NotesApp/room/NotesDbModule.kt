package com.example.firstcomposeactivity.NotesApp.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NotesDbModule
{
    @Provides
    fun provideData(@ApplicationContext applicationContext: Context): NotesDb
    {
       return Room
           .databaseBuilder(applicationContext, NotesDb::class.java, NotesDb.DB_NAME)
           .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRoomDbDao(crudDb: NotesDb): NotesDao
    {
        return crudDb.getDao()
    }
}