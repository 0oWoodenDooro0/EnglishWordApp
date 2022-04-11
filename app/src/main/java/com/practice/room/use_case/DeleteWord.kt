package com.practice.room.use_case

import com.practice.room.data.Word
import com.practice.room.data.WordRepository

class DeleteWord(
    private val repository: WordRepository
) {
    suspend operator fun invoke(word:Word){
        repository.deleteWord(word)
    }
}