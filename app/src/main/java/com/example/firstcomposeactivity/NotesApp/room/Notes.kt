package com.example.firstcomposeactivity.NotesApp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyNotesTable")
data class Notes(@PrimaryKey(autoGenerate = true) var id: Int = 0, var title: String, var desc: String)