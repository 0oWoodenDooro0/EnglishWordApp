package com.practice.room.fragments.insert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.practice.room.R
import com.practice.room.WordsEvent
import com.practice.room.WordsViewModel
import com.practice.room.databinding.FragmentInsertWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertWordFragment : Fragment() {

    private lateinit var viewModel: WordsViewModel
    private lateinit var binding: FragmentInsertWordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertWordBinding.inflate(inflater, container, false)

        binding.insertFloatingButton.setOnClickListener {
            viewModel.onEvent(
                WordsEvent.InsertWord(
                    binding.word.text.toString(),
                    binding.content.text.toString()
                )
            )
            findNavController().navigate(R.id.action_insertWordFragment_to_wordsFragment)
        }

        return binding.root
    }

}