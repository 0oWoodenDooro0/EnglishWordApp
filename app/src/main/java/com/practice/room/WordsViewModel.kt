package com.practice.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.room.data.FragmentChange
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
    var fragment: MutableLiveData<FragmentChange> = MutableLiveData(FragmentChange.wordsFragment)
    var deleteVisible: MutableLiveData<Boolean> = MutableLiveData(false)

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
            is WordsEvent.FragmentChange -> {
                fragment.value = event.fragment
            }
            is WordsEvent.DeleteIconClick -> {

            }
        }
    }
}