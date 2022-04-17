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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val wordUseCases: WordUseCases
) : ViewModel() {

    val wordList: LiveData<List<Word>> = wordUseCases.getWords()
    val fragment: MutableLiveData<FragmentChange> = MutableLiveData(FragmentChange.wordsFragment)
    val deleteVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val randomWord: MutableLiveData<Word> = MutableLiveData(null)

    fun onEvent(event: WordsEvent) {
        when (event) {
            is WordsEvent.InsertWord -> {
                viewModelScope.launch(Dispatchers.IO) {
                    wordUseCases.insertWord(event.word, event.content)
                }
            }
            is WordsEvent.DeleteWord -> {
                viewModelScope.launch(Dispatchers.IO) {
                    wordUseCases.deleteWord(event.word)
                }
            }
            is WordsEvent.ChangeFragment -> {
                fragment.postValue(event.fragment)
            }
            is WordsEvent.DeleteIconClick -> {
                deleteVisible.postValue(deleteVisible.value?.not())
            }
            is WordsEvent.RandomWord -> {
                randomWord.postValue(wordUseCases.randomWord(event.wordList))
            }
        }
    }
}