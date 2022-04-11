package com.practice.room.use_case

import androidx.lifecycle.LiveData
import com.practice.room.data.Word
import com.practice.room.data.WordRepository

class GetWords(
    private val repository: WordRepository
) {
    operator fun invoke(): LiveData<List<Word>> {
        return repository.getWords()
    }
}