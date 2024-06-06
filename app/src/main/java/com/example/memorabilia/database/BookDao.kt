package com.example.memorabilia.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrentlyReadingBookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentlyReadingBook(book: CurrentlyReadingBook)

    @Query("SELECT * FROM currently_reading WHERE userId = :userId")
    suspend fun getAllBooks(userId:String): List<CurrentlyReadingBook>

    @Query("UPDATE currently_reading SET progress = :progress WHERE id = :id")
    suspend fun updateBookProgress(id: Int, progress: Int)
    @Query("SELECT * FROM currently_reading WHERE userId = :userId AND title = :title AND author = :author LIMIT 1")
    suspend fun getBookByTitleAndAuthor(userId: String, title: String, author: String): CurrentlyReadingBook?
    @Delete
    suspend fun delete(book: CurrentlyReadingBook)
}