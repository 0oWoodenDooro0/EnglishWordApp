package com.practice.room.fragments.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.practice.room.R
import com.practice.room.WordsEvent
import com.practice.room.WordsViewModel
import com.practice.room.databinding.FragmentRandomWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomWordFragment : Fragment() {

    private lateinit var binding: FragmentRandomWordBinding
    private val viewModel: WordsViewModel by activityViewModels()
    private var visible = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRandomWordBinding.inflate(inflater, container, false)

        viewModel.randomWord.observe(viewLifecycleOwner) {
            it?.let {
                binding.randomWord.text = it.word
                binding.randomContent.text = it.content
            }
        }

        binding.contentVisible.setOnClickListener {
            contentVisible(visible)
        }

        binding.nextButton.setOnClickListener {
            viewModel.onEvent(WordsEvent.RandomWord)
            contentVisible()
        }

        return binding.root
    }

    private fun contentVisible(visibility: Boolean = true) {
        if (visibility) {
            binding.contentVisible.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_visibility_24
            )
            binding.randomContent.visibility = View.INVISIBLE
            visible = false
        } else {
            binding.contentVisible.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_visibility_off_24
            )
            binding.randomContent.visibility = View.VISIBLE
            visible = true
        }
    }

}