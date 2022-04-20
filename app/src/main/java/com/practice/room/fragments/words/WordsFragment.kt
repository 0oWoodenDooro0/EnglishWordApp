package com.practice.room.fragments.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.room.RecyclerViewAdapter
import com.practice.room.WordsViewModel
import com.practice.room.databinding.FragmentWordsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordsFragment : Fragment() {

    private lateinit var binding: FragmentWordsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel: WordsViewModel by activityViewModels()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordsBinding.inflate(inflater, container, false)

        adapter = RecyclerViewAdapter(viewModel)

        viewModel.wordList.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = adapter
        return binding.root
    }

}