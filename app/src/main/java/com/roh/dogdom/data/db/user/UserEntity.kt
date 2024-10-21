package com.roh.dogdom.data.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val account_id: String? = null,
    var name: String? = null,
    var profile_nickname: String? = null,
    var profile_image: String? = null,
    var gender: String? = null,
    var oauth_router: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}