package com.practice.room.fragments.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.practice.room.R
import com.practice.room.WordsViewModel
import com.practice.room.databinding.FragmentRandomWordBinding
import com.practice.room.observeOnce
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

        viewModel.randomWord.observeOnce {
            it?.let {
                binding.randomWord.text = it.word
                binding.randomContent.text = it.content
            }
        }

        binding.contentVisible.setOnClickListener {
            if (visible) {
                binding.contentVisible.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_visibility_24
                )
                binding.randomContent.visibility = View.INVISIBLE
            } else {
                binding.contentVisible.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_visibility_off_24
                )
                binding.randomContent.visibility = View.VISIBLE
            }
            visible = visible.not()
        }

        return binding.root
    }

}