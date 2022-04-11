package com.practice.room.data

import androidx.lifecycle.LiveData

interface WordRepository {

    fun getWords(): LiveData<List<Word>>

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)
}