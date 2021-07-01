package com.example.reviewsgrabber.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.reviewsgrabber.data.models.Review
import kotlinx.coroutines.flow.Flow

class ReviewsRepository {
    fun getReviews(): Flow<PagingData<Review>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ReviewsPagingSource()
        }.flow
    }
}