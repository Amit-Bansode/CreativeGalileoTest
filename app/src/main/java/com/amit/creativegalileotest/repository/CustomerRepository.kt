package com.amit.creativegalileotest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingSourceFactory
import androidx.paging.liveData
import com.amit.creativegalileotest.customerpaging.CustomerPagingSource
import com.amit.creativegalileotest.retrofit.CustomerAPI
import javax.inject.Inject

class CustomerRepository @Inject constructor (val customerAPI: CustomerAPI) {
    fun getCustomer()=Pager(
        config = PagingConfig(50, maxSize = 500),
        pagingSourceFactory = PagingSourceFactory { CustomerPagingSource(customerAPI) }
    ).liveData
}