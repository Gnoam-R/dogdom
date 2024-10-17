package com.roh.dogdom.data.db.log

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LogEntity::class), version = 1, exportSchema = false)
abstract class LogDB : RoomDatabase() {
    abstract fun logDao(): LogDao
}