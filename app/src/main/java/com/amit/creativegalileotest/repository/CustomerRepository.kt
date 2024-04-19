package com.amit.creativegalileotest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSourceFactory
import androidx.paging.liveData
import com.amit.creativegalileotest.customerpaging.CustomerPagingSource
import com.amit.creativegalileotest.retrofit.CustomerAPI
import javax.inject.Inject

class CustomerRepository @Inject constructor(val customerAPI: CustomerAPI) {
    private var id: String = ""
    private var mobile: String = ""
    private var name: String = ""

    fun getCustomer() = Pager(
        config = PagingConfig(50, maxSize = 500),
        pagingSourceFactory = PagingSourceFactory {
            CustomerPagingSource(
                name,
                mobile,
                id,
                customerAPI
            )
        }
    ).liveData

    fun setSearchParams(name: String, id: String, mobile: String) {
        this.mobile = mobile
        this.id = id
        this.name = name
    }
}