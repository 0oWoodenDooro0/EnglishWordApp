package com.practice.room.data

import androidx.lifecycle.LiveData

class WordRepositoryImpl(
    private val dao: WordDao
) : WordRepository {

    override fun getWords(): LiveData<List<Word>> {
        return dao.getAllData()
    }

    override suspend fun insertWord(word: Word) {
        return dao.insertWord(word)
    }

    override suspend fun deleteWord(word: Word) {
        return dao.deleteWord(word)
    }
}