package com.roh.dogdom.data.db.user

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = arrayOf(UserEntity::class),
    version = 1,
    exportSchema = false
)
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao
}