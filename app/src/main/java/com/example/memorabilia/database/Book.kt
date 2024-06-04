package com.example.memorabilia.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")

data class Book(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val publicationYear: Int,
    val genre: List<String>,
    val description: String,
    val coverImage: String
)

@Entity
data class CurrentlyReadingBook(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val author: String,
    val imageUrl: Int,
    val synopsis: String,
    val rating: Float
)

@Entity
data class WantToReadBook(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val author: String,
    val imageUrl: Int,
    val synopsis: String,
    val rating: Float
)