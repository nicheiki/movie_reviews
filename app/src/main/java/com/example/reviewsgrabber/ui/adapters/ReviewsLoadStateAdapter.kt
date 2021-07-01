package com.example.reviewsgrabber.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewsgrabber.databinding.PagingStateBinding

class ReviewsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ReviewsLoadStateAdapter.GamesLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): GamesLoadStateViewHolder {
        val binding = PagingStateBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesLoadStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: GamesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class GamesLoadStateViewHolder(
        private val binding: PagingStateBinding,
        private val retry: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                retryButton.setOnClickListener { retry.invoke() }
                retryProgress.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
            }
        }
    }
}