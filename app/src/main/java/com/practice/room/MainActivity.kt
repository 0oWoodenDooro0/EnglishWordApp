package com.practice.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.room.data.Word
import com.practice.room.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var words: MutableList<Word>
    private lateinit var wordsViewModel: WordsViewModel
    private val adapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wordsViewModel.words.observe(this, Observer {
            adapter.update(it)
        })

        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = adapter

    }
}