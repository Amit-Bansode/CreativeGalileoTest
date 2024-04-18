package com.amit.creativegalileotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amit.creativegalileotest.customerpaging.CustomerPagingAdapter
import com.amit.creativegalileotest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customerPagingAdapter: CustomerPagingAdapter
    lateinit var viewModel: CustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        customerPagingAdapter = CustomerPagingAdapter()
        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]
        binding.customerList.adapter = customerPagingAdapter
        viewModel.customerLivedata.observe(this, Observer {
            customerPagingAdapter.submitData(lifecycle, it)
        })
    }
}