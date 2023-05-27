package com.roh.dogdom.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BaseEntity::class), version = 1, exportSchema = false)
abstract class BaseDb : RoomDatabase() {
//    abstract fun logDao(): LogDao
    abstract fun baseDao(): BaseDao
}