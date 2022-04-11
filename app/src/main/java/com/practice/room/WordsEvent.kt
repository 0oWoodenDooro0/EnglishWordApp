package com.practice.room

import com.practice.room.data.Word

sealed class WordsEvent {
    data class InsertWord(val word: Word) : WordsEvent()
    data class DeleteWord(val word: Word) : WordsEvent()
}
