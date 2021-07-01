package com.example.reviewsgrabber.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.reviewsgrabber.databinding.MovieListFragmentBinding
import com.example.reviewsgrabber.ui.adapters.ReviewAdapter
import com.example.reviewsgrabber.ui.adapters.ReviewsLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {
    private lateinit var binding: MovieListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        val viewModel: MovieListViewModel by viewModels()
        val reviewAdapter = ReviewAdapter()

        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            LinearSnapHelper().attachToRecyclerView(this)

            adapter = reviewAdapter
            adapter = reviewAdapter.withLoadStateHeaderAndFooter(
                header = ReviewsLoadStateAdapter { reviewAdapter.retry() },
                footer = ReviewsLoadStateAdapter { reviewAdapter.retry() }
            )
            setHasFixedSize(true)
        }

        reviewAdapter.addLoadStateListener {
            binding.listProgressBar.isVisible = it.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = it.source.refresh is LoadState.Error
        }

        binding.retryButton.setOnClickListener {
            reviewAdapter.retry()
        }

        lifecycleScope.launch {
            viewModel.getReviews().collectLatest {
                reviewAdapter.submitData(it)
            }
        }

        return binding.root
    }
}