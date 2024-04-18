package com.amit.creativegalileotest.customerpaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amit.creativegalileotest.models.CustomersItem
import com.amit.creativegalileotest.retrofit.CustomerAPI

class CustomerPagingSource(val customerAPI: CustomerAPI) : PagingSource<Int, CustomersItem>() {
    override fun getRefreshKey(state: PagingState<Int, CustomersItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)!!.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)!!.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CustomersItem> {
        return try {
            val pageNo = params.key ?: 1
            val response = customerAPI.getCustomer(
                "",
                "",
                "",
                "true",
                pageNo.toString(),
                "50"
            )

            val lastPage = response.data?.count!!.toInt() / 50
            LoadResult.Page(
                response.data.customers,
                prevKey = if (pageNo == 0) null else pageNo - 1,
                nextKey = if (pageNo == lastPage) null else pageNo + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}