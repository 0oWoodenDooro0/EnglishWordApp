package com.practice.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val content: String
)