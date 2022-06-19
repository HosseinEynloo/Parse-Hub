package com.hossein.parsehub.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hossein.parsehub.models.ResponseItems
import com.hossein.parsehub.repository.HomeRepository
import javax.inject.Inject


class ItemsPagingSource @Inject constructor(private val repository: HomeRepository) :
    PagingSource<Int, ResponseItems.Doc>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseItems.Doc>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseItems.Doc> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.itemsList(currentPage)
            val data = response.body()?.docs ?: emptyList()
            val responseData = mutableListOf<ResponseItems.Doc>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}