package com.practice.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.room.data.Word
import com.practice.room.databinding.RecyclerviewItemRowBinding

class RecyclerViewAdapter(private val viewModel:WordsViewModel) :
    RecyclerView.Adapter<RecyclerViewAdapter.UserDataHolder>() {

    private var words: List<Word>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDataHolder {
        return UserDataHolder(
            RecyclerviewItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserDataHolder, position: Int) {
        var word : Word
        words?.let { wordList ->
            word = wordList[position]
            holder.binding.word.text = word.word
            holder.binding.content.text = word.content
            holder.binding.deleteButton.setOnClickListener {
                viewModel.onEvent(WordsEvent.DeleteWord(wordList[position]))
            }
        }
    }

    override fun getItemCount(): Int = words?.size ?: 0

    class UserDataHolder(val binding: RecyclerviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun update(updateWords: List<Word>) {
        words = updateWords
        notifyDataSetChanged()
    }
}