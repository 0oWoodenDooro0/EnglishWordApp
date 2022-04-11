package com.practice.room.data

import kotlinx.coroutines.flow.Flow

class WordRepositoryImpl(
    private val dao: WordDao
) : WordRepository {

    override fun getWords(): Flow<List<Word>> {
        return dao.getAllData()
    }

    override suspend fun insertWord(word: Word) {
        return dao.insertWord(word)
    }

    override suspend fun deleteWord(word: Word) {
        return dao.deleteWord(word)
    }
}