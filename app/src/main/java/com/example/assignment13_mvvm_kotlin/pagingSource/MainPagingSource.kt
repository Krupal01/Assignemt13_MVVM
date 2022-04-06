package com.example.assignment13_mvvm_kotlin.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment13_mvvm_kotlin.model.HitsItem
import com.example.assignment13_mvvm_kotlin.model.Page
import com.example.assignment13_mvvm_kotlin.network.RetroService
import com.example.assignment13_mvvm_kotlin.utils.Constants
import javax.inject.Inject

class MainPagingSource @Inject constructor(val service: RetroService ) : PagingSource<Int , HitsItem>() {


    override fun getRefreshKey(state: PagingState<Int, HitsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HitsItem> {
        return try {

            val currentPage : Int = params.key?:Constants.FIRST_PAGE_NUMBER
            var nextPage : Int? = null
            var prevPage : Int? = null

            val page = service.getRemotePage(Constants.URL_TAG,currentPage)

            nextPage = if (page.nbPages!! > currentPage){
                currentPage+1
            }else{
                null
            }
            prevPage = if(currentPage==1)null else currentPage-1

            LoadResult.Page(data = page.hits!!  , nextKey = nextPage , prevKey = prevPage)
        }
        catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}