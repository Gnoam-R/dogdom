package com.roh.dogdom.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Base")
data class BaseEntity (val msg: String, val timestamp: Long) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
