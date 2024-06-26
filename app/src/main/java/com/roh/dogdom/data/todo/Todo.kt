package com.roh.dogdom.data.todo

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
@Entity
data class Todo(
    val title :String,
    val description : String?,
    val isDone : Boolean?,
    @PrimaryKey val id: Int?  =  null
)
 */

@Entity
data class Todo constructor(
    val name:String,
    val age:Int
) {
    @PrimaryKey var id: Int?  =  null
}

//@Entity(tableName = "user")
//data class User constructor(val name:String, val age:Int) {
//    @PrimaryKey(autoGenerate = true)
//    var id:Int=0
//}