package com.gurtam.news.data.repository.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gurtam.news.data.network.NewsService
import com.gurtam.news.data.repository.toDomain
import com.gurtam.news.domain.entity.Headline

class NewsPagingSource(
    val backend: NewsService,
    val query: String
) : PagingSource<Int, Headline>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Headline> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = backend.getHeadlines(query, nextPageNumber, params.loadSize)
            LoadResult.Page(
                data = response.articles.map { it.toDomain() },
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Headline>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}