package com.example.firstcomposeactivity.NotesApp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(student: Notes):Long

    @Delete
    suspend fun deleteStudent(student: Notes)

    /*Room with Flow */
    @Query("SELECT * FROM mynotestable")
    fun getAllStudent(): Flow<List<Notes>>
}