package com.example.tz.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.tz.data.model.Book

class MainFragmentDiffCallBack(): DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}