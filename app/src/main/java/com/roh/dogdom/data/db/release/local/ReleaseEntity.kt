package com.roh.dogdom.data.db.release.local

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "release")
data class ReleaseEntity (
    val userId: Int,
    val title: String? = null,
    val comment: String? = null,
    val imageUri: String? = null,
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

data class UserPostInfo(
    val userId: Int,
    val title: String,
    val comment: String,
    val imageUri: Uri,
    val timestamp: Long
)
