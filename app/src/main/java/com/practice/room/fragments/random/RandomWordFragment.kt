package com.practice.room.fragments.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practice.room.databinding.FragmentRandomWordBinding

class RandomWordFragment : Fragment() {

    private lateinit var binding: FragmentRandomWordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRandomWordBinding.inflate(inflater, container, false)

        return binding.root
    }

}