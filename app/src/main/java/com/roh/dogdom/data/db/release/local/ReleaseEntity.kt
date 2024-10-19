package com.roh.dogdom.data.db.release.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "release")
data class ReleaseEntity (
    val userId: Long,
    var title: String? = null,
    var comment: String? = null,
    var imageUri: String? = null,
    val timestamp: Long? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = userId
}
