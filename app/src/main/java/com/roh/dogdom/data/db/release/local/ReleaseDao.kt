package com.roh.dogdom.data.db.release.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReleaseDao {
    @Query ("SELECT * FROM `release` ORDER BY id DESC")
    fun getAll(): List<ReleaseEntity>

    @Insert
    fun insertAll(vararg release: ReleaseEntity)

    @Query("DELETE FROM `release` ")
    fun nukeTable()
}