package com.example.tz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
 abstract class AppDatabase: RoomDatabase() {
     abstract fun bookDao(): BookDao
}