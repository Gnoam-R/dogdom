package com.roh.dogdom.data.db.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAll(): List<UserEntity>

    @Insert
    fun insertAll(vararg userEntity: UserEntity)

    @Query("DELETE FROM user")
    fun nukeTable()
}