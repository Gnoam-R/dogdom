package com.roh.dogdom.data.todo

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title :String,
    val description : String?,
    val isDone : Boolean?,
    @PrimaryKey val id: Int?  =  null
)


