package com.example.tz.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tz.R
import com.example.tz.data.model.Book
import com.example.tz.databinding.FragmentMainBinding
import com.example.tz.databinding.MainItemBinding
import org.koin.core.context.loadKoinModules

class MainFragmentViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: Book?) {
        if (book == null) {
            binding.cardImage.setImageResource(R.drawable.book_placeholder)
            binding.cardTitle.text = "Загрузка..."
            binding.cardPerson.text = ""
        } else {
            Glide.with(binding.cardImage)
                .load(book.formats.jpeg)
                .placeholder(R.drawable.book_placeholder)
                .into(binding.cardImage)
            binding.cardTitle.text = book.title
            binding.cardPerson.text = book.authors.first().name
        }
    }
}