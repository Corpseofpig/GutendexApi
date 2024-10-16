package com.example.tz.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.tz.R
import com.example.tz.databinding.FragmentMainBinding
import com.example.tz.databinding.MainItemBinding
import com.example.tz.presentation.adapter.MainFragmentAdapter
import com.example.tz.presentation.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by sharedViewModel()
    private lateinit var adapter : MainFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeBooks()
        observeNetworkState()
    }
    fun observeNetworkState() {
        lifecycleScope.launch {
            viewModel.networkState.collectLatest { isAvailable ->
                var message = ""
                if (isAvailable) {
                    message = "online mode"
                } else {
                    message = "offline mode"
                }
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun observeBooks() {
        lifecycleScope.launch {
            viewModel.books.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    fun setupAdapter() {
        adapter = MainFragmentAdapter{ bookItem ->
            viewModel.selectBook(bookItem)
            goToBookDetails()
        }
        binding.mainRv.adapter = adapter
    }

    fun goToBookDetails() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment())
            .addToBackStack(null)
            .commit()
    }

}