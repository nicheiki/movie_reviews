package com.example.reviewsgrabber.ui.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reviewsgrabber.data.models.Review
import com.example.reviewsgrabber.databinding.ReviewItemBinding
import com.squareup.picasso.Picasso

class ReviewAdapter : PagingDataAdapter<Review, ReviewAdapter.ReviewViewHolder>(COMPARATOR) {

    private val width = Resources.getSystem().displayMetrics.widthPixels

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, width) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Review>() {

            override fun areItemsTheSame(oldItem: Review, newItem: Review) =
                oldItem.multimedia.src == newItem.multimedia.src

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem == newItem
        }
    }

    class ReviewViewHolder(private val item: ReviewItemBinding) :
        RecyclerView.ViewHolder(item.root) {

        fun bind(Review: Review, width: Int) {
            item.apply {
                title.text = Review.display_title
                review.text = Review.summary_short
                Picasso.get().load(Review.multimedia.src).into(image)
            }
        }
    }
}

