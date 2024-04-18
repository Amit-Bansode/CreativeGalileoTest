package com.amit.creativegalileotest.retrofit

import com.amit.creativegalileotest.models.CustomerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CustomerAPI {
    @GET("customer/filter?")
    suspend fun getCustomer(
        @Query("cgId") cgId: String,
        @Query("name") name: String,
        @Query("mobile") mobile: String,
        @Query("paginated") paginated: String,
        @Query("pageNo") pageNo: String,
        @Query("pageSize") pageSize: String
    ) : CustomerResponse
}