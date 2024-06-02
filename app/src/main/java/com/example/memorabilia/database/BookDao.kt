package com.example.memorabilia.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    suspend fun getAll(): List<Book>

    @Insert
    suspend fun insert(book: Book)
}