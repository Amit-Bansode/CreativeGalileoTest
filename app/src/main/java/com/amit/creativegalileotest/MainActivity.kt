package com.amit.creativegalileotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.amit.creativegalileotest.customerpaging.CustomerPagingAdapter
import com.amit.creativegalileotest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var searchType: String = ""
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
        binding.rgSearchType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbId -> {
                    this.searchType = getString(R.string.id)
                }

                R.id.rbMobile -> {
                    this.searchType = getString(R.string.mobile_number)

                }

                R.id.rbName -> {
                    this.searchType = getString(R.string.name)
                }
            }
        }
        binding.svCustomer.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchWithType()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty()!!) {
                    searchWithType()
                }
                return false
            }
        })
    }

    private fun searchWithType() {
        var id = ""
        var mobile = ""
        var name = ""
        when (searchType) {
            getString(R.string.id) -> {
                id = binding.svCustomer.query.toString()
            }

            getString(R.string.mobile_number) -> {
                mobile = binding.svCustomer.query.toString()
            }

            getString(R.string.name) -> {
                name = binding.svCustomer.query.toString()
            }
        }
        viewModel.setSearchParams(name, id, mobile)
        customerPagingAdapter.refresh()
    }
}