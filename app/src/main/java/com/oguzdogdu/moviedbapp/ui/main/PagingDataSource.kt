package com.oguzdogdu.moviedbapp.ui.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oguzdogdu.moviedbapp.data.model.asMovies
import com.oguzdogdu.moviedbapp.data.service.MovieService
import com.oguzdogdu.moviedbapp.domain.model.NetworkMovie
import com.oguzdogdu.moviedbapp.util.Constants.API_KEY

class PagingDataSource(private val service: MovieService) : PagingSource<Int, NetworkMovie>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkMovie>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovie> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = service.getUpcoming(API_KEY,currentLoadingPageKey)
            val responseData = mutableListOf<NetworkMovie>()
            val data = response.asMovies()
            responseData.addAll(data.results)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
