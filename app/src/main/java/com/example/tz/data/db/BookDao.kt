package com.example.tz.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(booksEntity: List<BookEntity>)

    @Query("SELECT * FROM books")
    suspend fun getBooks() : List<BookEntity>
}