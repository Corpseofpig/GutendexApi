package com.example.tz.presentation.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.tz.data.model.Book
import com.example.tz.databinding.MainItemBinding

class MainFragmentAdapter(var clickListener: (Book) -> Unit): PagingDataAdapter<Book, MainFragmentViewHolder>(MainFragmentDiffCallBack()) {

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener(getItem(position)!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val binding = MainItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return MainFragmentViewHolder(binding)
    }
}