package com.example.reviewsgrabber.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reviewsgrabber.data.models.Review
import com.example.reviewsgrabber.data.network.Client
import retrofit2.HttpException
import java.io.IOException

const val SECRET_KEY = "NiDcNwAqd8HHjJ47Hnpb19gIHNAKD9tV"
private const val STARTING_PAGE_INDEX = 0
private const val LIMIT = 20

class ReviewsPagingSource() : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val reviews = Client.apiClient.getApiModels(key = SECRET_KEY, offset = position).results
            LoadResult.Page(
                data = reviews,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - LIMIT,
                nextKey = if (reviews.isEmpty()) null else position + LIMIT
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}