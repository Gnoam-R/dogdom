package com.roh.dogdom.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roh.dogdom.data.db.Log.Log

@Dao
interface BaseDao {
    @Query ("SELECT * FROM Base ORDER BY id DESC")
    fun getAll(): List<Log>

    @Insert
    fun insertAll(vararg Base: Log)

    @Query("DELETE FROM Base")
    fun nukeTable()
}