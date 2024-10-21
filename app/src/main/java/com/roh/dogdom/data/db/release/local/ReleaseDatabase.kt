package com.roh.dogdom.data.db.release.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(ReleaseEntity::class),
    version = 1,
    exportSchema = false
)
abstract class ReleaseDatabase : RoomDatabase() {
    abstract fun baseDao(): ReleaseDao
}