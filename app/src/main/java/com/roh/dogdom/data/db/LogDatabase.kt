package com.roh.dogdom.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Log::class), version = 1, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao
}