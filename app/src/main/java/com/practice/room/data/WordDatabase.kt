package com.practice.room.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract val wordDao: WordDao

    companion object {
        const val DATABASE_NAME = "words_database"
    }
}