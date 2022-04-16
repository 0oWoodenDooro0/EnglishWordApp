package com.practice.room

import com.practice.room.data.Word

sealed class WordsEvent {
    data class InsertWord(val word: String, val content: String) : WordsEvent()
    data class DeleteWord(val word: Word) : WordsEvent()
    data class FragmentChange(val fragment: com.practice.room.data.FragmentChange) : WordsEvent()
    object DeleteIconClick : WordsEvent()
}
