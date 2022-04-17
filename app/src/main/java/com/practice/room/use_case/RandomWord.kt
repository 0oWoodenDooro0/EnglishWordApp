package com.practice.room.use_case

import com.practice.room.data.Word

class RandomWord{
    operator fun invoke(wordList: List<Word>): Word {
        if (wordList.isEmpty()) {
            return Word(0, "沒有單字", "沒有內容")
        }
        return wordList.random()
    }
}