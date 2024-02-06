package com.example.firstcomposeactivity.NotesApp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.NotesApp.room.Notes
import com.example.firstcomposeactivity.NotesApp.room.NotesDao
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NotesRepo @Inject constructor(private var notesDao: NotesDao) {
    suspend fun inserNotes(notes: Notes)
        {
            notesDao.upsert(notes)
        }
    suspend fun getAllNotes() : Flow<DataState<ArrayList<Notes>>>
    {
       return flow{
           emit(DataState.Loading)
           Log.d("#####", "Loading")
            try
            {
                notesDao.getAllStudent().onStart {
                    Log.d("#####", "OnStart")
                }.catch {
                    Log.d("#####", "onCatch")

                }.collect {
                    Log.d("#####", Gson().toJson(it))
                    if(it.isNullOrEmpty()){
                        emit(DataState.Empty)
                    }
                    else{
                        emit(DataState.Success(it as ArrayList<Notes>))
                    }
                }
            }
            catch (exception: Exception)
            {
                Log.d("#####", Gson().toJson(exception))

                emit(DataState.Error(exception))
            }
        }
    }

    suspend fun deleteNotes(student: Notes)
    {
        notesDao.deleteStudent(student)
    }
}