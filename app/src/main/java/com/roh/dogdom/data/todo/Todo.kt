package com.roh.dogdom.data.todo

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title : LiveData<String?>,
    val description : LiveData<String?>,
    val isDone : LiveData<Boolean?>,
    @PrimaryKey(autoGenerate = true)
    val id: Int?  =  null
)
