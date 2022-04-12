package com.practice.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.room.WordsEvent
import com.practice.room.data.Word
import com.practice.room.use_case.WordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val wordUseCases: WordUseCases
) : ViewModel() {

    var words: LiveData<List<Word>> = wordUseCases.getWords()

    fun onEvent(event: WordsEvent) {
        when (event) {
            is WordsEvent.InsertWord -> {
                viewModelScope.launch {
                    wordUseCases.insertWord(event.word, event.content)
                }
            }
            is WordsEvent.DeleteWord -> {
                viewModelScope.launch {
                    wordUseCases.deleteWord(event.word)
                }
            }
        }
    }
}