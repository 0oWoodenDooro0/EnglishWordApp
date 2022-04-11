package com.practice.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.room.data.Word
import com.practice.room.databinding.RecyclerviewItemRowBinding

class RecyclerViewAdapter() :
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
        words?.let {
            holder.binding.word.text = it[position].word
            holder.binding.content.text = it[position].content
        }
    }

    override fun getItemCount(): Int = words?.size?:0

    class UserDataHolder(val binding: RecyclerviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun update(updateWords: List<Word>) {
        words = updateWords
        notifyDataSetChanged()
    }
}