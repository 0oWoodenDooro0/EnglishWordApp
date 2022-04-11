package com.practice.room.use_case

import com.practice.room.data.InvalidWordException
import com.practice.room.data.Word
import com.practice.room.data.WordRepository

class InsertWord(
    private val repository: WordRepository
) {

    @Throws(InvalidWordException::class)
    suspend operator fun invoke(word: Word){
        if (word.word.isBlank()){
            throw InvalidWordException("單字不可為空")
        }
        if(word.content.isBlank()){
            throw InvalidWordException("單字內容不可為空")
        }
        repository.insertWord(word)
    }
}