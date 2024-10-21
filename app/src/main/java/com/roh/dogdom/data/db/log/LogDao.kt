package com.roh.dogdom.data.db.log

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LogDao {
    @Query ("SELECT * FROM logs ORDER BY id DESC")
    fun getAll(): List<LogEntity>

    @Insert
    fun insertAll(vararg logEntities: LogEntity)

    @Query("DELETE FROM logs")
    fun nukeTable()
}