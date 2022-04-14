package com.practice.room.use_case

import android.content.Context
import android.widget.Toast
import com.practice.room.data.Word
import com.practice.room.data.WordRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InsertWord (
    private val repository: WordRepository,
    private val context: Context
) {

    suspend operator fun invoke(word: String, content: String) {
        if (word.isBlank()) {
            Toast.makeText(context, "單字不可為空", Toast.LENGTH_SHORT).show()
            return
        }
        if (content.isBlank()) {
            Toast.makeText(context, "單字內容不可為空", Toast.LENGTH_SHORT).show()
            return
        }
        repository.insertWord(Word(0, word, content))
    }
}