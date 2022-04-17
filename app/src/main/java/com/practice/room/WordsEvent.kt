package com.practice.room

import com.practice.room.data.FragmentChange
import com.practice.room.data.Word

sealed class WordsEvent {
    data class InsertWord(val word: String, val content: String) : WordsEvent()
    data class DeleteWord(val word: Word) : WordsEvent()
    data class RandomWord(val wordList: List<Word>) : WordsEvent()
    data class ChangeFragment(val fragment: FragmentChange) : WordsEvent()
    object DeleteIconClick : WordsEvent()
}
