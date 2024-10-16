package com.example.tz.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tz.R
import com.example.tz.data.model.Book
import com.example.tz.databinding.FragmentDetailsBinding
import com.example.tz.databinding.FragmentMainBinding
import com.example.tz.presentation.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() =  _binding!!
    private val viewModel: MainFragmentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
        observeBook()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupBackButton() {
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    fun observeBook() {
        viewModel.selectedBook.observe(viewLifecycleOwner) { book ->
            book?.let {displayBookDetails(it)}
        }
    }

    fun displayBookDetails(book: Book) {
        with(binding) {
            bookTitle.text = book.title
            bookAuthors.text = "Authors:${book.authors.joinToString(", ") {it.name}} "
            bookSubjects.text = "Subjects:${book.subjects.joinToString(", ")}"
            bookLanguages.text = "Languages:${book.languages.joinToString(", ")}"
            bookDownloadCount.text = "DownloadCount:${book.download_count}"
            Glide.with(requireContext())
                .load(book.formats.jpeg)
                .placeholder(R.drawable.book_placeholder)
                .into(bookCover)

            pdfButton.setOnClickListener{
                openInBrowser(book.formats.textHtml)
            }
            epubButton.setOnClickListener{
                openInBrowser(book.formats.epubZip)
            }
            rdfButton.setOnClickListener{
                openInBrowser(book.formats.rdf)
            }
        }
    }

    fun openInBrowser(url: String?) {
        url?.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        }
    }

}