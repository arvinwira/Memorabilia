package com.example.memorabilia.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val author: String,
    val imageUrl: Int,
    val synopsis: String,
    val rating: Float
)