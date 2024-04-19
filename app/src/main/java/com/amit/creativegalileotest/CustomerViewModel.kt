package com.amit.creativegalileotest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.amit.creativegalileotest.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(val customerRepository: CustomerRepository) :
    ViewModel() {

    val customerLivedata = customerRepository.getCustomer().cachedIn(viewModelScope)

    fun setSearchParams(name: String, id: String, mobile: String) {
        customerRepository.setSearchParams(name, id, mobile)
    }
}