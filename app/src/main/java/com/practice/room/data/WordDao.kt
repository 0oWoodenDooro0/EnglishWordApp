package com.practice.room.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    fun getAllData(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)
}