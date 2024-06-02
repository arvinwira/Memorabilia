package com.example.memorabilia.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrentlyReadingBookDao {
    @Query("SELECT * FROM currentlyreadingbook")
    suspend fun getAll(): List<CurrentlyReadingBook>

    @Insert
    suspend fun insert(book: CurrentlyReadingBook)
    @Delete
    suspend fun delete(book: CurrentlyReadingBook)
}

@Dao
interface WantToReadBookDao {
    @Query("SELECT * FROM wanttoreadbook")
    suspend fun getAll(): List<WantToReadBook>

    @Insert
    suspend fun insert(book: WantToReadBook)
}