package com.roh.dogdom.data.home

data class HomeItem (
    val type: ItemType,
    val imageUrl: String? = null,
    val videoUrl: String? = null,
    val text: String? = null
)

enum class ItemType {
    IMAGE, IMAGES, VIDEO, TEXT
}