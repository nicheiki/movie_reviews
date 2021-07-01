package com.example.reviewsgrabber.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.reviewsgrabber.data.models.Review
import com.example.reviewsgrabber.data.repository.ReviewsRepository
import kotlinx.coroutines.flow.Flow

class MovieListViewModel : ViewModel() {

    private val repository = ReviewsRepository()

    fun getReviews(): Flow<PagingData<Review>> {
        return repository.getReviews().cachedIn(viewModelScope)
    }
}
